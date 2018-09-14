
package zhaojing.com.java_library;

/**
 * AUTHOR：Created by Administrator on 2018-09-13 10:17
 * EMAIL:  2910763715@qq.com
 */

public class AssociativeArray<K,V> {
    private Object[][] pairs;
    private int index;
    public AssociativeArray(int length){
        pairs = new Object[length][2];

    }


    public void put(K key,V value){
        if(index >= pairs.length){
//            throw new ArrayIndexOutOfBoundsException();
            pairs[index++]= new Object[]{key,value};
        }
    }


    public V get(K key){
        for(int i=0;i<index;i++){
            if(key.equals(pairs[i][0])){
                return (V)pairs[i][1];
            }

        }

        return null;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(int i =0;i<index;i++){
            result.append(pairs[i][0].toString());
            result.append(":");
            result.append(pairs[i][1].toString());
            if(i<index-1){
                result.append("\n");
            }
        }
        return result.toString();
    }

    public static void main(String[] args) throws ArrayIndexOutOfBoundsException{
        AssociativeArray<Integer,String> map = new AssociativeArray<Integer, String>(6);
        map.put(0,"blue");
        map.put(1,"white");
        map.put(2,"green");
        map.put(3,"red");
        map.put(4,"black");
        map.put(5,"orange");
    }
}
