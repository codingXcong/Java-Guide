package demo;

public class ReInterrupt implements Runnable{

	@Override
	public void run() {
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			//e.printStackTrace();
			Thread.currentThread().interrupt();  //恢复中断
		}finally{
			System.out.println(Thread.currentThread().getName()+"睡了5秒");
		}
	}
	
	public static void main(String[] args) {
		System.out.println("main");
		ReInterrupt target = new ReInterrupt();
		Thread t = new Thread(target, "子线程");
		t.start();
		t.interrupt();
	}

}
