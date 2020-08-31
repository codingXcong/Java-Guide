package netty.example.codec.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author zhangguicong
 * @date 2020-08-30
 */
public class CharToByteEncoder extends MessageToByteEncoder<Character> {

    @Override protected void encode(ChannelHandlerContext ctx, Character msg, ByteBuf out) throws Exception {
        //将 Character 解码为 char，并将其写入到出站 ByteBuf 中
        out.writeChar(msg);
    }

}
