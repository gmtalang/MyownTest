package zhaojing.com.java_library.thread;

/**
 * AUTHOR：Created by Administrator on 2018-09-20 09:51
 * EMAIL:  2910763715@qq.com
 */

public class LiftOff implements Runnable {
    protected int count_down=10;
    private static int taskCount=0;
    private final int id = taskCount++;
    public LiftOff(){}
    public LiftOff(int count_down){
        this.count_down=count_down;
    }

    public String status(){
        return "thread_"+id+"("+(count_down>0?count_down:"Liftoff!")+"),";
    }

    @Override
    public void run() {
        while(count_down-- >0){
            System.out.println(status());
            Thread.yield();
        }
    }
}
