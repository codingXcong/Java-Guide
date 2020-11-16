package cn.zgc.encryption;

import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * @author zhangguicong
 * @date 2020-11-15
 */
public class Base64Demo {
    public static void main(String[] args) {
        System.out.println(Base64.encode("a".getBytes()));
        System.out.println(Base64.encode("ab".getBytes()));
        System.out.println(Base64.encode("abc".getBytes()));
    }
}
