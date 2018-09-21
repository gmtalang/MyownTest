package zhaojing.com.java_library;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.LinkedList;

/**
 * AUTHOR：Created by Administrator on 2018-09-18 10:53
 * EMAIL:  2910763715@qq.com
 */

class VeryBig{
    private static final int SIZE = 10000;
    private long[] la = new long[SIZE];
    private String ident;
    public VeryBig(String id){
        ident = id;
    }
    public String toString(){
        return ident;
    }

    protected void finalize(){
        System.out.println("finalize " + ident);
    }
}
public class References {
    private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<>();
    public static void checkQueue(){
        Reference<? extends VeryBig> inq = rq.poll();
        if(inq != null){
            // contain null object
            System.out.println("in queue : " + inq.get());
        }else{
            System.out.println(" created reference is null");
        }
    }

    public static void main(String[] args){
        int size = 1000;
        if(args.length > 0)
//            size = new Integer(args[0]);
            size = Integer.valueOf(args[0]);
        LinkedList<SoftReference<VeryBig>> sa =
                new LinkedList<>();
        for(int i=0;i<size;i++){
            sa.add(new SoftReference<VeryBig>(new VeryBig("Soft "+i),rq));
            System.out.println("just created" + sa.getLast());
            checkQueue();
        }


        LinkedList<WeakReference<VeryBig>> wa =
                new LinkedList<>();
        for(int i=0;i<size;i++){
            wa.add(new WeakReference<VeryBig>(new VeryBig("weak "+i),rq));
            System.out.println("just created : "+wa.getLast());
            checkQueue();
        }
        SoftReference<VeryBig> s = new SoftReference<VeryBig>(new VeryBig("soft"));
        WeakReference<VeryBig> w = new WeakReference<VeryBig>(new VeryBig("weak"));
        System.gc();
        LinkedList<PhantomReference<VeryBig>> pa = new LinkedList<>();
        for(int i=0;i < size;i++){
            pa.add(new PhantomReference<VeryBig>(new VeryBig("phantom "+ i),rq));
            System.out.println("just created :"+pa.getLast());
            checkQueue();
        }
    }

}
