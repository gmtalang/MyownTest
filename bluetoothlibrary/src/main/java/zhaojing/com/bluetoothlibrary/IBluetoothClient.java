package zhaojing.com.bluetoothlibrary;

import java.util.UUID;

import zhaojing.com.bluetoothlibrary.connect.listener.BleConnectStatusListener;
import zhaojing.com.bluetoothlibrary.connect.listener.BluetoothStateListener;
import zhaojing.com.bluetoothlibrary.connect.options.BleConnectOptions;
import zhaojing.com.bluetoothlibrary.connect.response.BleConnectResponse;
import zhaojing.com.bluetoothlibrary.connect.response.BleMtuResponse;
import zhaojing.com.bluetoothlibrary.connect.response.BleNotifyResponse;
import zhaojing.com.bluetoothlibrary.connect.response.BleReadResponse;
import zhaojing.com.bluetoothlibrary.connect.response.BleReadRssiResponse;
import zhaojing.com.bluetoothlibrary.connect.response.BleUnnotifyResponse;
import zhaojing.com.bluetoothlibrary.connect.response.BleWriteResponse;
import zhaojing.com.bluetoothlibrary.receiver.listener.BluetoothBondListener;
import zhaojing.com.bluetoothlibrary.search.SearchRequest;
import zhaojing.com.bluetoothlibrary.search.response.SearchResponse;

/**
 * Created by dingjikerbo on 2016/8/25.
 */
public interface IBluetoothClient {

    void connect(String mac, BleConnectOptions options, BleConnectResponse response);

    void disconnect(String mac);

    void registerConnectStatusListener(String mac, BleConnectStatusListener listener);

    void unregisterConnectStatusListener(String mac, BleConnectStatusListener listener);

    void read(String mac, UUID service, UUID character, BleReadResponse response);

    void write(String mac, UUID service, UUID character, byte[] value, BleWriteResponse response);

    void readDescriptor(String mac, UUID service, UUID character, UUID descriptor, BleReadResponse response);

    void writeDescriptor(String mac, UUID service, UUID character, UUID descriptor, byte[] value, BleWriteResponse response);

    void writeNoRsp(String mac, UUID service, UUID character, byte[] value, BleWriteResponse response);

    void notify(String mac, UUID service, UUID character, BleNotifyResponse response);

    void unnotify(String mac, UUID service, UUID character, BleUnnotifyResponse response);

    void indicate(String mac, UUID service, UUID character, BleNotifyResponse response);

    void unindicate(String mac, UUID service, UUID character, BleUnnotifyResponse response);

    void readRssi(String mac, BleReadRssiResponse response);

    void requestMtu(String mac, int mtu, BleMtuResponse response);

    void search(SearchRequest request, SearchResponse response);

    void stopSearch();

    void registerBluetoothStateListener(BluetoothStateListener listener);

    void unregisterBluetoothStateListener(BluetoothStateListener listener);

    void registerBluetoothBondListener(BluetoothBondListener listener);

    void unregisterBluetoothBondListener(BluetoothBondListener listener);

    void clearRequest(String mac, int type);

    void refreshCache(String mac);
}
