//: annotations/database/Uniqueness.java
// Sample of nested annotations
package com.example.annotation.database;

public @interface Uniqueness {
  Constraints constraints()
    default @Constraints(unique=true);
} ///:~
