package zhaojing.com.bluetoothlibrary.receiver;

import zhaojing.com.bluetoothlibrary.receiver.listener.BluetoothReceiverListener;

/**
 * Created by dingjikerbo on 2016/11/25.
 */

public interface IBluetoothReceiver {

    void register(BluetoothReceiverListener listener);
}
