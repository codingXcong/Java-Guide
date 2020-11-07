package netty.example.codec.decoder;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * 编解码器，即包含ByteToCharDecoder，又包含CharToByteEncoder
 * @author zhangguicong
 * @date 2020-08-30
 */
public class CombinedByteCharCodec extends CombinedChannelDuplexHandler<ByteToCharDecoder, CharToByteEncoder> {

    public CombinedByteCharCodec() {
        //将委托实例传递给父类
        super(new ByteToCharDecoder(), new CharToByteEncoder());
    }

}
