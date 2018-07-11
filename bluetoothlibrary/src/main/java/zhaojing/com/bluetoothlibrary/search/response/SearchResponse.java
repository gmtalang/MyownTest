package zhaojing.com.bluetoothlibrary.search.response;

import zhaojing.com.bluetoothlibrary.search.SearchResult;

/**
 * Created by zhaojing on 2016/9/1.
 */
public interface SearchResponse {

    void onSearchStarted();

    void onDeviceFounded(SearchResult device);

    void onSearchStopped();

    void onSearchCanceled();
}
