package zhaojing.com.bluetoothlibrary.utils.hook;

import android.os.IBinder;
import android.os.IInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import zhaojing.com.bluetoothlibrary.utils.BluetoothLog;
import zhaojing.com.bluetoothlibrary.utils.hook.utils.HookUtils;

/**
 * Created by zhaojing on 2018-06-20.
 */

public class BluetoothManagerBinderProxyHandler implements InvocationHandler{

    private IBinder iBinder;
    private Object iBluetoothManager;

    private Class<?> iBluetoothManagerClaz;

    public BluetoothManagerBinderProxyHandler(IBinder iBinder){
        this.iBinder=iBinder;
        this.iBluetoothManagerClaz= HookUtils.getClass("android.bluetooth.IBluetoothManager");
        Class<?> stub = HookUtils.getClass("android.bluetooth.IBluetoothManager$Stub");
        Method asInterface=HookUtils.getMethod(stub,"asInterface",IBinder.class);
        this.iBluetoothManager=HookUtils.invoke(asInterface,null,iBinder);

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        BluetoothLog.v(String.format("IBinder method: %s", method.getName()));
        if("queryLocalInterface".equals(method.getName())){
            return Proxy.newProxyInstance(proxy.getClass().getClassLoader(),
                    new Class<?>[]{IBinder.class, IInterface.class,iBluetoothManagerClaz},
                    new BluetoothManagerProxyHandler(iBluetoothManager));
        }
        return method.invoke(iBinder,args);
    }
}
