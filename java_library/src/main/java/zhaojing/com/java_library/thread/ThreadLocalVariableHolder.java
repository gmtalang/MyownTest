package zhaojing.com.java_library.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * AUTHOR：Created by Administrator on 2018-09-21 13:34
 * EMAIL:  2910763715@qq.com
 */

public class ThreadLocalVariableHolder {
    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
        private Random rand = new Random(47);
        protected synchronized Integer initialValue(){
            return rand.nextInt(1000);
        }
    };

    public static void increment(){
        value.set(value.get() + 1);
    }

    public static int get(){
        return value.get();
    }

    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i =0;i<5;i++)exec.execute(new Accessor(i));
        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();

    }
}
