package threadlocal;


public class ThreadLocalExample {
    /**
     * 输出结果：
     * Thread[Thread-0,5,main] get value t1
     * Thread[main,5,main] get value null
     * Thread[Thread-1,5,main]get value t2
     */
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        Thread t1 = new Thread(() -> {
            threadLocal.set("t1");
            System.out.println(Thread.currentThread() + " get value " + threadLocal.get());
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            threadLocal.set("t2");
            System.out.println(Thread.currentThread() + "get value " + threadLocal.get());
        });
        t2.start();

        System.out.println(Thread.currentThread() + " get value " + threadLocal.get());
    }
}
