package basic;

import java.util.concurrent.*;

/**
 * @author codingxcong
 * @date 2023-12-02
 */
public class CompletableFutureDemo {

    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
            200,
            10,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(100000),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) throws Exception {
        //createCompletableFuture();
        // 线程结果感知和处理异常
        //testResultHandle();
        // 线程串行化方法
        // testSerial();
        // 两任务组合 - 都要完成
        // testTwoTaskBoth();
        // 两个任务 - 一个完成
        // testOneTaskFinish();
        // 多任务组合
        testMultiTask();
    }

    /**
     * 多任务组合
     * allOf: 等待所有任务完成
     * anyOf: 只要有一个任务完成
     */
    private static void testMultiTask() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务一线程开始:" + Thread.currentThread().getName());
            int i = 12 / 2;
            System.out.println("任务一运行结束...." + i);
            return i;
        }, executor);
        CompletableFuture<Object> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务二线程开始:" + Thread.currentThread().getName());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务二运行结束....");
            return "hello";
        }, executor);
        CompletableFuture<Object> future03 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务三线程开始:" + Thread.currentThread().getName());

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务三运行结束....");
            return "hello2";
        }, executor);

        /*CompletableFuture<Void> allOf = CompletableFuture.allOf(future01, future02, future03);
        allOf.get();//等待所有任务完成
        System.out.println("返回数据:");*/

        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(future01, future02, future03);
        anyOf.get();//等待其中之一任务完成
        System.out.println("返回数据:");


    }

    /**
     * 两个任务 - 其中一个完成
     * runAfterEither：两个任务有一个执行完成，不需要获取 future 的结果，处理任务，也没有返回值。
     * acceptEither：两个任务有一个执行完成，获取它的返回值，处理任务，没有新的返回值。
     * applyToEither：两个任务有一个执行完成，获取它的返回值，处理任务并有新的返回值。
     */
    private static void testOneTaskFinish() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务一线程开始:" + Thread.currentThread().getName());
            int i = 12 / 2;
            System.out.println("任务一运行结束...." + i);
            return i;
        }, executor);
        CompletableFuture<Object> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务二线程开始:" + Thread.currentThread().getName());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务二运行结束....");
            return "hello";
        }, executor);

        /*future01.runAfterEitherAsync(future02,() -> {
            System.out.println("任务三线程开始:" + Thread.currentThread().getName());
        },executor);
        System.out.println("返回数据:");*/

        /*future02.acceptEitherAsync(future01,res ->{
            System.out.println("任务三线程开始:" + Thread.currentThread().getName()+"拿到上次任务的结果:"+res);
        },executor);
        System.out.println("返回数据:");*/

        CompletableFuture<String> future = future02.applyToEitherAsync(future01, res -> {
            System.out.println("任务三线程开始:" + Thread.currentThread().getName() + "拿到上次任务的结果:" + res);
            return res + "t3";
        }, executor);
        System.out.println("返回数据:"+future.get());
    }

    /**
     * 两任务组合 - 都要完成
     * runAfterBothAsync: 不获取结果并处理新任务
     * thenAcceptBothAsync: 获取结果并处理新任务
     * thenCombineAsync: 获取结果并获得新任务的结果
     */
    private static void testTwoTaskBoth() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future01 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务一线程开始:" + Thread.currentThread().getName());
            int i = 12 / 2;
            System.out.println("任务一运行结束...." + i);
            return i;
        }, executor);
        CompletableFuture<String> future02 = CompletableFuture.supplyAsync(() -> {
            System.out.println("任务二线程开始:" + Thread.currentThread().getName());
            System.out.println("任务二运行结束....");
            return "hello";
        }, executor);

        /*future01.runAfterBothAsync(future02,() -> {
            System.out.println("任务三开始...");
        });
        System.out.println("返回数据:");*/

        /*future01.thenAcceptBothAsync(future02, (res1, res2) -> {
            System.out.println("任务一返回值:"+res1+"任务二返回值:"+res2);
        });
        System.out.println("返回数据:");*/

        CompletableFuture<String> future = future01.thenCombineAsync(future02, (res1, res2) -> {
            System.out.println("任务一返回值:" + res1 + "，任务二返回值:" + res2);
            return res1 + (String) res2;
        }, executor);
        System.out.println("返回数据:"+future.get());
    }

    /**
     * 线程串行化方法
     * thenRun：继续执行，不接受上一个任务的返回结果,自己执行完没有返回结果
     * thenAccept：继续执行，接受上一个任务的返回结果,自己执行完没有返回结果
     * thenApply：继续执行，接受上一任务的返回结果，并且自己的返回结果也被下一个任务所感知
     */
    private static void testSerial() {
        /*CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getName());
            int i = 10 / 2;
            System.out.println("运行结果..." + i);
        }, executor).thenRunAsync(() -> {
            System.out.println("启动第二个任务");
        });
        System.out.println("【主】返回数据：");*/

        /*CompletableFuture<Void> uCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getName());
            int i = 10 / 2;
            System.out.println("运行结果..." + i);
            return i;
        }, executor).thenAcceptAsync(res -> {
            System.out.println("任务二启动了..."+"拿到了上一步的结果:"+res);
        });
        System.out.println("返回数据：");*/

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程:" + Thread.currentThread().getName());
            int i = 10 / 2;
            System.out.println("运行结果...." + i);
            return i;
        }, executor).thenApplyAsync(res -> {
            System.out.println("任务二启动了..." + "拿到了上一步的结果:" + res);
            return res*2;
        }, executor);

        try {
            Integer integer = future.get();
            System.out.println("返回数据:"+integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    /**
     * 线程结果感知和处理
     *
     */
    private static void testResultHandle() {
        // 方式一：使用handleAsync方法，推荐
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getName());
            int i = 10 / 2;
            System.out.println("运行结果..." + i);
            return i;
        }, executor).handleAsync((res, exception) -> {
            if (exception != null) {
                System.out.println("出现异常了..." + exception.getMessage());
                return -1;
            }
            return res;
        });


        // 方式二：使用whenCompleteAsync+exceptionally
        /*CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getName());
            int i = 10 / 0;
            System.out.println("运行结果..." + i);
            return i;
        }, executor).whenCompleteAsync((res, exception) -> {
            // whenCompleteAsync方法虽然能得到异常信息，但无法修改返回结果
            // res, exception: res是结果，exception是异常
            System.out.println("异步任务完成....感知到返回值为:"+res+"异常:"+exception);
        }).exceptionally(throwable -> {
            // exceptionally方法可以感知异常，并返回自定义默认值
            return 0;
        });*/

        try {
            Integer result = integerCompletableFuture.get();
            System.out.println("result..." + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    public static void createCompletableFuture() {
        // 方式一： runAsync 无线程返回值
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("当前线程："+Thread.currentThread().getName());
            int i = 10 / 2;
            System.out.println("运行结果..."+i);
        }, executor);

        // 方式二：supplyAsync 带线程返回值
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getName());
            int i = 10 / 2;
            System.out.println("运行结果..." + i);
            return i;
        }, executor);
        try {
            Integer result = integerCompletableFuture.get();
            System.out.println("result..." + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

}
