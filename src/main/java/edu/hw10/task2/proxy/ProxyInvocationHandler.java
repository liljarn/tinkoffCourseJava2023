package edu.hw10.task2.proxy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.hw10.task2.Cache;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class ProxyInvocationHandler implements InvocationHandler {
    private final Path pathToCachedMethods = Path.of("src/main/resources/hw10/cached");
    private final Map<Method, Map<List<Object>, Object>> map = new HashMap<>();
    private final Gson gson = new GsonBuilder().create();

    private final Object proxyObject;

    public ProxyInvocationHandler(Object proxyObject) {
        this.proxyObject = proxyObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            if (method.getAnnotation(Cache.class).persist()) {
                if (!Files.exists(pathToCachedMethods)) {
                    Files.createDirectory(pathToCachedMethods);
                }

                Path fileName = pathToCachedMethods.resolve(method.getName() + "_" + createFileName(method));
                if (!Files.exists(fileName)) {
                    Files.createFile(fileName);
                }

                Properties properties = new Properties();
                properties.load(Files.newInputStream(fileName));
                String gsonArgs = gson.toJson(args);
                String gsonString = properties.getProperty(gsonArgs);
                if (gsonString != null) {
                    return gson.fromJson(gsonString, method.getReturnType());
                }
                Object result = method.invoke(proxyObject, args);
                properties.put(gsonArgs, gson.toJson(result));
                properties.store(Files.newOutputStream(fileName), null);
                return result;
            }
            if (!map.containsKey(method) || !map.get(method).containsKey(Arrays.stream(args).toList())) {
                Object result = method.invoke(proxyObject, args);
                map.computeIfAbsent(method, meth -> new HashMap<>())
                    .put(Arrays.stream(args).toList(), result);
            }
            return map.get(method).get(Arrays.stream(args).toList());
        } else {
            return method.invoke(proxyObject, args);
        }
    }

    private String createFileName(Method method) {
        return Arrays.stream(method.getParameters()).map(Parameter::getName).collect(Collectors.joining("_"));
    }
}
