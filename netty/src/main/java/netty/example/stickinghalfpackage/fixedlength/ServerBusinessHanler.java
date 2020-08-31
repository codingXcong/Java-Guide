package netty.example.stickinghalfpackage.fixedlength;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

import static io.netty.util.CharsetUtil.UTF_8;

public class ServerBusinessHanler extends SimpleChannelInboundHandler<ByteBuf> {

    int count = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        System.out.println("从客户端收到的字符串:" + msg.toString(UTF_8));
        System.out.println("服务端接收到的请求数:" + (++count));
        ctx.writeAndFlush(Unpooled.copiedBuffer(UUID.randomUUID().toString(),UTF_8) );

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        System.out.println(cause.getStackTrace());
        ctx.channel().close();
    }

}
