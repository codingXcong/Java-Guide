package netty.example.stickinghalfpackage.delimiter;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import static io.netty.util.CharsetUtil.UTF_8;


public class ClientBusinessHandler extends SimpleChannelInboundHandler<ByteBuf> {
    int count = 1;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg){
        System.out.println("从服务器端接收到的信息: " + msg.toString(UTF_8));
        System.out.println("从服务器端读到的请求的个数: " + count++);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("abcdefghijklmnopqrstuvwxyz-abcdefghijklmnopqrstuvwxyz-abcdefghijklmnopqrstuvwxyz-abcdefghijklmnopqrstuvwxyz".getBytes()));
    }
}
