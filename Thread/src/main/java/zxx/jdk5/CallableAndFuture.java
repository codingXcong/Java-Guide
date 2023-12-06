package zxx.jdk5;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.*;

/**
 * Callable和Runnable基本类似，其中Runnable没有返回结果，Callable可以返回结果。
 * 		Future接收Callable放回的结果。
 * @author zgc
 */
public class CallableAndFuture {
	public static void main(String[] args) throws Exception{
		FutureTask<Integer> futureTask = new FutureTask<>(() -> {
			System.out.println("当前线程:" + Thread.currentThread().getName());
			Integer i = 14 / 2;
			System.out.println("运行结果:" + i);
			return i;
		});
		new Thread(futureTask).start();
		Integer result = futureTask.get();
		System.out.println(result);



		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		//Future获取Callable执行得到的结果
		final Future<String> future = threadPool.submit(new Callable<String>() {
			@SuppressWarnings("static-access")
			@Override
			public String call() throws Exception {
				Thread.currentThread().sleep(10000);
				return "任务有返回值";
			}
		});
		
		System.out.println("等待获取结果-->10s之后拿到");
		
		/*try {
			String result = future.get();
			System.out.println("拿到的结果字符串为："+result);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String result = future.get();  
					System.out.println("拿到的结果字符串为："+result);
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		Calendar cal = new GregorianCalendar();
		int nowSecond = cal.get(Calendar.SECOND);
		while(true){
			Calendar cal1 = new GregorianCalendar();
			System.out.println(cal1.get(Calendar.SECOND)-nowSecond);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
