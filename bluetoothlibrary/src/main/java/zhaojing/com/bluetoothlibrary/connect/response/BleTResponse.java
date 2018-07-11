package zhaojing.com.bluetoothlibrary.connect.response;

/**
 * AUTHOR：Created by Administrator on 2018-06-22 11:19
 * EMAIL:  2910763715@qq.com
 */

public interface BleTResponse<T> {
    void onResponse(int code,T data);
}
