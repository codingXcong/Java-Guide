package basic;

/**
 * synchronized关键字的使用场景
 * @author zhangguicong
 * @date 2019-12-01
 */
public class SynchronizedSample {
    private Integer count = 0;
    private static Long staticCount = 0L;

    private final Object MUTEX = new Object();

    public synchronized void method() {
        count++;
    }

    public static synchronized void staticMethod() {
        staticCount++;
    }

    public void codeFragment() {
        synchronized (this) {
            count++;
        }
    }

    public void staticCodeFragment() {
        synchronized (SynchronizedSample.class) {
            count++;
        }
    }

    public static void main(String[] args) {

    }

}
