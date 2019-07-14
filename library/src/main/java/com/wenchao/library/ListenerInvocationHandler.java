package com.wenchao.library;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author wenchao
 * @date 2019/7/14.
 * @time 15:37
 * description：
 */
public class ListenerInvocationHandler implements InvocationHandler {

    /**
     * 拦截的对象
     */
    private Object target;
    /**
     * 需要拦截额方法
     */
    private HashMap<String, Method> methodHashMap = new HashMap<>();

    public ListenerInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (target != null) {
            final String name = method.getName();
            method = methodHashMap.get(name);
            if (method != null) {
                if (method.getGenericParameterTypes().length == 0) {
                    return method.invoke(target);
                }
                return method.invoke(target, args);
            }
        }
        return null;
    }

    public void addMethod(String methodName, Method method) {
        methodHashMap.put(methodName, method);
    }
}
