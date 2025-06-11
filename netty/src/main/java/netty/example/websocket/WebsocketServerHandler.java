package netty.example.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * @author zhangguicong
 * @date 2020-08-07
 */
public class WebsocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    // 存储所有连接的客户端Channel
    private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("服务器收到消息："+msg.text());
        // 回复消息
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间"+ LocalDateTime.now()));
    }

    // 当web客户端连接后， 触发方法
    @Override public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channels.add(ctx.channel());  // 新连接加入组
        //id 表示唯一的值，LongText 是唯一的 ShortText 不是唯一
        System.out.println("handlerAdded 被调用" + ctx.channel().id().asLongText());
        System.out.println("handlerAdded 被调用" + ctx.channel().id().asShortText());
    }


    @Override public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        channels.remove(ctx.channel());
        System.out.println("handlerRemoved 被调用" + ctx.channel().id().asLongText());
    }

    @Override public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    // 新增方法：主动向所有客户端发送消息
    public static void broadcast(String message) {
        channels.writeAndFlush(new TextWebSocketFrame(message));
    }
}
