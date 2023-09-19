package io.zgc.design.patterns.structural.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author codingxcong
 * @date 2023-09-13
 */
public class JdkStationProxy<T> implements InvocationHandler {

    // 被代理对象
    private T target;

    JdkStationProxy(T target) {
        this.target = target;
    }

    /**
     * 获取代理对象
     * @param t
     * @param <T>
     * @return
     */
    public static<T> T getProxy(T t) {
        Object o = Proxy.newProxyInstance(t.getClass().getClassLoader(), t.getClass().getInterfaces(), new JdkStationProxy(t));
        return (T) o;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("××××您正在使用车票代售点进行购票，每张票将会收取5元手续费！××××");
        Object result = method.invoke(target, args);
        System.out.println("××××欢迎您的光临，再见！××××\n");
        return result;
    }
}
