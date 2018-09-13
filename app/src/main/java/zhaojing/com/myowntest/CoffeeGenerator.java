package zhaojing.com.myowntest;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import zhaojing.com.myowntest.view.Coffee;

/**
 * AUTHOR：Created by Administrator on 2018-09-11 15:38
 * EMAIL:  2910763715@qq.com
 * ctrl+j tag
 */

public class CoffeeGenerator<A> implements Generator<Coffee> ,Iterable<Coffee>{
    A x;

    public CoffeeGenerator(Class<A> kind){
       try{
           x=kind.newInstance();
       } catch (Exception e){
           throw new RuntimeException(e);
       }
    }


    /***
     *  fanxing function
     * @param args
     * @param <T>
     * @return
     */
    public static <T> List<T> makeList(T... args){

    }

    /***
     * fan xing function
     * @param x
     * @param <T>
     */
    public <T> void f(T x){
        System.out.println(x.getClass().getName());

    }

    public static <K,V> Map<K,V> map(){
        return new HashMap<K,V>();
    }

    @Override
    public Coffee next() {
        return null;
    }

    @NonNull
    @Override
    public Iterator<Coffee> iterator() {
        return null;
    }
}
