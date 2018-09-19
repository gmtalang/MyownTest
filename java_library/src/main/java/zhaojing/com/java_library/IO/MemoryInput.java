package zhaojing.com.java_library.IO;

import java.io.IOException;
import java.io.StringReader;

/**
 * AUTHOR：Created by Administrator on 2018-09-18 14:36
 * EMAIL:  2910763715@qq.com
 */

public class MemoryInput {

    public static void main(String[] args) throws IOException {
        StringReader in = new StringReader(BufferedInputFile.read("README.md"));
        int c;
        while ((c = in.read()) != -1) System.out.println((char) c);

    }
}
