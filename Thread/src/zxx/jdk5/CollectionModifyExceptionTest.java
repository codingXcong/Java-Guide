package zxx.jdk5;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
/**
 * 对于线程不安全的集合，在遍历集合的时候，移除集合中的元素，可能会抛出异常
 * @author zgc
 */
public class CollectionModifyExceptionTest {
	public static void main(String[] args) {
		Collection<User> users = new ArrayList<User>();
		users.add(new User("张三",28));	
		users.add(new User("李四",25));			
		users.add(new User("王五",31));
		Iterator<User> iu = users.iterator();
		while(iu.hasNext()){
			User user = iu.next();
			System.out.println("aaa");
			if("王五".equals(user.getName())){
				users.remove(user);
			} else {
				System.out.println(user);				
			}
		}
		
		/*Collection users = new CopyOnWriteArrayList();
		
		users.add(new User("张三",28));	
		users.add(new User("李四",25));			
		users.add(new User("王五",31));	
		Iterator itrUsers = users.iterator();
		while(itrUsers.hasNext()){
			System.out.println("aaaa");
			User user = (User)itrUsers.next();
			if("张三".equals(user.getName())){
				users.remove(user);
				//itrUsers.remove();
			} else {
				System.out.println(user);				
			}
		}*/
	}
}
