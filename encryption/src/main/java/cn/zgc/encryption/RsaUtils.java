package cn.zgc.encryption;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author zhangguicong
 * @date 2020-11-21
 */
public class RsaUtils {
    private static final String ALGORITHM = "RSA";

    /**
     * 公钥加密
     *
     * @param plainText 明文
     * @param publicKeyStr 公钥字符串
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(String plainText, String publicKeyStr) throws Exception {
        // 构建公钥
        PublicKey publicKey = MyKeyGenerator.genPublicKey(publicKeyStr);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 初始化为加密模式
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        return cipher.doFinal(plainText.getBytes("UTF-8"));
    }

    /**
     * 私钥加密
     *
     * @param plainText 明文
     * @param privateKeyStr 私钥字符串
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(String plainText, String privateKeyStr) throws Exception {
        // 构建私钥
        PrivateKey privateKey = MyKeyGenerator.genPrivateKey(privateKeyStr);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 初始化为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(plainText.getBytes("UTF-8"));
    }

    /**
     * 公钥解密
     *
     * @param cipherData 密文
     * @param publicKeyStr 公钥字符串
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] cipherData, String publicKeyStr) throws Exception {
        // 构建公钥
        PublicKey publicKey = MyKeyGenerator.genPublicKey(publicKeyStr);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 初始化为解密模式
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(cipherData);
    }

    /**
     * 私钥解密
     *
     * @param cipherData 密文
     * @param privateKeyStr 私钥字符串
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] cipherData, String privateKeyStr) throws Exception {
        // 构建私钥
        PrivateKey privateKey = MyKeyGenerator.genPrivateKey(privateKeyStr);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 初始化为解密模式
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(cipherData);
    }

    public static void main(String[] args) throws Exception{
        String plainText = "RSA加密";
        String privateKeyStr = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMmdzUJDDLDFl1V53Kp9fYhEmht5Lrb6zHGPlGQjkc49xFeqm5q56YHhNkMmvuOZ3bX+W27MZbahWoyToc8P/gMJKmPjeUuKg3nMrjCYwtsTtZRxiUg0d/n3eBtWGbGyG72OcUVETv1zFwq9vY0isLcGtaAYEOivDY+aPrlAn+zJAgMBAAECgYEAn1/sh7q052/qhxkDxInal8OTBrCuqtpqjkJvjRFJCv1sndgxlQftfEvpNwAD2ashxeHBqT5OESZPi9yQBjygD6JBi0O2oNjN1eUXGZDOm8JCEecYn50avSLHrEqkbMASXVi4GKpcZAZ8UHWgkibbsKEa+XNiqtoJZ0aKtLLWnmECQQDzWq/c2QpFNEJrT/DQNKzKcBavcVfytD72r9HZQZ1oOr9zhWDLi1/1JJlg6yJG9sL8h1KIMamJp8P89zoGkKTFAkEA1BfhnrTeQWmC+JVaiYfNr2xZupHmwrn3AVoByOZ40PblcRCNON16J+TUSDXzUOkCRvuy+tXgWsE8jEfTrfOQNQI/KOASqgXKs9UTUvMaf95ZJ6esJ8vh+eJMsSLXHKboyZkUj1LX71h5xbNgD+du2nyYQ9J3CmlNENIdGWBtXqztAkByeolvjpTAneDxqDv8K+8bltI/3+y8goAOnLSF6dCgigOCKbrwTKtYySzD5Ur2Cy8NfWI3/iVhFxhFZ6ecJvFZAkEAyPvUlp8MuxVzeqiD9XikuyFgvagr7AM0JGQH4vvDgqjMybWEA9rk2JCfz6rZE8cR44/bwHjB+HROTkSgF2rElg==";
        String publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCgXMFtDwNva3DvoRqfr495Ap77DiglKi7wpxXbSx1itT9oZGqcPi2QZzbrV50C0IjO/z1wGDkP+n8Nujy/pCELV+cFZfwmYLBojWs94IJ5x18Y0/7XXlmzi9jLUQhqZdwZRHIF1wPjh8CuQNLvebdxbjBPfdES65BsnBB1S2gvCwIDAQAB";

        //String privateKeyStr = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJ1uiWYPFVPuA2VJQZgcOR2tAivwddrXIbTTFG14ErlxJ4NkwBATL4A1jHGmtnBMcja8csfyBK3nee3O3JJ8aCTJwJN76hocRJX/lHbwqQKAAwxTFmyNFfs8HzseWtWBSDUJdUNWbR6n0NcQh9f3ErgmW/3/X15uk6d7HuYtf2ONAgMBAAECgYBg5ZT51RTKeLdsUt9lkRFye3bXtoPDcyAS3Mw46GuV6dhpHcy82CInZk4P8Y1epx6xI+H9wK8ynkNEpErMenfaHYqbXihccH1BTiraVma8MCeHbhALyI5mRu/eoSNRszirrsxHf9IRg44zjNLkLAuje/rsW+6fTJ1WRxZ+PYPggQJBAMy8OuaPiIchoQhOhUmnuIuvtp0j9UNHn/z50tH0dfAqaqsbSYs8pzfz1im/ADJVCSFfDEQnMYDh6HtWelNxAmECQQDE2hkGP2H4yq2O9EyKFvvi7NWqbNc7OHiy4wnETiMOPGGimJA38jr4OGY0ZLnyUqvkn+Bn/bw0zZuosn+QZcitAkAWZUzIJdFYftw/rV81NGyikyEVxXYx2ubsZGOkZep00B8FAqIj4gmkvD6kKGmcF+M8zEnqNqNaptuoikgtUoJhAkB+bkWcSaE3FZWbNzThjBmDifFBYF5sXrzGyCNBvZ2uSwG6WJpoFF/jhPW/uOHkzUwxrq2GnOUAVP4gv2RG7diRAkAaHF0Nh87tuHINEYyb1hgqmgswoRTRcXnsQ8IFDRSwTnwUB1bfHorA1NOpvd+zp9tP2XDUzARVOVJJH3mK49uB";
        //String publicKeyStr  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdbolmDxVT7gNlSUGYHDkdrQIr8HXa1yG00xRteBK5cSeDZMAQEy+ANYxxprZwTHI2vHLH8gSt53ntztySfGgkycCTe+oaHESV/5R28KkCgAMMUxZsjRX7PB87HlrVgUg1CXVDVm0ep9DXEIfX9xK4Jlv9/19ebpOnex7mLX9jjQIDAQAB";

        byte[] cipherData = encryptByPrivateKey(plainText, privateKeyStr);
        System.out.println("privatekey encrypt: " + Base64.encodeBase64String(cipherData));
        byte[] bytes = decryptByPublicKey(cipherData, publicKeyStr);
        System.out.println("publickey decrypt: "+ new String(bytes, "utf-8"));


        byte[] cipherData1 = encryptByPublicKey(plainText, publicKeyStr);
        System.out.println("publickey encrypt: " + Base64.encodeBase64String(cipherData1));
        byte[] bytes1 = decryptByPrivateKey(cipherData1, privateKeyStr);
        System.out.println("privatekey decrypt: "+ new String(bytes1, "utf-8"));
    }

}
