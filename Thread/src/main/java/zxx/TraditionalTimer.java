package zxx;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器
 * 		时间处理框架:quartz
 * @author zgc
 */
public class TraditionalTimer {
	static int count=0;
	public static void main(String[] args) {
		/*Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("每五秒炸你一次...");
			}
		}, 3000,5000);*/
		
		
		class MyTimerTask extends TimerTask{
			
			@Override
			public void run() {
				count = (count+1)%2;
				System.out.println("bombing!");
				new Timer().schedule(new MyTimerTask(),2000+2000*count);
			}
		}
		//间隔执行TimerTask,先是2秒然后4秒，再是2秒然后4秒，依次循环 
		new Timer().schedule(new MyTimerTask(), 2000);

		
		while(true){
			Calendar cal = new GregorianCalendar();
			System.out.println(cal.get(Calendar.SECOND));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
