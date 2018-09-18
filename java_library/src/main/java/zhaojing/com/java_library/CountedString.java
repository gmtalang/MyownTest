package zhaojing.com.java_library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * AUTHOR：Created by Administrator on 2018-09-14 14:23
 * EMAIL:  2910763715@qq.com
 */

public class CountedString {
    private static List<String> created = new ArrayList<>();
    private String s;
    private int id = 0;

    public CountedString(String s) {
        this.s = s;
        created.add(s);
        for(String s2:created)
            if(s2.equals(s))id++;
    }

    public String toString(){
        return "String : "+s+" id:"+id +" hashCode(): "+hashCode();
    }

    public int hashCode(){
        System.out.println("hashcode");
        int result =17;
        result = 37*result+s.hashCode();
        result = 37*result+id;
        System.out.println(result);
        return result;
    }
    public boolean equals(Object o){
        System.out.println("equals");
        return o instanceof CountedString &&
                s.equals(((CountedString)o).s)&&
                id == ((CountedString)o).id;
    }

    public static void main(String[] args){
        Map<CountedString,Integer> map =
                new HashMap<CountedString,Integer>();
        CountedString[] cs = new CountedString[5];
        for(int i = 0; i <cs.length;i++){
            cs[i] = new CountedString("hi");
            map.put(cs[i],i);
        }
        for(CountedString cstring:cs){
            System.out.println(map.get(cstring));

        }

    }
}
