package netty.example.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/**
 * @author zhangguicong
 * @date 2020-07-12
 */
public class ByteBufSamples {
    public static void main(String[] args) {
        newByteBuf();
        readByteBuf();
    }

    private static void readByteBuf() {
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello, ByteBuf", Charset.forName("utf-8"));

        System.out.println(byteBuf.toString(Charset.forName("utf-8")));


        //使用相关的方法
        if(byteBuf.hasArray()) { // true


            //按照某个范围读取
            System.out.println(byteBuf.getCharSequence(0, 4, Charset.forName("utf-8")));
            System.out.println(byteBuf.getCharSequence(4, 6, Charset.forName("utf-8")));


        }
    }

    /**
     * ByteBuf不需要使用flip 进行反转，它底层维护了 readerindex 和 writerIndex
     * 通过 readerindex 和  writerIndex 和  capacity， 将buffer分成三个区域：
     *      0---readerindex 已经读取的区域
     *      readerindex---writerIndex ， 可读的区域
     *      writerIndex -- capacity, 可写的区域
     */
    private static void newByteBuf() {
        ByteBuf buffer = Unpooled.buffer(15);

        for (int i = 0; i < 10; i++) {
            buffer.writeByte(i);
        }

        System.out.println("capacity:"+buffer.capacity());      //15
        System.out.println("readerIndex:"+buffer.readerIndex());//0
        System.out.println("writerIndex:"+buffer.writerIndex());//10

        for(int i = 0; i < buffer.writerIndex(); i++) {
            System.out.println(buffer.readByte());
        }

        System.out.println("capacity:"+buffer.capacity());      //15
        System.out.println("readerIndex:"+buffer.readerIndex());//10
        System.out.println("writerIndex:"+buffer.writerIndex());//10
    }
}
