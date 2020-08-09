package netty.example.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;

/**
 * @author zhangguicong
 * @date 2020-07-22
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 上线了～");
    }

    @Override public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 离开了～");
    }

    /**
     * 表示连接建立，一旦连接，handlerAdded第一个被执行
     * 将当前的channel 加入到 channelGroup中
     * @param ctx
     * @throws Exception
     */
    @Override public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 遍历channelGroup中所有的channel，并发送消息
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + " 加入聊天" + sdf.format(new java.util.Date()) + " \n");
        // 加入群聊
        channelGroup.add(channel);
    }

    /**
     * 断开连接，将断开信息推送给其他在线的用户
     * @param ctx
     * @throws Exception
     */
    @Override public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "离开了\n");
        System.out.println("channelGroup size " + channelGroup.size());
    }

    /**
     * 数据读取
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 获取当前channel
        Channel channel = ctx.channel();
        // 遍历channelGroup，回送不同的消息
        channelGroup.forEach(ch -> {
            if (ch != channel) {
                ch.writeAndFlush("[用户] "+channel.remoteAddress()+" 说："+msg+"\n");
            } else {
                ch.writeAndFlush("[你自己] 说："+msg+"\n");
            }
        });
    }

    @Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
