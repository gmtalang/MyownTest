package zhaojing.com.lib.reflection;

/**
 * AUTHOR：Created by Administrator on 2018-07-15 15:23
 * EMAIL:  2910763715@qq.com
 */

public class Car {

    private String name;
    private int num;
    public Color mColor;
    public enum Color{
        RED,
        BLACK,
        BLUE
    }

    public Car(){
        System.out.println(" this class if Car");
    }


    public void drive(){

    }
}
