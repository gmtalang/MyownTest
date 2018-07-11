package zhaojing.com.myowntest.common;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;

import java.util.List;

/**
 * AUTHOR：Created by Administrator on 2018-07-11 10:43
 * EMAIL:  2910763715@qq.com
 * 用于service的各项检测
 */

public class ServiceUtils {

    public static boolean isRunningService(final String className, Context cx){

        ActivityManager am=(ActivityManager)cx.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> info = am.getRunningServices(Integer.MAX_VALUE);
        if(info==null||info.size()==0)return false;
        for(ActivityManager.RunningServiceInfo amInfo:info){
            if(className.equals(amInfo.service.getClassName())){
                return true;
            }
        }
        return false;
    }

    /***
     * only once onStartCommand
     * @param it
     * @param cx
     * @param className
     */
    public static void startService(Intent it,Context cx,String className){

        if(isRunningService(className,cx)){
            return;
        }

        cx.startService(it);
    }
}
