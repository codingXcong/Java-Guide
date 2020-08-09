package netty.example.codec;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @author zhangguicong
 * @date 2020-08-09
 */
public class NettyClient {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();

        try {

        } finally {
            group.shutdownGracefully();
        }
    }
}
