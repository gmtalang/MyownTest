package zhaojing.com.bluetoothlibrary.search;

import zhaojing.com.bluetoothlibrary.search.response.BluetoothSearchResponse;

/**
 * Created by zhaojing on 2018-06-22.
 */

public interface IBluetoothSearchHelper {

    void startSearch(BluetoothSearchRequest request, BluetoothSearchResponse response);
    void stopSearch();
}
