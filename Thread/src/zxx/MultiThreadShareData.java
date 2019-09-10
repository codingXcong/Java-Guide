package zxx;
/**
 * 多线程共享数据
 * @author zgc
 *
 */
public class MultiThreadShareData {
	public static void main(String[] args) {
		new Thread().start();
		new Thread().start();
	}
	
}

class ShareDate{
	private int j = 0; 
}
