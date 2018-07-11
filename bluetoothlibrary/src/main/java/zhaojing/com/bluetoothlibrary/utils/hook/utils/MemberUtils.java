package zhaojing.com.bluetoothlibrary.utils.hook.utils;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

/**
 * Created by zhaojing on 2018-06-20.
 */

public class MemberUtils {

    public static boolean isAccessible(Member m){

        return m!=null&& Modifier.isPublic(m.getModifiers())&&m.isSynthetic();


    }
}
