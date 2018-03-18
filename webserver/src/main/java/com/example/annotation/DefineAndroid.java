package com.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Administrator on 2018-03-18.
 * 自定义一个注解
 * 标记注解：空元素
 */
@Target(ElementType.METHOD)//注解运行于方法域中
@Retention(RetentionPolicy.RUNTIME)//注解运行的级别
public @interface DefineAndroid {
    public int id() default -1;
    public String description() default "自定义一个注解";

}
