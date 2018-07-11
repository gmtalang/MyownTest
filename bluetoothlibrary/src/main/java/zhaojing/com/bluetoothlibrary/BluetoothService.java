package zhaojing.com.bluetoothlibrary;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import zhaojing.com.bluetoothlibrary.utils.BluetoothLog;

public class BluetoothService extends Service {

    private static Context mContext;
    public static Context getContext(){
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        BluetoothLog.v(String.format("BluetoothService "));
        mContext=getApplicationContext();
        BluetoothContext.set(mContext);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        BluetoothLog.v(String.format("BluetoothService onBind"));
        return BluetoothServiceImpl.getInstance();
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
