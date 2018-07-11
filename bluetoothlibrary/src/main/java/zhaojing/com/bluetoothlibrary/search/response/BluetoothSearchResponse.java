package zhaojing.com.bluetoothlibrary.search.response;


import zhaojing.com.bluetoothlibrary.search.SearchResult;

public interface BluetoothSearchResponse {
    void onSearchStarted();

    void onDeviceFounded(SearchResult device);

    void onSearchStopped();

    void onSearchCanceled();
}
