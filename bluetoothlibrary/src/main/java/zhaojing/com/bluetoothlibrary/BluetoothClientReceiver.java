package zhaojing.com.bluetoothlibrary;


import java.util.HashMap;
import java.util.List;

import zhaojing.com.bluetoothlibrary.connect.listener.BleConnectStatusListener;
import zhaojing.com.bluetoothlibrary.connect.listener.BluetoothStateListener;
import zhaojing.com.bluetoothlibrary.connect.response.BleNotifyResponse;
import zhaojing.com.bluetoothlibrary.receiver.listener.BluetoothBondListener;

/**
 * Created by liwentian on 2017/1/13.
 */

public class BluetoothClientReceiver {

    private HashMap<String, HashMap<String, List<BleNotifyResponse>>> mNotifyResponses;
    private HashMap<String, List<BleConnectStatusListener>> mConnectStatusListeners;
    private List<BluetoothStateListener> mBluetoothStateListeners;
    private List<BluetoothBondListener> mBluetoothBondListeners;
}
