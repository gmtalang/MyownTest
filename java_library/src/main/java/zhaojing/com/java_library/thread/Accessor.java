package zhaojing.com.java_library.thread;

/**
 * AUTHOR：Created by Administrator on 2018-09-21 13:32
 * EMAIL:  2910763715@qq.com
 */

public class Accessor implements Runnable{
    private final int id;
    public Accessor(int idn){
        id = idn;
    }

    @Override
    public void run() {
       while(!Thread.currentThread().isInterrupted()){
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
       }
    }

    @Override
    public String toString() {
        return "#"+id +": "+ThreadLocalVariableHolder.get();
    }
}
