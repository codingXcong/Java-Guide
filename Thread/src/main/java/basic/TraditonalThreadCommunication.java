package basic; /**
 * 编程实现：子线程循环10次，接着主线程循环100次，接着又回到子线程循环10次，然后有回到主线程
 * 执行100次。如此循环50次。
 * @author zgc
 *
 */

/**
 * 线程tips:wait方法和notify方法必须写在synchronized代码块或方法中，
 * 		  且synchronized用哪个对象上的锁，就用那个对象调用wait和notify方法。
 * @author zgc
 *
 */
public class TraditonalThreadCommunication {
	
	public static void main(String[] args) {
	    final Business business = new Business();
		new Thread(
				new Runnable() {
					@Override
					public void run() {
					
						for(int i=1;i<=50;i++){
							business.sub(i);
						}
						
					}
				}
		).start();
		
		for(int i=1;i<=50;i++){
			business.main(i);
		}
	}
	
	
	
	//以下方式没有实现通信
	/*public static void main(String[] args) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=1; i<=50; i++){
					synchronized (TraditonalThreadCommunication.class) {
						for(int j=1; j<=10; j++){
							System.out.println("sub thread sequence of " + j + ",loop of " + i);					
						}
					}
				}
			}
		});
		t.start();
		for(int i=1;i<=50;i++){
			synchronized (TraditonalThreadCommunication.class) {
			for(int j=1; j<=100; j++){
				System.out.println("main thread sequence of " + j + ",loop of " + i);
			}
			}
		}
	}*/
}

class Business {
	private boolean bShouldSub = true;
	
	public synchronized void sub(int i){
		while(!bShouldSub){		//这里也能使用if,但是使用while更好，因为会出现一些伪唤醒的情况
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		
		}
		for(int j=1;j<=10;j++){
			System.out.println("sub thread sequence of " + j + ",loop of " + i);
		}
		bShouldSub = false;
		this.notify();
	}
	
	public synchronized void main(int i){
		while(bShouldSub){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int j=1;j<=100;j++){
			System.out.println("main thread sequence of " + j + ",loop of " + i);
		}
		bShouldSub = true;
		this.notify();
	}
	
}