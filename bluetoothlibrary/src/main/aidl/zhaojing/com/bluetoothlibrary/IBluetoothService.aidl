// IBluetoothService.aidl
package zhaojing.com.bluetoothlibrary;

// Declare any non-default types here with import statements
import zhaojing.com.bluetoothlibrary.IResponse;
interface IBluetoothService {
    void callBluetoothApi(int code,inout Bundle args,IResponse response);
}
