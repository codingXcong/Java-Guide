package netty.example.helloword;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @author zhangguicong
 * @date 2020-07-18
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client:"+ctx);
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello, world.", CharsetUtil.UTF_8);
        ctx.writeAndFlush(byteBuf);
    }

    @Override public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("msg from server:"+byteBuf.toString(CharsetUtil.UTF_8));
    }

    @Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
