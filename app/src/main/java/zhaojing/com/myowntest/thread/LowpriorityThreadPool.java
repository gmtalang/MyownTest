package zhaojing.com.myowntest.thread;

import android.support.annotation.NonNull;

import java.util.concurrent.ThreadFactory;

/**
 * AUTHOR：Created by Administrator on 2018-07-11 11:58
 * EMAIL:  2910763715@qq.com
 */

public class LowpriorityThreadPool implements ThreadFactory{

    private static int count=1;

    @Override
    public Thread newThread(@NonNull Runnable r) {

        Thread t=new Thread(r);
        t.setName("low prio"+count++);
        t.setPriority(4);
        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("thread is name = "+t.getName()+" throwable "+e.getMessage());
            }
        });
        return t;
    }
}
