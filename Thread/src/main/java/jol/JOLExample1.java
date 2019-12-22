package jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * https://blog.csdn.net/mashaokang1314/article/details/96096282
 */
public class JOLExample1 {
    static A a=new A();
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);

        System.out.println("before hash");
        //没有计算hashcode之前的对象头
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        //JVM计算hashcode
        /*System.out.println("jvm------0x"+Integer.toHexString(a.hashCode()));
        //当计算完hashcode之后，我们可以查看对象头的变化
        System.out.println("after hash");
        System.out.println(ClassLayout.parseInstance(a).toPrintable());*/
    }
}