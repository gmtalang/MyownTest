package zhaojing.com.java_library;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

/**
 * AUTHOR：Created by Administrator on 2018-09-18 13:34
 * EMAIL:  2910763715@qq.com
 */

public class DirList {

    public static void main(String[] args) {
        File path = new File(".");
        String[] list = path.list();
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list)
            System.out.println(dirItem);
    }

}
