package com.example.annotation;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018-03-18.
 * 编写一个注解处理器
 */
public class AnnotationController {

    public static void main(String[] args){
        traceDefineAndroid(ApplicationAnnotation.class);
    }

    private static void traceDefineAndroid(Class<?> cls){
        for(Method m:cls.getDeclaredMethods()){
            DefineAndroid defineAndroid=m.getAnnotation(DefineAndroid.class);
            System.out.println("输出应用在函数上的对应注解元素: id="+defineAndroid.id()+
                    "  description="+defineAndroid.description());
        }
    }
}
