package zhaojing.com.java_library.thread;

/**
 * AUTHOR：Created by Administrator on 2018-09-20 15:04
 * EMAIL:  2910763715@qq.com
 */

public class Joining {

    public static void main(String[] args){
        Sleeper sleepy = new Sleeper("Sleepy",1500),
                grumpy = new Sleeper("Grummy",1500);
        Joiner dopery = new Joiner("Dopey",sleepy),
            doc = new Joiner("doc",grumpy);
        grumpy.interrupt();
    }
}
