package zhaojing.com.java_library.IO;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * AUTHOR：Created by Administrator on 2018-09-19 10:27
 * EMAIL:  2910763715@qq.com
 */

public class CharToByteUtil {

    /***
     * char[] to byte[]
     * @param chars
     * @return
     */
    public static byte[] getBytes(char[] chars) {
        Charset cs = Charset.forName("UTF-8");
        CharBuffer cb = CharBuffer.allocate(chars.length);
        cb.put(chars);
        cb.flip();
        ByteBuffer bb = cs.encode(cb);
        return bb.array();
    }

    /***
     * byte[]  to char[]
     * @param bytes
     * @return
     */
    public static char[] getChars(byte[] bytes){
        Charset cs = Charset.forName("UTF-8");
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes);
        bb.flip();
        CharBuffer cb = cs.decode(bb);
        return cb.array();
    }

    /***
     * char to byte[]
     * @param c
     * @return
     */
    public static  byte[] charToByte(char c){
        byte[] b =new byte[2];
        b[0]=(byte)((c & 0xFF00)>>8);
        b[1]=(byte)(c&0xFF);
        return b;
    }

    /***
     * byte to char
     * @param b
     * @return
     */
    public static char byteToChar(byte[] b){
        char c =(char) (((b[0] & 0xFF) << 8) | (b[1] & 0xFF));
        return c;
    }
}
