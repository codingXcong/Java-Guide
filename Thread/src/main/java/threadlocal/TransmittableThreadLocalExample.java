package threadlocal;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 使用ttl的使用，要配合TtlExecutors一起使用，不然会造成串单
 */
public class TransmittableThreadLocalExample {

    public static ThreadLocal<Stu> threadLocal = new TransmittableThreadLocal<>();
    public static ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(1));

    public static void main(String[] args) throws InterruptedException {
        /*threadLocal.set(new Stu("zgc",18));
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
        System.out.println("主线程读取本地变量"+threadLocal.get());*/

        testChuandan();

    }

    public static TransmittableThreadLocal<Integer> ttl = new TransmittableThreadLocal<>();
    public static ExecutorService executorService1 = Executors.newFixedThreadPool(5);
    public static void testChuandan() {
        for (int i =0 ; i<10; i++) {
            ttl.set(i);
            System.out.println("main:"+ttl.get());
            executorService1.submit(() -> {
                System.out.println("sub: "+ttl.get());
            });
            /*ThreadUtil.execAsync(() -> {
                System.out.println("sub: "+ttl.get());
            });*/
        }
    }
}
