package jol;

import org.openjdk.jol.info.ClassLayout;

public class JOLExample2 {
    static A a;
    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(5000);
        a=new A();
        System.out.println("before lock");
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        //锁住a对象
        synchronized (a){
            System.out.println("hello");
            System.out.println(ClassLayout.parseInstance(a).toPrintable());
        }
        System.out.println("after lock");
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }
}

