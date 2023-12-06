package zxx.jdk5;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁的测试
 * 	  只有读锁与读锁不会互斥
 * @author zgc
 *
 */
public class ReadWriteLockTest {
	public static void main(String[] args) {
		final Queue3 q3 = new Queue3();
		for(int i=0;i<3;i++)
		{
			new Thread(){
				public void run(){
					while(true){
						q3.get();						
					}
				}
				
			}.start();

			new Thread(){
				public void run(){
					while(true){
						q3.set(new Random().nextInt(10000));
					}
				}			
				
			}.start();
		}
	}
}

class Queue3 {
	private Object data = null;	//共享的数据
	ReadWriteLock rwl = new ReentrantReadWriteLock(); //创建一个读写锁
	
	public void get(){
		rwl.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"准备读数据啦。");
			Thread.sleep((long) (Math.random()*1000));
			System.out.println(Thread.currentThread().getName()+"读到数据了："+data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			rwl.readLock().unlock();
		}
	}
	
	//我们要控制到在写的过程当中不能去读
	public void set(Object data){
		rwl.writeLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"准备写数据啦。");
			Thread.sleep((long) (Math.random()*1000));
			this.data = data;
			System.out.println(Thread.currentThread().getName()+"写入了数据："+data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			rwl.writeLock().unlock();
		}
	}
}
