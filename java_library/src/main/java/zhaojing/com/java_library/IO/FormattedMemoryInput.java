package zhaojing.com.java_library.IO;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * AUTHOR：Created by Administrator on 2018-09-18 14:41
 * EMAIL:  2910763715@qq.com
 */

public class FormattedMemoryInput {
    public static void main(String[] args) throws IOException {

        DataInputStream in = new DataInputStream(new ByteArrayInputStream(BufferedInputFile
                .read("README.md").getBytes()));
        while (in.available() != 0)
            System.out.println((char) in.readByte());
    }
}
