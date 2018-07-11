package zhaojing.com.bluetoothlibrary.utils;

import java.util.Random;

/**
 * Created by zhaojing on 2018-06-20.
 */

public class RandomUtils {

    private static Random mRandom;
    public static double randFloat(){
        if(mRandom==null){
            mRandom = new Random();
            mRandom.setSeed(System.currentTimeMillis());
        }
        return mRandom.nextDouble();
    }
}
