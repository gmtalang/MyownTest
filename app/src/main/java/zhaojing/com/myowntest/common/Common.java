package zhaojing.com.myowntest.common;

import android.os.Environment;

/**
 * Created by Administrator on 2018-04-08.
 */
public class Common {



    public static String getFilePath(){
            String pathFile= Environment.getExternalStorageDirectory().getPath()+"/guomei"+System.currentTimeMillis()+".amr";

        return pathFile;
    }
}
