package zhaojing.com.java_library.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * AUTHOR：Created by Administrator on 2018-09-20 11:24
 * EMAIL:  2910763715@qq.com
 */

public class DaemonFromFactory implements Runnable {
    @Override
    public void run() {
        try{
            while (true){
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread()+" "+this);
            }
        }catch (InterruptedException e){
            System.out.println("interrupt");
        }
    }

    public static void main(String[] args){
        ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
        for(int i=0;i<10;i++)
            exec.execute(new DaemonFromFactory());
        System.out.println("all daemons started");
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
