package netty.example.stickinghalfpackage.lengthfield;

import lombok.Data;

import java.io.Serializable;

/**
 * 协议包
 * @author zhangguicong
 * @date 2020-08-15
 */
@Data public class MessageProtocol implements Serializable {
    private int length;
    private byte[] content;
}
