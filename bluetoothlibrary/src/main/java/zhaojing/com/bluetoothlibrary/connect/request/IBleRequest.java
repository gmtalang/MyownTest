package zhaojing.com.bluetoothlibrary.connect.request;

import zhaojing.com.bluetoothlibrary.connect.IBleConnectDispatcher;

/**
 * AUTHOR：Created by Administrator on 2018-06-22 11:43
 * EMAIL:  2910763715@qq.com
 */

public interface IBleRequest {
    void process(IBleConnectDispatcher dispatcher);
    void cancel();
}
