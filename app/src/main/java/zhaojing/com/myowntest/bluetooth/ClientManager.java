package zhaojing.com.myowntest.bluetooth;

import zhaojing.com.bluetoothlibrary.BluetoothClient;

/**
 * Created by dingjikerbo on 2016/8/27.
 */
public class ClientManager {

    private static BluetoothClient mClient;

    public static BluetoothClient getClient() {
        if (mClient == null) {
            synchronized (ClientManager.class) {
                if (mClient == null) {
                    mClient = new BluetoothClient(BluetoothApplication.getInstance());
                }
            }
        }
        return mClient;
    }
}
