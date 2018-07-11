// IResponse.aidl
package zhaojing.com.bluetoothlibrary;

// Declare any non-default types here with import statements

interface IResponse {
    void onResponse(int code,inout Bundle data);
}
