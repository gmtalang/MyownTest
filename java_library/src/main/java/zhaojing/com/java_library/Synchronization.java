package zhaojing.com.java_library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * AUTHOR：Created by Administrator on 2018-09-18 09:47
 * EMAIL:  2910763715@qq.com
 */

public class Synchronization {
    public static void main(String[] args){
        Collection<String> c = Collections.synchronizedCollection(new ArrayList<String>());
        Map<String,String> m = Collections.synchronizedMap(new HashMap<String, String>());
        Set<String> s = Collections.synchronizedSet(new HashSet<String>());
        List<String> list = Collections.synchronizedList(new ArrayList<String>());
        Map<String,String> sm = Collections.synchronizedSortedMap(new TreeMap<String, String>());

        Collection<String> mm = new ArrayList<>();
        ///////////////////////////////////////////////
//        Iterator<String> it = mm.iterator();
//        mm.add("an object");
        /////////////////////////////
        mm.add("an object");
        Iterator<String> it = mm.iterator();
        try {
            String cuo = it.next();
        }catch (ConcurrentModificationException e){
            System.out.println(e);
        }

    }
}
