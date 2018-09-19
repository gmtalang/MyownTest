package zhaojing.com.java_library.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * AUTHOR：Created by Administrator on 2018-09-19 09:35
 * EMAIL:  2910763715@qq.com
 */

public class ChannelCopy {
    private static final int SIZE = 1024;
    public static void main(String[] args) {

        try {
            FileChannel in = new FileInputStream("README.md").getChannel();
            FileChannel out = new FileOutputStream("read.md").getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(SIZE);
            while (in.read(buffer)!=-1){
                buffer.flip();//prepare for writing
                out.write(buffer);
                buffer.clear();//prepare for reading
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }finally {
        }
    }

}
