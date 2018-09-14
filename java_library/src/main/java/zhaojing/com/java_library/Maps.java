package zhaojing.com.java_library;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * AUTHOR：Created by Administrator on 2018-09-13 15:23
 * EMAIL:  2910763715@qq.com
 */

public class Maps {

    public static void printKeys(Map<Integer,String> map){
        System.out.println(map.keySet());
    }

    public static void test(Map<Integer,String> map){
        System.out.println(map.getClass().getSimpleName());
        map.put(0,"xiaoqiao");
        map.put(1,"daxia");
        for(Map.Entry<Integer,String> entry: map.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        System.out.println("identity hash map = "+map.containsKey(0));
        System.out.println("identity hash map = "+map.get(0));
    }

    public static void main(String[] args){
        IdentityHashMap<Integer,String> map = new IdentityHashMap<>();
        test(map);
    }
}
