package netty.example.codec.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author zhangguicong
 * @date 2020-08-30
 */
public class ByteToIntegerDecoder extends ByteToMessageDecoder {
    @Override protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 一个int = 4为字节
        if (in.readableBytes() >= 4) {
            out.add(in.readInt());
        }
    }
}
