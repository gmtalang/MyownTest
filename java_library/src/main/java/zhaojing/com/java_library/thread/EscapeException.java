package zhaojing.com.java_library.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * AUTHOR：Created by Administrator on 2018-09-20 15:27
 * EMAIL:  2910763715@qq.com
 */

public class EscapeException implements Runnable {

    @Override
    public void run() {
        throw  new RuntimeException();
    }

    public static void main(String[] args){
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new EscapeException());
    }
}
