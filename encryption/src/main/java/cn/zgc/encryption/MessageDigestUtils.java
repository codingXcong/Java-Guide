package cn.zgc.encryption;

import java.security.MessageDigest;

/**
 * 消息摘要
 * @author zhangguicong
 * @date 2020-11-07
 */
public class MessageDigestUtils {
    public static void main(String[] args) throws Exception {
        String msg = "hello alpahapy";
        MessageDigest md = MessageDigest.getInstance("MD4");
        byte[] digest = md.digest(msg.getBytes("utf-8"));
        StringBuilder sb = new StringBuilder();
        for (byte item : digest) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        String result = sb.toString().toUpperCase();
        System.out.println(result);
    }
}
