package demo;
/**
 * 多线程下，变量的不可见性（读脏数据）
 * @author zgc
 */
public class NoVisibility {
	public static boolean ready;
	public static int number;

	private static class ReaderThread extends Thread{

		@Override
		public void run() {
			while(!ready){
				Thread.yield();
			}
			System.out.println(number);
		}
	}
	public static void main(String[] args) {
		/*for(int i=0; i<1000; i++)
			new ReaderThread().start();
		number =42;
		ready = true; */
		T1 t = new T1();
		t.print();
		StringBuilder n = t.getMsg();
		n.append("ccc");
		System.out.println(n);
		t.print();
		
	}
}
