package zhaojing.com.java_library.thread;

/**
 * AUTHOR：Created by Administrator on 2018-09-20 15:00
 * EMAIL:  2910763715@qq.com
 */

public class Joiner extends Thread {
    private Sleeper sleeper;
    public Joiner(String name,Sleeper sleeper){
        super(name);
        this.sleeper = sleeper;
        start();
    }

    @Override
    public void run() {
        try{
            sleeper.join();
        }catch (InterruptedException e){
            System.out.println("interrupted");
        }
        System.out.println(getName()+" join completed");
    }
}
