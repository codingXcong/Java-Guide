package netty.example.codec.decoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @author zhangguicong
 * @date 2020-08-30
 */
public class IntegerToStringEncoder extends MessageToMessageEncoder<Integer> {

    @Override protected void encode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {
        out.add(String.valueOf(msg));
    }

}
