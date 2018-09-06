package zhaojing.com.myowntest.thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AUTHOR：Created by Administrator on 2018-07-11 12:04
 * EMAIL:  2910763715@qq.com
 */

public class TaskTracingThreadPool extends ThreadPoolExecutor {

    private AtomicInteger mTaskCount=new AtomicInteger(0);

    public TaskTracingThreadPool(){
        super(3,3,0L, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        mTaskCount.getAndIncrement();
    }


    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        mTaskCount.getAndDecrement();
    }

    public int getNbrOfTasks() {
        return mTaskCount.get();
    }
}
