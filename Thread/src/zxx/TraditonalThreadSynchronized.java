package zxx;
/**
 * 线程基于synchronize的同步
 * @author zgc
 */
public class TraditonalThreadSynchronized {
	public static void main(String[] args) {
		Thread t = new Thread(
				new Runnable(){
					public void run() {
						Outputer output = new Outputer();
						while(true){
							output.output2("zhangguicong");
						}
					};
				}
		);
		t.start();
		Thread t1 = new Thread(
				new Runnable(){
					public void run() {
						Outputer output = new Outputer();
						while(true){
							output.output2("hongmanying");
						}
					};
				}
				);
		t1.start();
	}
	
	static class Outputer{
		public void output(String name){  //线程不安全的
			int len = name.length();
			for(int i=0;i<len;i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		public synchronized void output1(String name){  //线程安全的
			int len = name.length();
			for(int i=0;i<len;i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		public void output2(String name){  //线程安全的
			int len = name.length();
			synchronized (Outputer.class) 
			{
				for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
		}
	}
}
