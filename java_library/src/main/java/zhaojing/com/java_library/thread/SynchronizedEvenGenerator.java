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

    public  void write_a(){
        System.out.println(Thread.currentThread().getName());
        while (a<10000) {
            a++;
            System.out.println(" a = " + a);

        }
    }

    public void read_a(){
        System.out.println(Thread.currentThread().getName());
        while(a<1000) {
            if (keshi) {
                System.out.println(" read a = " + a);
            }
        }
    }

    public static void main(String[] args){
        final SynchronizedEvenGenerator even = new SynchronizedEvenGenerator();
        ExecutorService exec= Executors.newCachedThreadPool();
        exec.execute(new Runnable() {
            @Override
            public void run() {
                even.write_a();
            }
        });
        exec.execute(new Runnable() {
            @Override
            public void run() {
                even.read_a();
            }
        });
        exec.execute(new Runnable() {
            @Override
            public void run() {
                even.write_a();
            }
        });
    }
}
