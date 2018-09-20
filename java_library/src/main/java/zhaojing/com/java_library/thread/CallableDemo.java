package zhaojing.com.java_library.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * AUTHOR：Created by Administrator on 2018-09-20 10:21
 * EMAIL:  2910763715@qq.com
 */
class TaskWithResult implements Callable<String>{
    private int id;
    public TaskWithResult(int id){
        this.id = id;
    }
    @Override
    public String call() throws Exception {
        return "result of taskwithresult_"+id;
    }
}

public class CallableDemo {
    public static void main(String[] args){
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Future<String>> results = new ArrayList<>();
        for(int i = 0;i<10;i++){
            results.add(exec.submit(new TaskWithResult(i)));
        }
        for(Future<String> fs:results) {
            try {
                System.out.println(fs.get());
            } catch (ExecutionException e){
                e.printStackTrace();
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                exec.shutdown();
            }
        }
    }
}
