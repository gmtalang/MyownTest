package zhaojing.com.bluetoothlibrary.connect;

import zhaojing.com.bluetoothlibrary.connect.request.BleRequest;

public interface IBleConnectDispatcher {

    void onRequestCompleted(BleRequest request);
}
