package cn.zgc.encryption;
import org.apache.commons.codec.binary.Base64;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author zgc
 * @desc AES 加密工具类
 */
public class AesUtils {
    public static final String CODE = "utf-8";
    private static final String KEY_ALGORITHM = "AES";
    /*** 默认的加密算法 */
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";


    /**
     * AES 加密操作
     *
     * @param plainText  待加密内容
     * @param password 加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String plainText, String password) {
        try {
            //初始化密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            Key key = MyKeyGenerator.genAesKey(password);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            // 加密
            byte[] result = cipher.doFinal(plainText.getBytes(CODE));
            //通过Base64转码返回
            return Base64.encodeBase64String(result);
        } catch (Exception ex) {
            Logger.getLogger(AesUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content
     * @param password
     * @return
     */
    public static String decrypt(String content, String password) {

        try {
            //初始化密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            Key key = MyKeyGenerator.genAesKey(password);
            cipher.init(Cipher.DECRYPT_MODE, key);
            // 加密
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));
            return new String(result, CODE);
        } catch (Exception ex) {
            Logger.getLogger(AesUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(final String password) {
        //返回生成指定算法密钥生成器的 MyKeyGenerator 对象
        javax.crypto.KeyGenerator kg = null;
        try {
            kg = javax.crypto.KeyGenerator.getInstance(KEY_ALGORITHM);

            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());

            //AES 要求密钥长度为 128
            kg.init(128);
            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            // 转换为AES专用密钥
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AesUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        String s = "hello,您好";
        System.out.println("加密前的明文:" + s);
        String s1 = AesUtils.encrypt(s, "12341234123412341234123412341234");
        System.out.println("密文:" + s1);
        System.out.println("解密后的明文:" + AesUtils.decrypt(s1, "12341234123412341234123412341234"));
    }

}
