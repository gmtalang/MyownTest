package zhaojing.com.bluetoothlibrary.connect;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;


import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import zhaojing.com.bluetoothlibrary.Code;
import zhaojing.com.bluetoothlibrary.Constants;
import zhaojing.com.bluetoothlibrary.RuntimeChecker;
import zhaojing.com.bluetoothlibrary.connect.options.BleConnectOptions;
import zhaojing.com.bluetoothlibrary.connect.request.BleConnectRequest;
import zhaojing.com.bluetoothlibrary.connect.request.BleIndicateRequest;
import zhaojing.com.bluetoothlibrary.connect.request.BleMtuRequest;
import zhaojing.com.bluetoothlibrary.connect.request.BleNotifyRequest;
import zhaojing.com.bluetoothlibrary.connect.request.BleReadDescriptorRequest;
import zhaojing.com.bluetoothlibrary.connect.request.BleReadRequest;
import zhaojing.com.bluetoothlibrary.connect.request.BleReadRssiRequest;
import zhaojing.com.bluetoothlibrary.connect.request.BleRefreshCacheRequest;
import zhaojing.com.bluetoothlibrary.connect.request.BleRequest;
import zhaojing.com.bluetoothlibrary.connect.request.BleUnnotifyRequest;
import zhaojing.com.bluetoothlibrary.connect.request.BleWriteDescriptorRequest;
import zhaojing.com.bluetoothlibrary.connect.request.BleWriteNoRspRequest;
import zhaojing.com.bluetoothlibrary.connect.request.BleWriteRequest;
import zhaojing.com.bluetoothlibrary.connect.response.BleGeneralResponse;
import zhaojing.com.bluetoothlibrary.utils.BluetoothLog;
import zhaojing.com.bluetoothlibrary.utils.ListUtils;

public class BleConnectDispatcher implements IBleConnectDispatcher, RuntimeChecker, Handler.Callback {

    private static final int MAX_REQUEST_COUNT = 100;
    private static final int MSG_SCHEDULE_NEXT = 0x12;

    private List<BleRequest> mBleWorkList;
    private BleRequest mCurrentRequest;

    private IBleConnectWorker mWorker;

    private String mAddress;

    private Handler mHandler;

    public static BleConnectDispatcher newInstance(String mac) {
        return new BleConnectDispatcher(mac);
    }

    private BleConnectDispatcher(String mac) {
        mAddress = mac;
        mBleWorkList = new LinkedList<BleRequest>();
        mWorker = new BleConnectWorker(mac, this);
        mHandler = new Handler(Looper.myLooper(), this);
    }

    public void connect(BleConnectOptions options, BleGeneralResponse response) {
        addNewRequest(new BleConnectRequest(options, response));
    }

    public void disconnect() {
        checkRuntime();

        BluetoothLog.w(String.format("Process disconnect"));

        if (mCurrentRequest != null) {
            mCurrentRequest.cancel();
            mCurrentRequest = null;
        }

        for (BleRequest request : mBleWorkList) {
            request.cancel();
        }

        mBleWorkList.clear();

        mWorker.closeGatt();
    }

    public void refreshCache() {
        addNewRequest(new BleRefreshCacheRequest(null));
    }

    public void clearRequest(int clearType) {
        checkRuntime();

        BluetoothLog.w(String.format("clearRequest %d", clearType));

        List<BleRequest> requestClear = new LinkedList<BleRequest>();

        if (clearType == 0) {
            requestClear.addAll(mBleWorkList);
        } else {
            for (BleRequest request : mBleWorkList) {
                if (isRequestMatch(request, clearType)) {
                    requestClear.add(request);
                }
            }
        }

        for (BleRequest request : requestClear) {
            request.cancel();
        }

        mBleWorkList.removeAll(requestClear);
    }

    private boolean isRequestMatch(BleRequest request, int requestType) {
        if ((requestType & Constants.REQUEST_READ) != 0) {
            return request instanceof BleReadRequest;
        } else if ((requestType & Constants.REQUEST_WRITE) != 0) {
            return request instanceof BleWriteRequest || request instanceof BleWriteNoRspRequest;
        } else if ((requestType & Constants.REQUEST_NOTIFY) != 0) {
            return request instanceof BleNotifyRequest || request instanceof BleUnnotifyRequest
                    || request instanceof BleIndicateRequest;
        } else if ((requestType & Constants.REQUEST_RSSI) != 0) {
            return request instanceof BleReadRssiRequest;
        } else {
            return false;
        }
    }

    public void read(UUID service, UUID character, BleGeneralResponse response) {
        addNewRequest(new BleReadRequest(service, character, response));
    }

    public void write(UUID service, UUID character, byte[] bytes, BleGeneralResponse response) {
        addNewRequest(new BleWriteRequest(service, character, bytes, response));
    }

    public void writeNoRsp(UUID service, UUID character, byte[] bytes, BleGeneralResponse response) {
        addNewRequest(new BleWriteNoRspRequest(service, character, bytes, response));
    }

    public void readDescriptor(UUID service, UUID character, UUID descriptor, BleGeneralResponse response) {
        addNewRequest(new BleReadDescriptorRequest(service, character, descriptor, response));
    }

    public void writeDescriptor(UUID service, UUID character, UUID descriptor, byte[] bytes, BleGeneralResponse response) {
        addNewRequest(new BleWriteDescriptorRequest(service, character, descriptor, bytes, response));
    }

    public void notify(UUID service, UUID character, BleGeneralResponse response) {
        addNewRequest(new BleNotifyRequest(service, character, response));
    }

    public void unnotify(UUID service, UUID character, BleGeneralResponse response) {
        addNewRequest(new BleUnnotifyRequest(service, character, response));
    }

    public void indicate(UUID service, UUID character, BleGeneralResponse response) {
        addNewRequest(new BleIndicateRequest(service, character, response));
    }

    public void unindicate(UUID service, UUID character, BleGeneralResponse response) {
        addNewRequest(new BleUnnotifyRequest(service, character, response));
    }

    public void readRemoteRssi(BleGeneralResponse response) {
        addNewRequest(new BleReadRssiRequest(response));
    }

    public void requestMtu(int mtu, BleGeneralResponse response) {
        addNewRequest(new BleMtuRequest(mtu, response));
    }

    private void addNewRequest(BleRequest request) {
        checkRuntime();

        if (mBleWorkList.size() < MAX_REQUEST_COUNT) {
            request.setRuntimeChecker(this);
            request.setAddress(mAddress);
            request.setWorker(mWorker);
            mBleWorkList.add(request);
        } else {
            request.onResponse(Code.REQUEST_OVERFLOW);
        }

        scheduleNextRequest(10);
    }

    @Override
    public void onRequestCompleted(BleRequest request) {
        checkRuntime();

        if (request != mCurrentRequest) {
            throw new IllegalStateException("request not match");
        }

        mCurrentRequest = null;

        scheduleNextRequest(10);
    }

    private void scheduleNextRequest(long delayInMillis) {
        mHandler.sendEmptyMessageDelayed(MSG_SCHEDULE_NEXT, delayInMillis);
    }

    private void scheduleNextRequest() {
        if (mCurrentRequest != null) {
            return;
        }

        if (!ListUtils.isEmpty(mBleWorkList)) {
            mCurrentRequest = mBleWorkList.remove(0);
            mCurrentRequest.process(this);
        }
    }

    @Override
    public void checkRuntime() {
        if (Thread.currentThread() != mHandler.getLooper().getThread()) {
            throw new IllegalStateException("Thread Context Illegal");
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MSG_SCHEDULE_NEXT:
                scheduleNextRequest();
                break;
        }
        return true;
    }
}
