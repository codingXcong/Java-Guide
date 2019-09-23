package thread.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author zhangguicong
 * @date 2019-09-23
 */
public class MyClassLoaderTest {
    public static void main(String[] args) throws Exception {
        //testMyClassLoader();
        testBrokerDelegateClassLoader();
    }

    private static void testMyClassLoader()
            throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
                   InvocationTargetException {
        MyClassLoader classLoader = new MyClassLoader("/Users/classloader", null);
        Class<?> aClass = classLoader.loadClass("thread.classloader.Hello");
        System.out.println(aClass.getClassLoader());

        Object hello = aClass.newInstance();
        System.out.println(hello);

        Method method = aClass.getMethod("hello", String.class);
        String result = (String) method.invoke(hello, "tomcat");
        System.out.println("Result:"+result);
    }

    private static void testBrokerDelegateClassLoader()
            throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException,
                   InvocationTargetException {
        BrokerDelegateClassLoader classLoader = new BrokerDelegateClassLoader();
        Class<?> aClass = classLoader.loadClass("thread.classloader.Hello");
        System.out.println(aClass.getClassLoader());

        Object hello = aClass.newInstance();
        System.out.println(hello);

        Method method = aClass.getMethod("hello", String.class);
        String result = (String) method.invoke(hello, "tomcat");
        System.out.println("Result:"+result);
    }
}
