package zhaojing.com.myowntest.ndk;

import android.content.Context;

/**
 * AUTHOR：Created by Administrator on 2018-07-10 13:35
 * EMAIL:  2910763715@qq.com
 *
 * E:\yuzt\MyownTest\app\build\intermediates\classes\debug>javah -d jni  -jni -classpath ..\debug zhaojing.com.myowntest.ndk.NDKUtils

 */

public class NDKUtils {

    private Context tx;
    // call method
   public native String getStringFromNative(int key,int name);


}
