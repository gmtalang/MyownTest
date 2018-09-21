package zhaojing.com.java_library.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * AUTHOR：Created by Administrator on 2018-09-20 17:08
 * EMAIL:  2910763715@qq.com
 */

public class SynchronizedEvenGenerator  {
    private int a=1;
    private boolean keshi=true;

    public synchronized void write_a(){
        System.out.println(Thread.currentThread().getName());

            a++;
            System.out.println(" a = " + a);


    }

    public synchronized void cachu_a(){
        System.out.println(Thread.currentThread().getName());
            a=0;
            if (keshi) {
                System.out.println(" read a = " + a);
            }

    }

    public static void main(String[] args){
        final SynchronizedEvenGenerator even = new SynchronizedEvenGenerator();
        ExecutorService exec= Executors.newCachedThreadPool();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while(i<500)
                even.write_a();
                i++;
            }
        });
        exec.execute(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while(i<100)
                even.cachu_a();
                i++;
            }
        });
//        exec.execute(new Runnable() {
//            @Override
//            public void run() {
//                even.write_a();
//            }
//        });
    }
}