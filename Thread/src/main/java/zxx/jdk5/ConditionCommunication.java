package zxx.jdk5;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Condition对象封装了Object的wait和notify方法。
 * @author zgc
 */
public class ConditionCommunication {
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
	
}

class Business {
	private boolean bShouldSub = true;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	public void sub(int i){
		lock.lock();
		try{
			while(!bShouldSub){		//这里也能使用if,但是使用while更好，因为会出现一些伪唤醒的情况
					try {
						condition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			
			}
			for(int j=1;j<=10;j++){
				System.out.println("sub thread sequence of " + j + ",loop of " + i);
			}
			bShouldSub = false;
			condition.signal();
		}finally{
			lock.unlock();
		}
	}
	
	public void main(int i){
		lock.lock();
		try{
			while(bShouldSub){
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for(int j=1;j<=100;j++){
				System.out.println("main thread sequence of " + j + ",loop of " + i);
			}
			bShouldSub = true;
			condition.signal();
		}finally{
			lock.unlock();
		}
	}
	
}