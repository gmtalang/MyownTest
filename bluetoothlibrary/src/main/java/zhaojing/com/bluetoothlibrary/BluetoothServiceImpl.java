package zhaojing.com.bluetoothlibrary;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import java.util.UUID;

import zhaojing.com.bluetoothlibrary.connect.BleConnectManager;
import zhaojing.com.bluetoothlibrary.connect.options.BleConnectOptions;
import zhaojing.com.bluetoothlibrary.connect.response.BleGeneralResponse;
import zhaojing.com.bluetoothlibrary.search.BluetoothSearchManager;
import zhaojing.com.bluetoothlibrary.search.SearchRequest;
import zhaojing.com.bluetoothlibrary.utils.BluetoothLog;

import static zhaojing.com.bluetoothlibrary.Constants.CODE_CLEAR_REQUEST;
import static zhaojing.com.bluetoothlibrary.Constants.CODE_CONNECT;
import static zhaojing.com.bluetoothlibrary.Constants.CODE_DISCONNECT;
import static zhaojing.com.bluetoothlibrary.Constants.CODE_INDICATE;
import static zhaojing.com.bluetoothlibrary.Constants.CODE_NOTIFY;
import static zhaojing.com.bluetoothlibrary.Constants.CODE_READ;
import static zhaojing.com.bluetoothlibrary.Constants.CODE_READ_DESCRIPTOR;
import static zhaojing.com.bluetoothlibrary.Constants.CODE_READ_RSSI;
import static zhaojing.com.bluetoothlibrary.Constants.CODE_REFRESH_CACHE;
import static zhaojing.com.bluetoothlibrary.Constants.CODE_REQUEST_MTU;
import static zhaojing.com.bluetoothlibrary.Constants.CODE_SEARCH;
import static zhaojing.com.bluetoothlibrary.Constants.CODE_STOP_SESARCH;
import static zhaojing.com.bluetoothlibrary.Constants.CODE_UNNOTIFY;
import static zhaojing.com.bluetoothlibrary.Constants.CODE_WRITE;
import static zhaojing.com.bluetoothlibrary.Constants.CODE_WRITE_DESCRIPTOR;
import static zhaojing.com.bluetoothlibrary.Constants.CODE_WRITE_NORSP;
import static zhaojing.com.bluetoothlibrary.Constants.EXTRA_BYTE_VALUE;
import static zhaojing.com.bluetoothlibrary.Constants.EXTRA_CHARACTER_UUID;
import static zhaojing.com.bluetoothlibrary.Constants.EXTRA_DESCRIPTOR_UUID;
import static zhaojing.com.bluetoothlibrary.Constants.EXTRA_MAC;
import static zhaojing.com.bluetoothlibrary.Constants.EXTRA_MTU;
import static zhaojing.com.bluetoothlibrary.Constants.EXTRA_OPTIONS;
import static zhaojing.com.bluetoothlibrary.Constants.EXTRA_REQUEST;
import static zhaojing.com.bluetoothlibrary.Constants.EXTRA_SERVICE_UUID;
import static zhaojing.com.bluetoothlibrary.Constants.EXTRA_TYPE;

/**
 * Created by dingjikerbo on 2015/10/29.
 */
public class BluetoothServiceImpl extends IBluetoothService.Stub implements Handler.Callback {

    private static BluetoothServiceImpl sInstance;

    private Handler mHandler;

    private BluetoothServiceImpl() {
        mHandler = new Handler(Looper.getMainLooper(), this);
    }

    public static BluetoothServiceImpl getInstance() {
        if (sInstance == null) {
            synchronized (BluetoothServiceImpl.class) {
                if (sInstance == null) {
                    sInstance = new BluetoothServiceImpl();
                }
            }
        }
        return sInstance;
    }

    @Override
    public void callBluetoothApi(int code, Bundle args, final IResponse response) throws RemoteException {
        Message msg = mHandler.obtainMessage(code, new BleGeneralResponse() {

            @Override
            public void onResponse(int code, Bundle data) {
                if (response != null) {
                    if (data == null) {
                        data = new Bundle();
                    }
                    try {
                        response.onResponse(code, data);
                    } catch (Throwable e) {
                        BluetoothLog.e(e);
                    }
                }
            }
        });

        args.setClassLoader(getClass().getClassLoader());
        msg.setData(args);
        msg.sendToTarget();
    }

    @Override
    public boolean handleMessage(Message msg) {
        Bundle args = msg.getData();
        String mac = args.getString(EXTRA_MAC);
        UUID service = (UUID) args.getSerializable(EXTRA_SERVICE_UUID);
        UUID character = (UUID) args.getSerializable(EXTRA_CHARACTER_UUID);
        UUID descriptor = (UUID) args.getSerializable(EXTRA_DESCRIPTOR_UUID);
        byte[] value = args.getByteArray(EXTRA_BYTE_VALUE);
        BleGeneralResponse response = (BleGeneralResponse) msg.obj;

        switch (msg.what) {
            case CODE_CONNECT:
                BleConnectOptions options = args.getParcelable(EXTRA_OPTIONS);
                BleConnectManager.connect(mac, options, response);
                break;

            case CODE_DISCONNECT:
                BleConnectManager.disconnect(mac);
                break;

            case CODE_READ:
                BleConnectManager.read(mac, service, character, response);
                break;

            case CODE_WRITE:
                BleConnectManager.write(mac, service, character, value, response);
                break;

            case CODE_WRITE_NORSP:
                BleConnectManager.writeNoRsp(mac, service, character, value, response);
                break;

            case CODE_READ_DESCRIPTOR:
                BleConnectManager.readDescriptor(mac, service, character, descriptor, response);
                break;

            case CODE_WRITE_DESCRIPTOR:
                BleConnectManager.writeDescriptor(mac, service, character, descriptor, value, response);
                break;

            case CODE_NOTIFY:
                BleConnectManager.notify(mac, service, character, response);
                break;

            case CODE_UNNOTIFY:
                BleConnectManager.unnotify(mac, service, character, response);
                break;

            case CODE_READ_RSSI:
                BleConnectManager.readRssi(mac, response);
                break;

            case CODE_SEARCH:
                SearchRequest request = args.getParcelable(EXTRA_REQUEST);
                BluetoothSearchManager.search(request, response);
                break;

            case CODE_STOP_SESARCH:
                BluetoothSearchManager.stopSearch();
                break;

            case CODE_INDICATE:
                BleConnectManager.indicate(mac, service, character, response);
                break;

            case CODE_REQUEST_MTU:
                int mtu = args.getInt(EXTRA_MTU);
                BleConnectManager.requestMtu(mac, mtu, response);
                break;

            case CODE_CLEAR_REQUEST:
                int clearType = args.getInt(EXTRA_TYPE, 0);
                BleConnectManager.clearRequest(mac, clearType);
                break;

            case CODE_REFRESH_CACHE:
                BleConnectManager.refreshCache(mac);
                break;
        }
        return true;
    }
}
