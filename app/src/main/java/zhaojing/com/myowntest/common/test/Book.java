package zhaojing.com.myowntest.common.test;

/**
 * AUTHOR：Created by Administrator on 2018-09-06 15:23
 * EMAIL:  2910763715@qq.com
 */

public class Book {
    @Override
    protected void finalize() throws Throwable {
//        super.finalize();
        //  is to verify finalize()
        if(true){
            // perform
        }
    }


}
