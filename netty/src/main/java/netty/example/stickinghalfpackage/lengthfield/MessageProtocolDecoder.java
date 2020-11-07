package netty.example.stickinghalfpackage.lengthfield;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author zhangguicong
 * @date 2020-08-16
 */
public class MessageProtocolDecoder extends ReplayingDecoder<Void> {

    @Override protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) {
        System.out.println("MessageProtocolDecoder decode");
        int length = in.readInt();

        byte[] content = new byte[length];
        in.readBytes(content);

        // 转换成MessageProtocol对象，并放入out中，传递给下一个handler进行处理
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setLength(length);
        messageProtocol.setContent(content);
        out.add(messageProtocol);

    }
}
