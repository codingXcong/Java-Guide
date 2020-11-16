package cn.zgc.encryption;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * @author zhangguicong
 * @date 2020-11-15
 */
public class DesDemo {

    /**
     * CBC模式需要有个初始化向量
     */
    private static final String TRANSFORMATION = "DES/CBC/PKCS5Padding";
    private static final String KEY_ALGORITHM = "DES";

    public static void main(String[] args) throws Exception {
        String plainText = "abc123加密";
        String secretKeyStr = "3h$1j4&d";
        String cipherText = encrypt(plainText, secretKeyStr);
        System.out.println("密文："+cipherText);
        String msg = decrypt(cipherText, secretKeyStr);
        System.out.println("明文:"+msg);
    }

    private static String encrypt(String plainText, String secretKeyStr ) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        Key key = new SecretKeySpec(secretKeyStr.getBytes("utf-8"), KEY_ALGORITHM);
        // 初始向量，参数表示跟谁进行异或，初始向量的长度必须是8位
        IvParameterSpec iv = new IvParameterSpec("12345678".getBytes("utf-8"));
        cipher.init(Cipher.ENCRYPT_MODE,key,iv);
        byte[] bytes = cipher.doFinal(plainText.getBytes("utf-8"));
        return Base64.encodeBase64String(bytes);
    }

    private static String decrypt(String cipherText, String secretKeyStr) throws Exception {
        byte[] cipherData = Base64.decodeBase64(cipherText);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);

        Key key = new SecretKeySpec(secretKeyStr.getBytes("utf-8"), KEY_ALGORITHM);
        // 初始向量，参数表示跟谁进行异或，初始向量的长度必须是8位
        IvParameterSpec iv = new IvParameterSpec("12345678".getBytes("utf-8"));
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] bytes = cipher.doFinal(cipherData);
        return new String(bytes,"utf-8");
    }

}
