package zhaojing.com.java_library.thread;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * AUTHOR：Created by Administrator on 2018-09-21 15:46
 * EMAIL:  2910763715@qq.com
 */

public class SleepBlocked implements Runnable{

    @Override
    public void run() {
        try{
            TimeUnit.SECONDS.sleep(100);
        }catch (InterruptedException e){
            System.out.println("interrupted exception");
        }
        System.out.println("exiting sleep.run()");
    }
}
