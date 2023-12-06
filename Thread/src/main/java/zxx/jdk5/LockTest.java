package zxx.jdk5;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class LockTest {
	public static void main(String[] args) {
		final Outputer output = new Outputer();
		Thread t = new Thread(
				new Runnable(){
					public void run() {
						
						while(true){
							output.output1("zhangguicong");
						}
					};
				}
		);
		t.start();
		Thread t1 = new Thread(
				new Runnable(){
					public void run() {
						//Outputer output = new Outputer();
						while(true){
							output.output1("hongmanying");
						}
					};
				}
				);
		t1.start();
	}
	
	static class Outputer{
		
		Lock lock = new ReentrantLock();
		public void output1(String name){  //线程安全的
			lock.lock();
			int len = name.length();
			for(int i=0;i<len;i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
			lock.unlock();
		}
		
	}
}
