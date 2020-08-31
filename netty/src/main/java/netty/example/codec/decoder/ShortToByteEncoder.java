package netty.example.codec.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author zhangguicong
 * @date 2020-08-30
 */
public class ShortToByteEncoder extends MessageToByteEncoder<Short> {

    @Override protected void encode(ChannelHandlerContext ctx, Short msg, ByteBuf out) throws Exception {
        out.writeShort(msg);
    }

}
