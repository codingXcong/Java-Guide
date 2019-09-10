package zxx.jdk5;
import java.util.Random;
/**
 * 演示CompletionService
 * 	  CompeletionService用于提交一组Callable任务，其take方法返回已完成的一个Callable
 * 	     对应的Future对象
 * 		哪个任务先完成就获取它的运行结果
 * @author zgc
 */
import java.util.concurrent.*;
public class CompletionServiceTest {
	
	public static void main(String[] args) {
		ExecutorService threadPool2 =  Executors.newFixedThreadPool(10);
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool2);
		for(int i=1;i<=10;i++){
			final int seq = i;
			completionService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					Thread.sleep(new Random().nextInt(5000));
					return seq;
				}
			});
		}
		for(int i=0;i<10;i++){
			try {
				System.out.println(
						completionService.take().get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
