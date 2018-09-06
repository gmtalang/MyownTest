package zhaojing.com.lib;

import java.lang.reflect.Modifier;

import zhaojing.com.lib.reflection.Car;
import zhaojing.com.lib.reflection.Outer;

/**
 * 3 kinds of class get
 */
public class myClass {

    public static void  main(String[] args){

        //1.object.getClass

        Car mCar=new Car();
        Class clazz=mCar.getClass();
        String clazz_name1=clazz.getName();
        String clazz_name2=clazz.getSimpleName();
        String clazz_name3=clazz.getCanonicalName();

        //2. .Class flags

        Class carClazz=Car.class;
        Class intClazz=int.class;

        //3.class.forName()
        try {
            Class clz = Class.forName("zhaojing.com.lib.reflection.Car");
            Class floatClazz = float.class;
            Class intClazz1=new int[]{}.getClass();
            Class carClazz1= new Car[]{}.getClass();
            System.out.println("car array is name: "+carClazz1);

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        //4. static inner class
        Class clz= Outer.Inner.class;
        System.out.println("inner class name: "+clz.getName());
        System.out.println("inner class a simple name: "+clz.getSimpleName());

        //5. array class
        Class clz1=new Outer.Inner[][][]{}.getClass();
        System.out.println("inner class name: "+clz1.getName());
        System.out.println("inner class a simple name: "+clz1.getSimpleName());

        //6.naming inner class
        Runnable run =new Runnable() {
            @Override
            public void run() {

            }
        };
        System.out.println("inner class is name: "+run.getClass().getName());
        System.out.println("inner class simple name: "+run.getClass().getSimpleName());

        //7. get class modifier
        System.out.println("modifier value: "+TestModifier.class.getModifiers());
        System.out.println("modifier : "+ Modifier.toString(TestModifier.class.getModifiers()));

    }


}
