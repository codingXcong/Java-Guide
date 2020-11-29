package cn.zgc.encryption;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

/**
 * @author zhangguicong
 * @date 2020-11-29
 */
public class CertificateUtils {

    public static final String CERT_TYPE = "X.509";

    public static byte[] encryptByPrivateKey(byte[] data, String keyStorePath, String alias, String password) {
        return null;
    }

    public static byte[] decryptByPrivateKey(byte[] data, String keyStorePath, String alias, String password) {
        return null;
    }

    public static byte[] encryptByPublicKey(byte[] data, String certificatePath) {
        return null;
    }

    public static byte[] decryptByPublicKey(byte[] data, String certificatePath) {
        return null;
    }

    public static byte[] sign(byte[] sign, String keyStorePath, String alias, String password) {
        return null;
    }

    public static boolean verify(byte[] data, byte[] sign, String certificatePath) {
        return false;
    }

    private static PrivateKey getPrivateKeyByKeyStore() {
        return null;
    }

    private static PublicKey getPublicKeyByCertificate() {
        return null;
    }

    /**
     * 根据证书路径加载证书
     * @param certificatePath 证书路径
     * @return
     */
    private static Certificate getCertificate(String certificatePath) {

        return null;
    }

    /**
     * 从密钥库中获取证书
     */
    private static Certificate getCertificate(String keyStorePath, String alias, String password) throws Exception {
        KeyStore keyStore = getKeyStore(keyStorePath, password);
        return keyStore.getCertificate(alias);
    }

    /**
     * 获取密钥库
     */
    private static KeyStore getKeyStore(String keyStorePath, String password) throws Exception {
        // 实例化密钥库
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        // 获取密钥库文件流
        FileInputStream is = new FileInputStream(keyStorePath);
        // 加载密钥库
        ks.load(is, password.toCharArray());
        // 关闭密钥库文件流
        is.close();
        return ks;
    }


}

