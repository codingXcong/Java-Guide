package zxx.jdk5;
/**
 * 实现一个缓存机制
 *    使用读写锁
 * @author zgc
 */
import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
public class CachDemo {
	private Map<String,Object> cache = new HashMap<String,Object>();
	
	public static void main(String[] args) {
		
	}
	
	/*//使用synchronize实现线程安全
	public Object getData(String key){
		Object value = cache.get(key);
		synchronized (this) {
			if(value==null){
				value = "aaaa";//实际连接数据库查询;
				cache.put(key,value);
			}
		}
		return value;
	}*/
	
	private ReadWriteLock rwl = new ReentrantReadWriteLock();
	public  Object getData(String key){
		rwl.readLock().lock();
		Object value = null;
		try{
			value = cache.get(key);
			if(value == null){
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				try{
					if(value==null){
						value = "aaaa";//实际失去queryDB();
					}
				}finally{
					rwl.writeLock().unlock();
				}
				rwl.readLock().lock();
			}
		}finally{
			rwl.readLock().unlock();
		}
		return value;
	}
}
