package netty.example.stickinghalfpackage.lengthfield;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author zhangguicong
 * @date 2020-08-16
 */
public class MessageProtocolEncoder extends MessageToByteEncoder<MessageProtocol> {

    @Override protected void encode(ChannelHandlerContext channelHandlerContext, MessageProtocol messageProtocol, ByteBuf out)
            throws Exception {
        System.out.println("MessageProtocolEncoder encode");
        out.writeInt(messageProtocol.getLength());
        out.writeBytes(messageProtocol.getContent());
    }
}
