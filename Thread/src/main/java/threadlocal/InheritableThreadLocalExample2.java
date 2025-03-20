package threadlocal;

import java.util.concurrent.*;

public class InheritableThreadLocalExample2 {

    public static ThreadLocal<Stu> wrongThreadLocal = new InheritableThreadLocal<>();
    public static ThreadLocal<Stu> rightThreadLocal = new MyInheritableThreadLocal<>();
    // 线程数为1表示可以进行线程复用
    public static ExecutorService executorService = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(),
            new CustomThreadFactory());

    public static void main(String[] args) throws Exception{
        wrong();
        System.out.println("-------------------------------------");
        //right();
    }

    public static void right() throws InterruptedException {
        rightThreadLocal.set(new Stu("zgc",27));

        executorService.submit(() -> {
            System.out.println("right sub thread : "+rightThreadLocal.get());
            rightThreadLocal.get().setAge(37);
            System.out.println("right sub thread : "+rightThreadLocal.get());
        });

        TimeUnit.SECONDS.sleep(1);

        System.out.println("right 主线程读取本地变量："+ rightThreadLocal.get());
        rightThreadLocal.get().setAge(99);
        System.out.println("right 主线程读取本地变量："+ rightThreadLocal.get());

        executorService.submit(() -> {
            // 这里依然输出Stu{name='zgc', age=37}， ITL在做主子线程传递的时候，是在创建子线程时复制过去，只要这个子线程不被销毁，那么在主线程对值进行的修改不会同步到子线程
            System.out.println("right 子线程读取本地变量: "+rightThreadLocal.get());
        });
    }

    //public static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void wrong() throws InterruptedException {
        wrongThreadLocal.set(new Stu("zgc",27));

        executorService.submit(() -> {
            System.out.println("sub thread : "+ wrongThreadLocal.get());
            wrongThreadLocal.get().setAge(37);
            System.out.println(wrongThreadLocal.get().hashCode());
            System.out.println("sub thread : "+ wrongThreadLocal.get());
        });

        TimeUnit.SECONDS.sleep(1);

        System.out.println("主线程读取本地变量（读取到了子线程修改后的数据）："+ wrongThreadLocal.get());
        wrongThreadLocal.get().setAge(99);
        System.out.println("主线程读取本地变量："+ wrongThreadLocal.get());
        System.out.println(wrongThreadLocal.get().hashCode());

        TimeUnit.SECONDS.sleep(1);

        executorService.submit(() -> {
            System.out.println("子线程读取本地变量（读取到了主线程修改后的变量）: "+ wrongThreadLocal.get());
            System.out.println("子线程名称：" + Thread.currentThread().hashCode());
        });
    }
}
