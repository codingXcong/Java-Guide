package cn.zgc.encryption;

import org.apache.commons.codec.binary.Hex;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * @author zhangguicong
 * @date 2020-11-22
 */
public class SignatureUtils {

    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    public static byte[] sign(byte[] data, String privateKeyStr) throws Exception {
        // 生成私钥
        PrivateKey privateKey = MyKeyGenerator.genPrivateKey(privateKeyStr);
        // 实例化Signature对象
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        // 初始化Signature
        signature.initSign(privateKey);
        // 更新
        signature.update(data);
        // 签名
        return signature.sign();
    }

    public static boolean verify (byte[] data, String publicKeyStr, byte[] sign) throws Exception {
        // 生成公钥
        PublicKey publicKey = MyKeyGenerator.genPublicKey(publicKeyStr);
        // 实例化
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        // 初始化Signature
        signature.initVerify(publicKey);
        // 更新
        signature.update(data);
        // 验证
        return signature.verify(sign);
    }

    public static void main(String[] args) throws Exception{
        String msg = "数字签名MD5withRSA";
        String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCYaaj9EFpOTCs72ZDnaYgrU8xssv4CmSJ8+Ri38nWy/bdNNZYYRMaNDnVdjzfTna7Usiz0Msvqc7tb8qsUOMzUHboBuPJTmzjqjAq2MxtpLJc6ioD4pt6R2cWC81CYozf7z0M0fBJv1dxY9COoeXoPbyCJ/BxSR9TKAd0350uVawIDAQAB";
        String privateKeyStr = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJhpqP0QWk5MKzvZkOdpiCtTzGyy/gKZInz5GLfydbL9t001lhhExo0OdV2PN9OdrtSyLPQyy+pzu1vyqxQ4zNQdugG48lObOOqMCrYzG2kslzqKgPim3pHZxYLzUJijN/vPQzR8Em/V3Fj0I6h5eg9vIIn8HFJH1MoB3TfnS5VrAgMBAAECgYBeyDajSewfDRdF3QNUJjjcWPykDJwSmZDtDyvkGRBBA69egJia9wpS5k7upAYfv8vd0gBKzNwrLVWy1LK3nKsjtEF1k8pIfIbpsQ4wu3Z3O27TTWN1bZ+w+ddr8N9U2VUILenCWNIzaMeERKR2yT06Hh+mJfKinzjCmBvMi5GkQQJBAO78pYgxHLQpbEt38jcq3neUPgJR03ztN8iDOHBWXJUuDii8ZEoWnHm7wxml3Hxb02vXvQzA3Lz6G61WISI8WzMCQQCjQ0P8skNlsenJu26Sy8ROnSMZA2J2Xcz665KxvWvDZ8Led9ZI0aoJEgGWzLftnaXk7A/busfD6tM1W4SSERzpAkBekTC7VJvEGsQUlPEOrXL8pHdpGZHzVDcHrA0QPsxB58iIjAuvWYUvkoHgS0aVGwztFqBjRInUaa+8BmH0FygLAkBb5g8s/MMHSWBJ16FkLCLyBW2uNMOF0UMCsvu95Nv2AEAmrdoG304REPuoANA/ho/NDjvW+Ypc85+z1zIGbm+RAkAjzbpnD/ty8+7hX5cv2tGKhf/kwg2PToz6b9qh1hWLYFkJBkhLuN+Lrd9JJSrb1e8cQh9qrShfy9xT76FBlmB6";
        byte[] sign = SignatureUtils.sign(msg.getBytes("utf-8"), privateKeyStr);
        System.out.println("签名：\n"+ Hex.encodeHexString(sign));
        boolean verify = SignatureUtils.verify(msg.getBytes("utf-8"), publicKeyStr, sign);
        System.out.println("验签状态：\n"+verify);

    }

}
