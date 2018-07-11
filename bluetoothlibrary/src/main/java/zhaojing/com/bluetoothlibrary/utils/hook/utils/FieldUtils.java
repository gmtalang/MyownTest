package zhaojing.com.bluetoothlibrary.utils.hook.utils;

import java.lang.reflect.Field;

import zhaojing.com.bluetoothlibrary.utils.StringUtils;

/**
 * Created by zhaojing on 2018-06-20.
 */

public class FieldUtils {

    public static Field getDeclaredField(final Class<?> cls,final String fieldName,final boolean forceAccess){

        Validate.isTrue(cls!=null,"The class must not be null");
        Validate.isTrue(StringUtils.isNotBlank(fieldName),"The field name must not be blank/empty");
        try {
            final Field field=cls.getDeclaredField(fieldName);
            if(!MemberUtils.isAccessible(field)){
                if(forceAccess){
                    field.setAccessible(true);
                }else{
                    return null;
                }

            }

            return field;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;
    }
}
