package basic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A 线程打印 5 次 A，B 线程打印 10 次 B，C 线程打印 15 次 C,按照此顺序循环 10 轮
 */
public class ThreadCommunicationWithCondition {
    // 通信对象:0--打印 A 1---打印 B 2----打印 C
    private int number = 0;
    // 声明一个锁
    private Lock lock = new ReentrantLock();
    // 声明钥匙A
    private Condition conditionA = lock.newCondition();
    // 声明钥匙B
    private Condition conditionB = lock.newCondition();
    // 声明钥匙C
    private Condition conditionC = lock.newCondition();

    /**
     * A打印5次
     * @param j
     */
    public void printA(int j) {
        try {
            lock.lock();
            while(number != 0) {
                conditionA.await();
            }
            System.out.println(Thread.currentThread().getName() + "输出 A,第" + j + "轮开始");
            //输出 5 次 A
            for (int i = 0; i < 5; i++) {
                System.out.println("A");
            }
            //开始打印 B
            number = 1;
            //唤醒 B
            conditionB.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * B 打印10次
     * @param j
     */
    public void printB(int j) {
        try {
            lock.lock();
            while(number != 1) {
                conditionB.await();
            }
            System.out.println(Thread.currentThread().getName() + "输出 B,第" + j + "轮开始");
            //输出 10 次 B
            for (int i = 0; i < 10; i++) {
                System.out.println("B");
            }
            //开始打印 C
            number = 2;
            //唤醒 C
            conditionC.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 打印15次 C
     * @param j
     */
    public void printC(int j) {
        try {
            lock.lock();
            while(number != 2) {
                conditionC.await();
            }
            System.out.println(Thread.currentThread().getName() + "输出 C,第" + j + "轮开始");
            //输出 15 次 C
            for (int i = 0; i < 15; i++) {
                System.out.println("C");
            }
            // 开始打印A
            number = 0;
            conditionA.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ThreadCommunicationWithCondition communication = new ThreadCommunicationWithCondition();

        new Thread(() ->{
            for (int i = 1; i <= 10; i++) {
                communication.printA(i);
            }
        }, "A 线程").start();

        new Thread(() ->{
            for (int i = 1; i <= 10; i++) {
                communication.printB(i);
            }
        }, "B 线程").start();

        new Thread(() ->{
            for (int i = 1; i <= 10; i++) {
                communication.printC(i);
            }
        }, "C 线程").start();
    }

}
