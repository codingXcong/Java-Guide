package cn.zgc.network.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author zhangguicong
 * @date 2025/3/27
 */
public class DbufferDemo {
    public static void main(String[] args) {
        ByteBuffer intBuffer = ByteBuffer.allocateDirect(1024);
        System.out.println("------------after allocate------------------");
        System.out.println("position=" + intBuffer.position());
        System.out.println("limit=" + intBuffer.limit());
        System.out.println("capacity=" + intBuffer.capacity());

        for (int i = 0; i < 5; i++) {
            intBuffer.putInt(i);

        }

        System.out.println("------------after putTest------------------");
        System.out.println("position=" + intBuffer.position());
        System.out.println("limit=" + intBuffer.limit());
        System.out.println("capacity=" + intBuffer.capacity());
        // 使用buffer之前要先flip一下
        intBuffer.flip();
        System.out.println("------------after flip ------------------");
        System.out.println("position=" + intBuffer.position());
        System.out.println("limit=" + intBuffer.limit());
        System.out.println("capacity=" + intBuffer.capacity());
        for (int i = 0; i < 5; i++) {
            int j = intBuffer.getInt();
            System.out.println("j = " + j);
        }
    }
}
