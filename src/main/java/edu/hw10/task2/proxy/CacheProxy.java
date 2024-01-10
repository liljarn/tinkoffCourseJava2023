package edu.hw10.task2.proxy;

import java.lang.reflect.Proxy;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CacheProxy {
    public static <T> T create(T tObj, Class<T> tClass) {
        return tClass.cast(Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[] {tClass},
            new ProxyInvocationHandler(tObj)
        ));
    }
}
