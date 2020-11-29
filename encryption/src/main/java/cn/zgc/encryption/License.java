package cn.zgc.encryption;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class License{

    /**得到产生的私钥/公钥对 
     * @return KeyPair
     */
    public static KeyPair getKeypair(){

        //产生RSA密钥对(myKeyPair)
        KeyPairGenerator myKeyGen = null;
        try {
            // 这个myKeyGen实例就是用于：生成公钥和私钥对（而且公钥与私钥对的加密算法都是RSA算法加密）
            myKeyGen = KeyPairGenerator.getInstance("RSA");
            // 序列化
            myKeyGen.initialize(1024);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        KeyPair myKeyPair = myKeyGen.generateKeyPair();
        return myKeyPair;
    }

    /**根据私钥和信息生成签名 
     * @param privateKey
     * @param data
     * @return 签名的Base64编码
     */
    public static String getSignature(PrivateKey privateKey,String data){

        // 实例签名
        Signature sign;
        String res = "";
        try {
            // 返回MD5，rsa算法的Signature对象
            sign = Signature.getInstance("MD5WithRSA");
            // 通过传入私钥的方式初始化签名对象
            sign.initSign(privateKey);
            // 更新待签名的数据
            sign.update(data.getBytes());
            // 返回已更新的签名字节
            byte[] signSequ = sign.sign();
            // 签名的Base64编码
            res = Base64.getEncoder().encodeToString(signSequ);
        }catch(NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SignatureException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    /**验证签名 
     * @param publicKey 公钥的Base64编码 
     * @param sign 签名的Base64编码 
     * @param data 生成签名的原数据
     * @return
     */
    public static boolean verify(String publicKey, String sign, String data){
        boolean res = true;
        try {
            // 将Base64编码进行decode反编码形成byte
            byte[] keyBytes = Base64.getDecoder().decode(publicKey);
            // 将这个byte作为参数生成keyspec
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            // 实例化一个RSA签名算法的密钥工厂
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            // 生成公钥（一个使用RSA加密的算法）
            PublicKey publicK = keyFactory.generatePublic(keySpec);
            // 实例化一个signature签名，采用MD5以及RSA进行加密或者签名
            Signature signature = Signature.getInstance("MD5withRSA");
            // 通过公钥初始化验证对象
            signature.initVerify(publicK);
            // 更新要验证的数据
            signature.update(data.getBytes());
            // 验证待传入的签名
            res = signature.verify(Base64.getDecoder().decode(sign));
        }catch(Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Signature 签名算法
     * @param args
     */
    public static void main(String[] args) {

        String data = "给我签名吧！";

        /*(1)生成公钥和私钥对*/
        KeyPair keyPair = getKeypair();
        String publicKey = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
        String privateKey = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
        System.out.println("公钥：" + publicKey);
        System.out.println("私钥：" + privateKey);

        /*(2)用私钥生成签名*/
        PrivateKey pk = keyPair.getPrivate();
        String signStr = getSignature(pk,data);
        System.out.println("签名是：" + signStr);

        /*(3)验证签名*/
        System.out.println("验证签名的结果是：" + verify(publicKey,signStr,data));

    }
} 
