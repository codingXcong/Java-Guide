package netty.example.helloword;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author zhangguicong
 * @date 2020-07-18
 */
public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        // 事件循环组
        EventLoopGroup group = new NioEventLoopGroup();

        try{
            // 创建客户端启动类
            Bootstrap bootstrap = new Bootstrap();

            // 设置相关参数
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyClientHandler());
                        }
                    });

            // 连接服务器端
            ChannelFuture channelFuture = bootstrap.connect("localhost", 5555).sync();
            // 对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }


}
