package zhaojing.com.java_library.thread;

import java.util.concurrent.ThreadFactory;

/**
 * AUTHOR：Created by Administrator on 2018-09-20 11:19
 * EMAIL:  2910763715@qq.com
 * produce many threads
 */

public class DaemonThreadFactory implements ThreadFactory{

    @Override
    public Thread newThread(Runnable runnable) {
        Thread t =new Thread(runnable);
        t.setDaemon(true);
        return t;
    }
}
