package cn.zgc.encryption;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * @author zhangguicong
 * @date 2020-11-08
 */
public class KeyGeneratorSample {
    public static void main(String[] args) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance("DES");
        kg.init(56);
        SecretKey secretKey = kg.generateKey();
        byte[] encoded = secretKey.getEncoded();
        StringBuilder sb = new StringBuilder();
        for (byte item : encoded) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
        }
        String result = sb.toString().toUpperCase();
        System.out.println(result);
    }
}
