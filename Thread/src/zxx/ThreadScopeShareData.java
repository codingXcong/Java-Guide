package zxx;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 线程范围内的数据共享，即不同线程操作的数据互不影响
 * 		示例中，线程操作两个模块（A，B）
 * @author zgc
 */

public class ThreadScopeShareData {
	//通过一个Map实现数据和线程的绑定
	private static Map<Thread,Integer> threadData = new HashMap<Thread,Integer>();
	public static void main(String[] args) {
		for(int i=0; i<2; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					int data = new Random().nextInt();
					threadData.put(Thread.currentThread(), data);
					System.out.println(Thread.currentThread().getName()+
							" has put data : "+data);
					new A().get();
					new B().get();
				}
			}).start();
		}
	}
	
	static class A{
		public void get() {
			int data = threadData.get(Thread.currentThread());
			System.out.println("A from "+Thread.currentThread().getName()
					+" get data:"+data);
		}
	}
	
	static class B{
		public void get() {
			int data = threadData.get(Thread.currentThread());
			System.out.println("B from "+Thread.currentThread().getName()
					+" get data:"+data);
		}
	}
}

/*//没有共享的示例
public class ThreadScopeShareData {
	private static int data = 0;
	
	public static void main(String[] args) {
		for(int i=0; i<2; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName()+
							" has put data : "+data);
					new A().get();
					new B().get();
				}
			}).start();
		}
	}
	
	static class A{
		public void get() {
			System.out.println("A from "+Thread.currentThread().getName()
					+" get data:"+data);
		}
	}
	
	static class B{
		public void get() {
			System.out.println("B from "+Thread.currentThread().getName()
					+" get data:"+data);
		}
	}
}
*/