package zhaojing.com.bluetoothlibrary.search;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.reflect.Method;

import zhaojing.com.bluetoothlibrary.search.response.BluetoothSearchResponse;
import zhaojing.com.bluetoothlibrary.utils.BluetoothUtils;
import zhaojing.com.bluetoothlibrary.utils.proxy.ProxyBulk;
import zhaojing.com.bluetoothlibrary.utils.proxy.ProxyInterceptor;
import zhaojing.com.bluetoothlibrary.utils.proxy.ProxyUtils;

/**
 * Created by zhaojing on 2018-06-22.
 */

public class BluetoothSearchHelper implements IBluetoothSearchHelper,ProxyInterceptor, Handler.Callback {
    private BluetoothSearchRequest mCurrentRequest;
    private static IBluetoothSearchHelper sInstance;
    private Handler mHandler;

    private BluetoothSearchHelper(){
        mHandler = new Handler(Looper.getMainLooper(),this);
    }
    public static IBluetoothSearchHelper getsInstance(){
        if(sInstance==null){
            synchronized (BluetoothSearchHelper.class){
                if(sInstance==null){
                    BluetoothSearchHelper helper=new BluetoothSearchHelper();
                    sInstance= ProxyUtils.getProxy(helper,IBluetoothSearchHelper.class,helper);

                }//if
            }//synchronized
        }//if
        return sInstance;
    }//public

    @Override
    public boolean handleMessage(Message msg) {
        ProxyBulk.safeInvoke(msg.obj);
        return true;
    }

    @Override
    public boolean onIntercept(Object object, Method method, Object[] args) {
        mHandler.obtainMessage(0,new ProxyBulk(object,method,args)).sendToTarget();
        return true;
    }

    @Override
    public void startSearch(BluetoothSearchRequest request, BluetoothSearchResponse response) {
        request.setSearchResponse(new BluetoothSearchResponseImpl(response));

        if(!BluetoothUtils.isBluetoothEnabled()){
            request.cancel();
        }else {
            stopSearch();
            if(mCurrentRequest==null){
                mCurrentRequest=request;
                mCurrentRequest.start();
            }
        }
    }

    @Override
    public void stopSearch() {
        if(mCurrentRequest!=null){
            mCurrentRequest.cancel();
            mCurrentRequest=null;
        }
    }



    private class BluetoothSearchResponseImpl implements BluetoothSearchResponse{

        BluetoothSearchResponse response;
        BluetoothSearchResponseImpl(BluetoothSearchResponse response){

            this.response=response;

        }

        @Override
        public void onSearchStarted() {
            response.onSearchStarted();
        }

        @Override
        public void onDeviceFounded(SearchResult device) {
            response.onDeviceFounded(device);
        }

        @Override
        public void onSearchStopped() {
            response.onSearchStopped();
        }

        @Override
        public void onSearchCanceled() {
            response.onSearchStopped();
            mCurrentRequest=null;
        }
    }
}
