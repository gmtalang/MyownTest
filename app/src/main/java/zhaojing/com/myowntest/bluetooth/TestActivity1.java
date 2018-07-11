package zhaojing.com.myowntest.bluetooth;

import android.app.Activity;
import android.os.Bundle;

import zhaojing.com.bluetoothlibrary.utils.BluetoothLog;
import zhaojing.com.myowntest.R;

/**
 * Created by liwentian on 2017/3/9.
 */

public class TestActivity1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity2);
        BluetoothLog.v(String.format("%s onCreate", this.getClass().getSimpleName()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        BluetoothLog.v(String.format("%s onResume", this.getClass().getSimpleName()));
    }



    @Override
    protected void onStart() {
        super.onStart();
        BluetoothLog.v(String.format("%s onStart", this.getClass().getSimpleName()));
    }
}
