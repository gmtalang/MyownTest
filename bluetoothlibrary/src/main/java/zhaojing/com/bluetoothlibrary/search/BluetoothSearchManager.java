package zhaojing.com.bluetoothlibrary.search;

import android.os.Bundle;

import zhaojing.com.bluetoothlibrary.connect.response.BleGeneralResponse;
import zhaojing.com.bluetoothlibrary.search.response.BluetoothSearchResponse;

import static zhaojing.com.bluetoothlibrary.Constants.DEVICE_FOUND;
import static zhaojing.com.bluetoothlibrary.Constants.EXTRA_SEARCH_RESULT;
import static zhaojing.com.bluetoothlibrary.Constants.SEARCH_CANCEL;
import static zhaojing.com.bluetoothlibrary.Constants.SEARCH_START;
import static zhaojing.com.bluetoothlibrary.Constants.SEARCH_STOP;

/**
 * AUTHOR：Created by zhaojing on 2018-06-22 11:11
 * EMAIL:  2910763715@qq.com
 */

public class BluetoothSearchManager {

    public static void search(SearchRequest request,final BleGeneralResponse response){
        BluetoothSearchRequest requestWrapper = new BluetoothSearchRequest(request);
        BluetoothSearchHelper.getsInstance().startSearch(requestWrapper, new BluetoothSearchResponse() {
            @Override
            public void onSearchStarted() {
                response.onResponse(SEARCH_START,null);
            }

            @Override
            public void onDeviceFounded(SearchResult device) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(EXTRA_SEARCH_RESULT,device);
                response.onResponse(DEVICE_FOUND,bundle);
            }

            @Override
            public void onSearchStopped() {
                response.onResponse(SEARCH_STOP,null);
            }

            @Override
            public void onSearchCanceled() {
                response.onResponse(SEARCH_CANCEL,null);
            }
        });
    }

    public static void stopSearch(){
        BluetoothSearchHelper.getsInstance().stopSearch();
    }
}
