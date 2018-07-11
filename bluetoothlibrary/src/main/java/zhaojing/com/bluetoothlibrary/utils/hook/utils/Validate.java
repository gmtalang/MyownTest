package zhaojing.com.bluetoothlibrary.utils.hook.utils;

/**
 * Created by zhaojing on 2018-06-20.
 */

public class Validate {
    public static void isTrue(boolean expression,String message,Object... values){
       if (expression=false){
           throw new IllegalArgumentException(String.format(message,values));
       }
    }
}
