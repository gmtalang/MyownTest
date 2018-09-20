package zhaojing.com.java_library.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * AUTHOR：Created by Administrator on 2018-09-20 10:13
 * EMAIL:  2910763715@qq.com
 */

public class SingleThreadExecutor {

    public static void main(String[] args){
        ExecutorService exec = Executors.newSingleThreadExecutor();
        for(int i=0;i<5;i++){
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
