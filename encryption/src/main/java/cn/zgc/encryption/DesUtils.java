package cn.zgc.encryption;


import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

/**
 * DES、3DES(DESede)加解密工具类
 * @author zhangguicong
 * @date 2020-11-14
 */
public class DesUtils {

    private static final String ALGORITHM = "DES/ECB/PKCS5Padding";
    private static final String ALGORITHM_3DES = "DESede/ECB/PKCS5Padding";

    public static byte[] encryptDes(String plainText, String secretKeyStr) throws Exception {
        //SecretKey secretKey = genDesKey();
        // 构建密钥
        SecretKey secretKey = SecretKeyGenerator.genDesKey(secretKeyStr);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 初始化为加密模式
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        byte[] data = cipher.doFinal(plainText.getBytes("UTF-8"));
        return data;
    }

    public static byte[] decryptDes(byte[] cipherData, String secretKeyStr) throws Exception {
        // 构建密钥
        SecretKey secretKey = SecretKeyGenerator.genDesKey(secretKeyStr);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 初始化为解密模式
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] bytes = cipher.doFinal(cipherData);
        return bytes;
    }

    public static byte[] encrypt3Des(String plainText, String secretKeyStr) throws Exception {
        SecretKey secretKey = SecretKeyGenerator.genDESedeKey(secretKeyStr);
        Cipher cipher = Cipher.getInstance(ALGORITHM_3DES);
        // 初始化为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        // 加密
        return cipher.doFinal(plainText.getBytes("utf-8"));
    }

    public static byte[] decrypt3Des(byte[] cipherData, String secretKeyStr) throws Exception {
        SecretKey secretKey = SecretKeyGenerator.genDESedeKey(secretKeyStr);
        Cipher cipher = Cipher.getInstance(ALGORITHM_3DES);
        // 初始化为解密模式
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        // 解密
        return cipher.doFinal(cipherData);
    }



    public static void main(String[] args) throws Exception {
        String secretKeyStr = "aj2gh1#q";
        byte[] data = encryptDes("hello",secretKeyStr);
        String text = Base64.encodeBase64String(data);
        System.out.println("DES加密："+text);

        byte[] decrypt = decryptDes(data, secretKeyStr);
        System.out.println("DES解密："+new String(decrypt,"utf-8"));

        // 3DES密钥长度要大于等于24个字节
        String desesKeyStr = "aj2gh1#qaj2gh1#qaj2gh1#q";
        byte[] bytes = encrypt3Des("3des加密", desesKeyStr);
        System.out.println("3DES加密：" + Base64.encodeBase64String(bytes));

        byte[] bytes1 = decrypt3Des(bytes, desesKeyStr);
        System.out.println("3DES解密：" + new String(bytes1, "utf-8"));

    }
}
