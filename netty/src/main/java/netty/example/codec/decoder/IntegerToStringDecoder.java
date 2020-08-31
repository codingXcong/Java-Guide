package netty.example.codec.decoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @author zhangguicong
 * @date 2020-08-30
 */
public class IntegerToStringDecoder extends MessageToMessageDecoder<Integer> {

    @Override protected void decode(ChannelHandlerContext ctx, Integer msg, List<Object> out) throws Exception {
        // 放入out中，传递给pipelline中下一个Handler
        out.add(String.valueOf(msg));
    }

}
