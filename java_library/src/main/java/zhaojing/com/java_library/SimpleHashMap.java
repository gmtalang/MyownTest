package zhaojing.com.java_library;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/**
 * AUTHOR：Created by Administrator on 2018-09-14 11:26
 * EMAIL:  2910763715@qq.com
 */

public class SimpleHashMap<K,V> extends AbstractMap<K,V>{
    private static final String TAG = "SimpleHashMap";
    private static final int SIZE = 997;
    private LinkedList<MapEntry<K,V>>[] buckets = new LinkedList[SIZE];

    public V put(K key,V value){
        V oldValue = null;
        int index = Math.abs(key.hashCode())%SIZE;
        if(buckets[index] == null)
            buckets[index] = new LinkedList<MapEntry<K,V>>();
        LinkedList<MapEntry<K,V>> bucket = buckets[index];
        MapEntry<K,V> pair = new MapEntry<K,V>(key,value);
        boolean found =false;
        ListIterator<MapEntry<K,V>> it = bucket.listIterator();
        while(it.hasNext()){
            MapEntry<K,V> ipair =it.next();
            if(ipair.getKey().equals(key)){
                oldValue = ipair.getValue();
                it.set(pair);
                found = true;
                break;
            }
        }
        if(!found)buckets[index].add(pair);
        return oldValue;
    }

    public V get(Object key){
        int index = Math.abs(key.hashCode())%SIZE;
        if(buckets[index] == null) return null;
        for(MapEntry<K,V> ipair:buckets[index])
            if(ipair.getKey().equals(key))
                return ipair.getValue();
        return null;
    }
    public Set<Entry<K,V>> entrySet(){
        Set<Map.Entry<K,V>> set = new HashSet<Entry<K, V>>();
        for(LinkedList<MapEntry<K,V>> bucket:buckets){
            if(bucket == null) continue;
            for(MapEntry<K,V> mpair:bucket)
                set.add(mpair);
        }
        return set;
    }

    public static void main(String[] args){
        SimpleHashMap<String,String> map =
                new SimpleHashMap<String,String>();
        map.put("one","taotao");
        map.put("two","yuyu");
        map.put("three","jiajia");
        for(Map.Entry<String,String> value:map.entrySet()) {
            System.out.println(value.getKey()+" = "+value.getValue());
        }
    }
}
