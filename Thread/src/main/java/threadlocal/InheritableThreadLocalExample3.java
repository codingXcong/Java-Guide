package threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InheritableThreadLocalExample3 {

    public static ThreadLocal<Integer> wrongThreadLocal = new InheritableThreadLocal<>();
    // 线程数为1表示可以进行线程复用
    public static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws Exception{
        wrong();
        System.out.println("-------------------------------------");
        //right();
    }



    public static void wrong() throws InterruptedException {
        wrongThreadLocal.set(1);
        
        executorService.submit(() -> {
            wrongThreadLocal.set(11);
            System.out.println("sub thread : "+ wrongThreadLocal.get());
        });

        TimeUnit.SECONDS.sleep(1);

        System.out.println("主线程读取本地变量："+ wrongThreadLocal.get());
        wrongThreadLocal.set(2);
        System.out.println("主线程读取本地变量："+ wrongThreadLocal.get());

        executorService.submit(() -> {
            System.out.println("子线程读取本地变量: "+ wrongThreadLocal.get());
        });
    }
}
