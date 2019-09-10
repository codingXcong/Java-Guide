package zxx;
/**
 * 创建线程的传统方式
 * @author zgc
 */
public class TraditionalThread {
	public static void main(String[] args) {
		Thread t = new Thread(){ 			//方式一：通过重写Thread的run方法实现多线程
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}
			}
		};
		t.start();
		
		Thread t1 = new Thread(
				new Runnable(){

					@Override
					public void run() {
						while(true){
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println(Thread.currentThread().getName());
						}
					}
					
				}
		);
		t1.start();
		
		
		new Thread(
				new Runnable() {
					@Override
					public void run() {
						System.out.println("runnable run");
					}
				}
		){
			public void run() {
				System.out.println("thread.run");
			}
		}.start();
	}
}
