package cn.zgc.encryption;


import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author zhangguicong
 * @date 2020-11-14
 */
public class MyKeyGenerator {

    private static final String KEY_ALGORITHM_DES = "DES";
    private static final String KEY_ALGORITHM_3DES = "DESede";
    private static final String KEY_ALGORITHM_AES = "AES";
    private static final String KEY_ALGORITHM_RSA = "RSA";

    /**
     * 生成随机的密钥
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static SecretKey randomDesKey() throws NoSuchAlgorithmException {
        // 实例化密钥生成器
        javax.crypto.KeyGenerator kg = javax.crypto.KeyGenerator.getInstance(KEY_ALGORITHM_DES);
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
        javax.crypto.KeyGenerator kg = javax.crypto.KeyGenerator.getInstance(KEY_ALGORITHM_3DES);
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

    /**
     * 随机生成密钥对
     * @return
     * @throws Exception
     */
    public static KeyPair randomKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM_RSA);
        /**
         * 设置RSA密钥长度，范围在512-65536之间且是64的倍数，默认是1024位
         */
        keyPairGenerator.initialize(1024);
        // 生成密钥对
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 随机生成公钥（RSA）
     * @return
     * @throws Exception
     */
    public static PublicKey randomPublicKey() throws Exception {
        KeyPair keyPair = randomKeyPair();
        return keyPair.getPublic();
    }

    /**
     * 根据公钥串生成公钥
     * @param publicKeyStr
     * @return
     * @throws Exception
     */
    public static PublicKey genPublicKey(String publicKeyStr) throws Exception {
        // 实例化公钥材料
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyStr));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
        // 生成公钥
        return keyFactory.generatePublic(publicKeySpec);
    }

    /**
     * 随机生成私钥（RSA）
     * @return
     * @throws Exception
     */
    public static PrivateKey randomPrivateKey() throws Exception {
        KeyPair keyPair = randomKeyPair();
        return keyPair.getPrivate();
    }


    /**
     * 根据私钥串生成私钥
     * @param privateKeyStr 私钥字符串，BASE64格式
     * @return
     * @throws Exception
     */
    public static PrivateKey genPrivateKey(String privateKeyStr) throws Exception {
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyStr));
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM_RSA);
        return keyFactory.generatePrivate(privateKeySpec);
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

        KeyPair keyPair = randomKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        //PublicKey publicKey = randomPublicKey();
        System.out.println("publicKey:"+ Base64.getEncoder().encodeToString(publicKey.getEncoded()));

        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //PrivateKey privateKey = randomPrivateKey();
        System.out.println("privateKey:"+ Base64.getEncoder().encodeToString(privateKey.getEncoded()));

        PublicKey publicKey1 = genPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjZRirloRSTzhYSeqCqi/pfU1DSG91GZMoxIOxLBOhx64R0lobMXfsahSCDumZdDsNlLoHteSV1X4d427LzH1/awHKK/M9ko1AT6OIzrEUbiI1Ni2npq90uTVnpv41pUHBcatoCNXOqv6TEZo9p1EYNxYO6zr1Ec7MC8KdcxQHh416Z7f2FqZmo7gVUAzbBkDRtigXHuhZPG8nX1Yu+bOCP1cIOGP+C986GpICl2o+SltfRnyHN+OTi15K6dSXzBECzRpVf8N7sKcettdNi3DbGhGKS0BzDUmY3VvFmqezN3sBQg15OimukQg2KsdgE7z2YwenPY/kUNgdk5KUjEAXQIDAQAB");
        System.out.println(publicKey1);

        PrivateKey privateKey1 = genPrivateKey("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCQu8W1M32viRxzvBtg81RUDxF0IixKqtklaSh7TdfbTqScMo081kbQvnf3iZfVgmtiTeZU2XT57rosP9ukapqlBM5clhJI0mb3P4a+vsSNwghro14OFYEZ2dG0Ijvwb/E7ksqTQrN5L0Fcv4VQ9iI3YGkgbymCbWcYtTbiGh8rymMFolLi2Rb0AUB/d3m8Y9ZlBoj1rkTj8qoIEkUjWanYoALKgay8VpG8H64RLrtNnErSEEt/j24Wu/VSr+lzVtGvk4GIZrljW9/FfEvg30vajAL9tCMbWZo5wVjqHlUfdXB1ZlBAukQEM9HEczSnLD5YbzHgBI//phfkBA+3eQVlAgMBAAECggEBAIgOfIUiqnxhQEXsBzDUjemXYKjzlv96LDdm648+VjTrcQME4pDWPivtYYlp5znnKFt5L5WD9tsc+Baw0SF2tXl7UyLmULn0UKcNKPYXD17TkGbfZIOI45wRj3ju1bnpAbUnARf+FAlPyvoNcJ3zQ6cbErhSx4q8nbY0ZCuYssTOSHFd9qESYZEwsOYqLkt2+3FXdDdWvlcNnrcnmAK4ZneaG+ObGHBvVZiONkwCdvScj4G2I2urHkWO91dethXybZQsqVEnLaMjA6C2Aefp+FftiC75cnvUZBRIa0+HcSJtRDrdmkFf7uwb7asL0LxRv0N39Ywoi7MQ6NNwMgpqfuECgYEA2Hk8RTuRu1b6knUT6GLO9retE1p64d8dbQfE142+nJ8bzLs+A1lVLxvfjfjrgr+Igb1ehnG0E6Fwlkz0RcaLGWaE/F8HLdTEIS5gkvYsXBFuD0gdnmJLdcBH+eguE9nIn0trKcPlzQyscOpOAER6xkKUA9gA2YJhW6Rbz8ht35cCgYEAqykkst9SQBtniiS8xZ3n830AvtgnsfIRWZHgy5WanVSjz2qeZY64XBxlKYbB3L5DozEPW+8dGUzZj9zJUASFOzMsg+hQ0uK6BcezsiTbbMTv8amuLlZL8MebvtiByZ/32c50/u7xL1iNOLuW4Me9a+Wf54HKGrNEr5k1Rk4yomMCgYEAoncK1uMIUtPKp1SbJlPSBLSBfv7vLsom6YdlImnNXvrDAHqe5iCLoBmJRKJHDw/0wXqAkOKRRhJpz0PeKOgDyYJ52HbximIe+NA3xtRfIpIqSenT4OTgwXUYD+VDf755paQSv3u44pXabbxL4kOC6X1aXTAmIxiJPSAzj9RbMe0CgYAL5BLZnmwvaff6f+zhacP4hYGswIWZdjDAYUMVwbH5Ji0vNsAEXDLwq0RX6zXCqTE3wCvJ/kWuQUmIFhWiSn4bC8YW7+fVjszPo/0W7jqURuZIirpAG9ywwJ1QV+hEYqmsZwx3vY/h8yv0egvUV+wP9+bxBXJOApBOBr3NwZcA8wKBgFX8Vg8R81qZbNKArQJssAT0ri+npCNhCa4uHvk8jALlqLCeWEy1pI1cG+s6ibnGYI3RpCbcIAf49fcGYJY59x5nnH7lzmIOxmmoG5y712EM2ywR4PhNm+kzN5SY5GcC3mXp1rtc+JwyvhxtSeXvweh0NejJC2gxI+1Syp4K4onl");
        System.out.println(privateKey1);

    }
}
