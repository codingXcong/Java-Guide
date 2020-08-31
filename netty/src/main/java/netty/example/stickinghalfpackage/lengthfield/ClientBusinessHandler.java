package netty.example.stickinghalfpackage.lengthfield;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import static io.netty.util.CharsetUtil.UTF_8;

/**
 * @author zhangguicong
 * @date 2020-08-15
 */
public class ClientBusinessHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;

    @Override protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProtocol mp) throws Exception {
        System.out.println("客户端收到如下消息：");
        System.out.println("length:"+mp.getLength());
        System.out.println("content:"+new String(mp.getContent(),UTF_8));
        System.out.println("客户端收到消息的次数："+(++count));
    }

    @Override public void channelActive(ChannelHandlerContext ctx)  {
        for(int i=0; i<5; i++) {
                String msg = "i feel cold";
                byte[] content = msg.getBytes(UTF_8);
                int len = content.length;

                MessageProtocol mp = new MessageProtocol();
                mp.setLength(len);
                mp.setContent(content);
                ctx.writeAndFlush(mp);
        }
    }

    @Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
