package zxx;

import java.util.Random;


/**
 * 利用ThreadLocal类实现线程范围内的数据共享
 * 		多个数据
 * @author zgc
 *
 */

//比下面的方式更加优雅
public class ThreadLocalTest2 {
	//private static ThreadLocal<MyThreadScopeData> myThreadScopeData = 
		//new ThreadLocal<MyThreadScopeData>();
	public static void main(String[] args) {
		for(int i=0; i<2; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					int data = new Random().nextInt();
					MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
					myData.setName("name"+data);
					myData.setAge(data);
					System.out.println(Thread.currentThread().getName()+
							" put data:"+data);
					new A().get();
					new B().get();
				}
			}).start();
		}
	}
	
	static class A{
		public void get() {
			MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
			System.out.println("A from " + Thread.currentThread().getName() 
					+ " getMyData: " + myData.getName() + "," +
					myData.getAge());
		}
	}
	
	static class B{
		public void get() {
			MyThreadScopeData myData = MyThreadScopeData.getThreadInstance();
			System.out.println("B from " + Thread.currentThread().getName() 
					+ " getMyData: " + myData.getName() + "," +
					myData.getAge());
		}
	}
}

//将类本身绑定到当前线程
class MyThreadScopeData{
	
	private String name;
	private int age;
	private static ThreadLocal<MyThreadScopeData> map= new ThreadLocal
			<MyThreadScopeData>();
	
	public static MyThreadScopeData getThreadInstance(){ //因为这里，变量只存在当前线程，所以不存在线程安全问题
		MyThreadScopeData instance = map.get();
		if(instance == null){       
			instance = new MyThreadScopeData();
			map.set(instance);
		}
		return instance;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}

/*//这种处理方式不够优雅
public class ThreadLocalTest2 {
	private static ThreadLocal<MyThreadScopeData> myThreadScopeData = 
			new ThreadLocal<MyThreadScopeData>();
	public static void main(String[] args) {
		for(int i=0; i<2; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					int data = new Random().nextInt();
					MyThreadScopeData myData = new MyThreadScopeData();
					myData.setName("name" + data);
					myData.setAge(data);
					myThreadScopeData.set(myData);
					System.out.println(Thread.currentThread().getName()+
							" put data:"+data);
					new A().get();
					new B().get();
				}
			}).start();
		}
	}
	
	static class A{
		public void get() {
			MyThreadScopeData myData = myThreadScopeData.get();;
			System.out.println("A from " + Thread.currentThread().getName() 
					+ " getMyData: " + myData.getName() + "," +
					myData.getAge());
		}
	}
	
	static class B{
		public void get() {
			MyThreadScopeData myData = myThreadScopeData.get();;
			System.out.println("B from " + Thread.currentThread().getName() 
					+ " getMyData: " + myData.getName() + "," +
					myData.getAge());
		}
	}
}

class MyThreadScopeData{
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}*/
