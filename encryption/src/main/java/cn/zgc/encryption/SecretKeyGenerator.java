package cn.zgc.encryption;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @author zhangguicong
 * @date 2020-11-14
 */
public class SecretKeyGenerator {

    private static final String KEY_ALGORITHM_DES = "DES";
    private static final String KEY_ALGORITHM_3DES = "DESede";
    private static final String KEY_ALGORITHM_AES = "AES";

    /**
     * 生成随机的密钥
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static SecretKey randomDesKey() throws NoSuchAlgorithmException {
        // 实例化密钥生成器
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM_DES);
        // 初始化，使用sun提供者，这里必须指定56
        kg.init(56);
        // 生成密钥
        SecretKey secretKey = kg.generateKey();
        return secretKey;
    }

    /**
     * 根据给定的密钥串生成密钥
     * @param secretKeyStr 密钥字符串，要求密钥长度要大于等于8
     * @return
     * @throws Exception
     */
    public static SecretKey genDesKey(String secretKeyStr) throws Exception {
        // 实例化DES密钥材料
        DESKeySpec desKeySpec = new DESKeySpec(secretKeyStr.getBytes("utf-8"));
        // 实例化密钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM_DES);
        // 生成密钥
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        return secretKey;
    }

    /**
     * 生成随机的密钥
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static SecretKey randomDESedeKey() throws NoSuchAlgorithmException {
        // 实例化密钥生成器
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM_3DES);
        // 初始化，使用sun提供者，这里必须指定112或者168
        kg.init(168);
        // 生成密钥
        SecretKey secretKey = kg.generateKey();
        // 获得密钥的二进制编码
        return secretKey;
    }


    public static SecretKey genDESedeKey(String secretKeyStr) throws Exception {
        // 实例化DESede密钥材料
        DESedeKeySpec dks = new DESedeKeySpec(secretKeyStr.getBytes("utf-8"));
        // 实例化密钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM_3DES);
        // 生成密钥
        return keyFactory.generateSecret(dks);
        //return new SecretKeySpec(secretKeyStr.getBytes("utf-8"),KEY_ALGORITHM_3DES);
    }

    public static SecretKey randomAesKey() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM_AES);
        // AES要求密钥长度为128位、192位或256位
        kg.init(256);
        return kg.generateKey();
    }

    public static SecretKey genAesKey(String secretKeyStr) throws Exception {
        SecretKey secretKey = new SecretKeySpec(secretKeyStr.getBytes("utf-8"),KEY_ALGORITHM_AES);
        return secretKey;
    }

    public static void main(String[] args) throws Exception {
        SecretKey secretKey = randomDesKey();
        System.out.println(secretKey);
        //DES密钥字符串长度必须大于等于8个字节
        SecretKey secretKey1 = genDesKey("abcdefgh");
        System.out.println(secretKey1);

        SecretKey secretKey2 = randomDESedeKey();
        System.out.println(secretKey2);
        // 3DEA密钥字符串长度必须大于等于24个字节
        SecretKey secretKey4 = genDESedeKey("abcdefghabcdefghabcdefgh");
        System.out.println(secretKey4);

        SecretKey secretKey3 = randomAesKey();
        System.out.println(secretKey3);
        // AES密字符串长度需为16字节、24字节、32字节
        SecretKey secretKey5 = genAesKey("12345678");
        System.out.println(secretKey5);

    }
}
