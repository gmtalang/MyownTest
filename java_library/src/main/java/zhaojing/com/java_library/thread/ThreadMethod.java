package zhaojing.com.java_library.thread;

/**
 * AUTHOR：Created by Administrator on 2018-09-20 14:04
 * EMAIL:  2910763715@qq.com
 */

public class ThreadMethod {
    private int count_down =5;
    private Thread t;
    private String name;
    public ThreadMethod(String name){
        this.name = name;
    }
    public void runTask(){
        if(t == null){
            t = new Thread(name){
                @Override
                public void run() {
                    try{
                        while(true){
                            System.out.println(this);
                            if(--count_down == 0)return;
                            sleep(100);
                        }
                    }catch(InterruptedException e){
                        System.out.println("sleep interrupt");
                    }
                }
                public String toString(){
                    return getName() + ":" +count_down;
                }
            };
            t.start();
        }
    }

    public static void main(String[] args){
        new ThreadMethod("ThreadMethod").runTask();
    }
}
