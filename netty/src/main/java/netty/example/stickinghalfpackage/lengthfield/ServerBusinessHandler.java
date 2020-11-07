package netty.example.stickinghalfpackage.lengthfield;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

import static io.netty.util.CharsetUtil.UTF_8;

/**
 * @author zhangguicong
 * @date 2020-08-15
 */
public class ServerBusinessHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;

    @Override protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol mp) throws Exception {
        //接收到数据，并处理
        int len = mp.getLength();
        byte[] content = mp.getContent();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("服务器接收到信息如下");
        System.out.println("长度=" + len);
        System.out.println("内容=" + new String(content, UTF_8));

        System.out.println("服务器接收到消息包数量=" + (++this.count));

        //回复消息
        String responseContent = UUID.randomUUID().toString();
        int responseLen = responseContent.getBytes("utf-8").length;
        byte[]  responseContent2 = responseContent.getBytes("utf-8");
        //构建一个协议包
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLength(responseLen);
        messageProtocol.setContent(responseContent2);

        ctx.writeAndFlush(messageProtocol);
    }

    @Override public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }

    @Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
