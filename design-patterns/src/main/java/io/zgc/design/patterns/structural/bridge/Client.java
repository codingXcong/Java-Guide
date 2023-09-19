package io.zgc.design.patterns.structural.bridge;

/**
 * @author codingxcong
 * @date 2023-09-19
 */
public class Client {
    public static void main(String[] args) {
        IPhone iPhone = new IPhone();
        iPhone.setSale(new StudentSale("学生",1));


        String phone = iPhone.getPhone();
        System.out.println(phone);
    }
}
