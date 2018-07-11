package zhaojing.com.bluetoothlibrary.receiver;

import java.util.List;

import zhaojing.com.bluetoothlibrary.receiver.listener.BluetoothReceiverListener;

/**
 * Created by dingjikerbo on 16/11/26.
 */

public interface IReceiverDispatcher {

    List<BluetoothReceiverListener> getListeners(Class<?> clazz);
}
