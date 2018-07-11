package zhaojing.com.myowntest.bluetooth;
import android.bluetooth.BluetoothDevice;

import java.util.UUID;

import zhaojing.com.bluetoothlibrary.Code;
import zhaojing.com.bluetoothlibrary.connect.response.BleNotifyResponse;
import zhaojing.com.bluetoothlibrary.connect.response.BleWriteResponse;
import zhaojing.com.bluetoothlibrary.utils.ByteUtils;
import zhaojing.com.bluetoothlibrary.utils.UUIDUtils;

/**
 * Created by liwentian on 2017/3/27.
 */

public class SecureConnector {

    private static BluetoothDevice mDevice;

    public static void processStep1(BluetoothDevice device) {
        mDevice = device;

        ClientManager.getClient().write(device.getAddress(), UUIDUtils.makeUUID(0xFE95), UUIDUtils.makeUUID(0x10),
                ByteUtils.fromInt(0xDE85CA90), new BleWriteResponse() {
                    @Override
                    public void onResponse(int code) {
                        if (code == Code.REQUEST_SUCCESS) {
                            processStep2();
                        }
                    }
                });
    }

    public static void processStep2() {
        ClientManager.getClient().notify(mDevice.getAddress(), UUIDUtils.makeUUID(0xFE95),
                UUIDUtils.makeUUID(0x01), new BleNotifyResponse() {
                    @Override
                    public void onNotify(UUID service, UUID character, byte[] value) {

                    }

                    @Override
                    public void onResponse(int code) {

                    }
                });
    }
}
