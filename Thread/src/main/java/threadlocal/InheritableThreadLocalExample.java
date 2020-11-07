package threadlocal;

import java.util.concurrent.TimeUnit;

public class InheritableThreadLocalExample {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal();
    private static InheritableThreadLocal<Integer> iThreadLocal = new InheritableThreadLocal<>();

    /**
     * 输出结果：
     * main: wrongThreadLocal = 12345678, iThreadLocal= 87654321
     * Thread-0:null, iThreadLocal= 87654321
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        threadLocal.set(12345678);
        iThreadLocal.set(87654321);
        System.out.println(Thread.currentThread().getName() + ": wrongThreadLocal = " +threadLocal.get()
                + ", iThreadLocal= " +iThreadLocal.get());
        new Thread(()-> System.out.println(Thread.currentThread().getName() + ":" +threadLocal.get()+
                ", iThreadLocal= " +iThreadLocal.get())).start();
        TimeUnit.SECONDS.sleep(1);
    }
}
