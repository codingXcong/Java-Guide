package threadlocal;

import java.util.concurrent.*;

public class InheritableThreadLocalExample4 {

    public static ThreadLocal<Integer> wrongThreadLocal = new InheritableThreadLocal<>();

    // 线程数为1表示可以进行线程复用
    public static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws Exception{
        wrong();
    }

    public static void wrong() throws InterruptedException {
        // 模拟第一个请求
        wrongThreadLocal.set(27);
        System.out.println("请求1主线程："+ wrongThreadLocal.get());
        executorService.submit(() -> {
            System.out.println("sub thread : "+Thread.currentThread().getName()+" "+ wrongThreadLocal.get());
            wrongThreadLocal.set(37);
            System.out.println("sub thread : "+Thread.currentThread().getName()+" "+wrongThreadLocal.get());
        });
        wrongThreadLocal.remove();

        // 模拟第二个请求
        TimeUnit.SECONDS.sleep(1);
        wrongThreadLocal.set(99);
        System.out.println("请求2主线程："+ wrongThreadLocal.get());
        executorService.submit(() -> {
            System.out.println("sub thread: "+Thread.currentThread().getName()+" "+ wrongThreadLocal.get());
        });
    }
}
