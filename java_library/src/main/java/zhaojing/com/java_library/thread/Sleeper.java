package zhaojing.com.java_library.thread;

/**
 * AUTHOR：Created by Administrator on 2018-09-20 14:31
 * EMAIL:  2910763715@qq.com
 */

public class Sleeper extends Thread{
    private int duration;
    public Sleeper(String name,int sleepTime){
        super(name);
        duration = sleepTime;
        start();
    }

    @Override
    public void run() {
        try{
            sleep(duration);
        }catch (InterruptedException e){
            System.out.println(getName()+"was interrupt"+"isInterrupted():" +isInterrupted());
            return;
        }
        System.out.println(getName()+" has awakened");
    }
}
