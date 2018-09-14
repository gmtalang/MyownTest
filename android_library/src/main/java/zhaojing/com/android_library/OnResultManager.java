package zhaojing.com.android_library;

import android.app.Activity;
import android.content.Intent;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * AUTHOR：Created by Administrator on 2018-09-13 14:39
 * EMAIL:  2910763715@qq.com
 *
 * startForResult
 */

public class OnResultManager {
    private static final String TAG = "OnResultManager";

    private static WeakHashMap<Activity,HashMap<Integer,Callback>> sCallbacks =new WeakHashMap();
    private WeakReference<Activity> mActivity;

    public OnResultManager(Activity activity){
        mActivity = new WeakReference<Activity>(activity);
    }

    public void startForResult(Intent intent,int reqeuestCode,Callback callback){
        Activity activity = getActivity();
        if(activity == null)return;
        addCallBack(activity,reqeuestCode,callback);
        activity.startActivityForResult(intent,reqeuestCode);
    }

    public void trigger(int requestCode,int resultCode,Intent data){
        Activity activity = getActivity();
        if(activity == null)return;
        Callback callback = findCallBack(activity,requestCode);
        if(callback != null){
            callback.onActivityResult(requestCode,resultCode,data);
        }
    }

    private void addCallBack(Activity activity,int requestCode,Callback callback){

        HashMap<Integer,Callback> map = sCallbacks.get(activity);
        if(map == null){
            map = new HashMap<>();
            sCallbacks.put(activity,map);
        }
        map.put(requestCode,callback);

    }

    private Callback findCallBack(Activity activity,int requestCode){
        HashMap<Integer,Callback> map = sCallbacks.get(activity);
        if(map != null){
            return map.remove(requestCode);
        }
        return null;
    }

    private Activity getActivity(){
        return mActivity.get();
    }

    public interface Callback{
        void onActivityResult(int requestCode,int resultCode,Intent data);
    }

}
