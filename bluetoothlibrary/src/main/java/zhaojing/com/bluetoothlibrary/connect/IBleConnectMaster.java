package zhaojing.com.bluetoothlibrary.connect;


import java.util.UUID;

import zhaojing.com.bluetoothlibrary.connect.options.BleConnectOptions;
import zhaojing.com.bluetoothlibrary.connect.response.BleGeneralResponse;

/**
 * Created by dingjikerbo on 2016/8/24.
 */
public interface IBleConnectMaster {

    void connect(BleConnectOptions options, BleGeneralResponse response);

    void disconnect();

    void read(UUID service, UUID character, BleGeneralResponse response);

    void write(UUID service, UUID character, byte[] bytes, BleGeneralResponse response);

    void writeNoRsp(UUID service, UUID character, byte[] bytes, BleGeneralResponse response);

    void readDescriptor(UUID service, UUID character, UUID descriptor, BleGeneralResponse response);

    void writeDescriptor(UUID service, UUID character, UUID descriptor, byte[] value, BleGeneralResponse response);

    void notify(UUID service, UUID character, BleGeneralResponse response);

    void unnotify(UUID service, UUID character, BleGeneralResponse response);

    void readRssi(BleGeneralResponse response);

    void indicate(UUID service, UUID character, BleGeneralResponse response);

    void requestMtu(int mtu, BleGeneralResponse response);

    void clearRequest(int clearType);

    void refreshCache();
}
