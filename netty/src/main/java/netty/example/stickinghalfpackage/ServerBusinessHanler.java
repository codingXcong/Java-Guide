package netty.example.stickinghalfpackage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

public class ServerBusinessHanler extends SimpleChannelInboundHandler<ByteBuf> {

    int count = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        String message = new String(bytes, Charset.forName("UTF-8"));
        System.out.println("从客户端收到的字符串:" + message);
        System.out.println("服务端接收到的请求数:" + (++count));
        ctx.writeAndFlush(Unpooled.copiedBuffer(UUID.randomUUID().toString(),Charset.forName("UTF-8")) );

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        System.out.println(cause.getStackTrace());
        ctx.channel().close();
    }

}
