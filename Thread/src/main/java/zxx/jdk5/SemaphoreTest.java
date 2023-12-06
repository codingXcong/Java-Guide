package zxx.jdk5;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
/**
 * 测试Semaphore类
 *    1、Semaphore，计数信号量。可以控制同时访问资源的线程个数。
 *    2、当个信号量的Semaphore对象可以实现互斥锁的功能，并且可以由一个线程获得锁，再由另外
 *      一个线程释放锁，这可应用于死锁恢复的一些场合。
 * @author zgc
 *
 */
public class SemaphoreTest {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final  Semaphore sp = new Semaphore(3);
		for(int i=0;i<10;i++){
			Runnable runnable = new Runnable(){
					public void run(){
					try {
						//从此信号量获取一个许可,若没有许可能够获得则线程将阻塞
						sp.acquire();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.out.println("线程" + Thread.currentThread().getName() + 
							"进入，当前已有" + (3-sp.availablePermits()) + "个并发");
					try {
						Thread.sleep((long)(Math.random()*10000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程" + Thread.currentThread().getName() + 
							"即将离开");	
					//释放信号量
					sp.release();
					//下面代码有时候执行不准确，因为其没有和上面的代码合成原子单元
					System.out.println("线程" + Thread.currentThread().getName() + 
							"已离开，当前已有" + (3-sp.availablePermits()) + "个并发");					
				}
			};
			service.execute(runnable);			
		}
	}

}
