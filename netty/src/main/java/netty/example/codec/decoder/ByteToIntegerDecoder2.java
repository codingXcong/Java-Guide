package netty.example.codec.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author zhangguicong
 * @date 2020-08-30
 */
public class ByteToIntegerDecoder2 extends ReplayingDecoder<Void> {

    @Override protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //从入站 ByteBuf 中读取 一个 int，并将其添加到解码消息的 List 中
        out.add(in.readInt());
    }

}
