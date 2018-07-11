package zhaojing.com.bluetoothlibrary.utils.hook;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by zhaojing on 2018-06-20.
 *
 */

public class BluetoothGattProxyHandler implements InvocationHandler{

    private Object bluetoothGatt;

    public BluetoothGattProxyHandler(Object bluetoothGatt){
        this.bluetoothGatt=bluetoothGatt;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(bluetoothGatt,args);
    }
}
