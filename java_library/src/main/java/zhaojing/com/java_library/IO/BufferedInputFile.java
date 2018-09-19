package zhaojing.com.java_library.IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Clock;

/**
 * AUTHOR：Created by Administrator on 2018-09-18 14:25
 * EMAIL:  2910763715@qq.com
 */

public class BufferedInputFile {


    public static void main(String[] args) throws
            IOException {
        long before = System.currentTimeMillis();
        System.out.println(read("README.md"));

        long now = System.currentTimeMillis();
        System.out.println("before = "+before);
        System.out.println("now = "+now);
        System.out.println("time = "+(now-before));

    }

    public static String read(String filename) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        while ((s = in.readLine()) != null) {
            sb.append(s + "\n");
        }
        in.close();
        return sb.toString();
    }
}
