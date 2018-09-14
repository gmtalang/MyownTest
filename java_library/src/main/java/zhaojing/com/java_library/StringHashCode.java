package zhaojing.com.java_library;

/**
 * AUTHOR：Created by Administrator on 2018-09-14 14:01
 * EMAIL:  2910763715@qq.com
 */

public class StringHashCode {

    public static void main(String[] args){
        String[] hellos = "Hello Hello".split(" ");
        System.out.println(hellos[0].hashCode());
        System.out.println(hellos[1].hashCode());
    }
}
