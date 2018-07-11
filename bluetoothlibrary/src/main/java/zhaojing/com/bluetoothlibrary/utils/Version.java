package zhaojing.com.bluetoothlibrary.utils;

import android.os.Build;

/**
 * Created by zhaojing on 2018-06-20.
 */

public class Version {
    public static boolean isMarshmallow(){
        return Build.VERSION.SDK_INT>=Build.VERSION_CODES.M;
    }
}
