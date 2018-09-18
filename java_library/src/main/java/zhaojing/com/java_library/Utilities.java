package zhaojing.com.java_library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * AUTHOR：Created by Administrator on 2018-09-17 15:03
 * EMAIL:  2910763715@qq.com
 */

public class Utilities {
    static List<String>  list = Arrays.asList("one two three four Five Six one".split(" "));
    public static void main(String[] args){
        System.out.println("max w/ comparator : "+ Collections.max(list,String.CASE_INSENSITIVE_ORDER));
        System.out.println("min w/ comparator : "+ Collections.min(list,String.CASE_INSENSITIVE_ORDER));
        List<String> subList = Arrays.asList("four Five Six".split(" "));
        System.out.println("index of sub list : " + Collections.indexOfSubList(list,subList)) ;
        System.out.println("last index of sublist : "+ Collections.lastIndexOfSubList(list,subList));
        Collections.replaceAll(list,"one","Yo");
        System.out.println("replaceAll :"+list);
        Collections.reverse(list);
        System.out.println("reverse :"+list);
        Collections.rotate(list,3);
        System.out.println("rotate :"+list);
        List<String> source = Arrays.asList("in the matrix".split(" "));
        Collections.copy(list,source);
        System.out.println("copy :"+list);
        Collections.swap(list,0,list.size() - 1);
        System.out.println("swap :"+list);
        Collections.shuffle(list,new Random(47));// change sequence
        System.out.println("shuffled: " + list);
        Collections.fill(list,"pop");
        System.out.println("fill :"+ list);
        List<String> dups = Collections.nCopies(3,"snap");
        Enumeration<String> e = Collections.enumeration(dups);
        Vector<String> v = new Vector<>();
        while(e.hasMoreElements())v.addElement(e.nextElement());
        ArrayList<String> arrayList = Collections.list(v.elements());
        System.out.println("arraylist :"+ arrayList);
    }
}
