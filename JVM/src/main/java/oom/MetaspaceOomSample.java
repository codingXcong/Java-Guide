package oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zhangguicong
 * @date 2019-12-23
 */
public class MetaspaceOomSample {

    public static void main(String[] args) {
        dynamicCreateClass();
    }

    public static void dynamicCreateClass () {
        while(true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Dog.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    if (method.getName().equals("eat")) {
                        System.out.println("play with dog before dog eat.");
                    }
                    return methodProxy.invokeSuper(o,objects);
                }
            });

            Dog dog = ((Dog) enhancer.create());
            dog.eat();
        }
    }

    static class Dog {
        public void eat () {
            System.out.println("dog is eating bone");
        }
    }
}
