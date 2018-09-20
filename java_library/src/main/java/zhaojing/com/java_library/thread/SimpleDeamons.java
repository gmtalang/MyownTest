package zhaojing.com.java_library.thread;

import java.util.concurrent.TimeUnit;

/**
 * AUTHOR：Created by Administrator on 2018-09-20 11:07
 * EMAIL:  2910763715@qq.com
 */

public class SimpleDeamons  implements Runnable{

    @Override
    public void run() {
        try {
            while (true){
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() +" "+this);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
            System.out.println("interrrupted");
        }
    }

    public static void main(String[] args){
        for(int i=0;i<10;i ++){
            Thread daemon = new Thread(new SimpleDeamons());
            daemon.setDaemon(true);
            daemon.start();
        }

        System.out.println("all daemon started");
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
