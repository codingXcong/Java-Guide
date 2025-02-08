package io.zgc.util;

/**
 * @author zhangguicong
 * @date 2025/2/8
 */
public class TokenUtilsSample {
    /**

     @RequestMapping(value = "/session", method = RequestMethod.GET)
     public Message sessionKey() {

     String sessionKey = TokenUtils.encodeToken(
         (byte) AlgorithmType.HS256.getCode()
         , (byte) TokenType.BEARER.getCode()
         , System.currentTimeMillis()
         , sessionExpire
         , IdGenerateUtils.getUuidData()
         , null
         , Base64.decodeBase64(secret));


     return Message.build(Result.newResult()
         .property("session_key", sessionKey)
         .property("expires_in", sessionExpire / 1000));
     }

     */

}
