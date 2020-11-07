package threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TransmittableThreadLocalExample {

    public static ThreadLocal<Stu> threadLocal = new TransmittableThreadLocal<>();
    public static ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(10));

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set(new Stu("zgc",18));
        System.out.println("主线程读取本地变量"+threadLocal.get());

        executorService.submit(() -> {
            System.out.println("子线程读取本地变量："+threadLocal.get());
        });

        TimeUnit.SECONDS.sleep(1);

        threadLocal.get().setAge(19);
        System.out.println("主线程读取本地变量"+threadLocal.get());

        executorService.submit(() -> {
            System.out.println("子线程读取本地变量: "+threadLocal.get());
            threadLocal.get().setAge(20);
            System.out.println("子线程读取本地变量 : "+threadLocal.get());
        });

        TimeUnit.SECONDS.sleep(1);
        System.out.println("主线程读取本地变量"+threadLocal.get());

    }
}
