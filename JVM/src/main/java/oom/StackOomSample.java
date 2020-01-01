package oom;

/**
 * vm args:
 *    -XX:ThreadStackSize=1m
 * @author zhangguicong
 * @date 2019-12-23
 */
public class StackOomSample {

    private static long counter = 0 ;

    public static void main(String[] args) {
        call();
    }

    private static void call () {
        counter++;
        System.out.println("第"+counter+"次调用call方法");
        call();
    }

}
