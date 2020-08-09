package netty.example.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author zhangguicong
 * @date 2020-07-11
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {


    @Override public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // ByteBuf是引用计数对象，必须要调用release()显式释放对象
        ((ByteBuf)msg).release();
    }

    @Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 发生异常时关闭连接
        ctx.close();
    }
}
