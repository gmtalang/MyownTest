package zhaojing.com.myowntest.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.LinkedList;
import java.util.Queue;

/**
 * AUTHOR：Created by Administrator on 2018-07-10 11:46
 * EMAIL:  2910763715@qq.com
 */

public class SaveLocationService extends Service{

    private static final String LOCATION_KEY="location";

    private boolean shouldStop=false;
    private Queue<String> jobs=new LinkedList<>();
    private Thread thread=new Thread(new Runnable() {
        @Override
        public void run() {
            while (!shouldStop){
                String location=takeLocation();
                if(location==null){
                    System.out.println("getted location --- "+location);
                }
            }
        }
    });

    private String takeLocation(){
        String location=null;
        synchronized (jobs){
            if(jobs.isEmpty()){
                try{
                    jobs.wait();
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                    return null;
                }
            }
            location = jobs.poll();
        }
        return location;

    }
    @Override
    public void onCreate() {
        super.onCreate();
        thread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
         super.onStartCommand(intent, flags, startId);
        String location=intent.getStringExtra(LOCATION_KEY);
        synchronized (jobs){
            jobs.add(location);
            jobs.notify();
        }

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        synchronized (jobs){
            shouldStop=true;
            jobs.notify();
        }
    }
}
