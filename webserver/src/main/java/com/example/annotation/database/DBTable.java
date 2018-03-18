//: annotations/database/DBTable.java
package com.example.annotation.database;
import java.lang.annotation.*;

@Target(ElementType.TYPE) // Applies to classes only
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {
  public String name() default "";//数据库表名
} ///:~
