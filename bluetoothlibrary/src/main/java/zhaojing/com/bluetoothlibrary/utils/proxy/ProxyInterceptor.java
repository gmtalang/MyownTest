package zhaojing.com.bluetoothlibrary.utils.proxy;

import java.lang.reflect.Method;

/**
 * Created by zhaojing on 2016/9/18.
 * 代理拦截器
 */
public interface ProxyInterceptor {
    boolean onIntercept(Object object, Method method, Object[] args);
}
