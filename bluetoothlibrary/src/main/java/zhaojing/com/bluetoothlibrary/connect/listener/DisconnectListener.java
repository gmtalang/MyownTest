package zhaojing.com.bluetoothlibrary.connect.listener;

/**
 * Created by dingjikerbo on 2016/9/7.
 */
public interface DisconnectListener extends GattResponseListener {
    void onDisconnected();
}
