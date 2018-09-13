package zhaojing.com.myowntest.common.test;

/**
 * AUTHOR：Created by Administrator on 2018-09-06 15:51
 * EMAIL:  2910763715@qq.com
 * initialize sequece
 */

public class Window {
    Book book_one =new Book(1);
    Window(){
        System.out.println("window");
        book_three = new Book(44);
    }

    Book book_two = new Book(2);
    Book book_three = new Book(3);

    public static void main(String[] args){

        new Window();

    }
}
