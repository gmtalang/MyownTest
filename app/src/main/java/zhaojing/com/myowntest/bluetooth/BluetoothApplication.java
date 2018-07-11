package zhaojing.com.myowntest.bluetooth;

import android.app.Application;
import android.os.Handler;

import zhaojing.com.bluetoothlibrary.BluetoothContext;

/**
 * Created by dingjikerbo on 2016/8/27.
 */
public class BluetoothApplication extends Application {

    private static BluetoothApplication instance;

    public static Application getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        BluetoothContext.set(this);

    }
}
