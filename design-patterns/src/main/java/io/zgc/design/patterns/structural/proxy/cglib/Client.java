package io.zgc.design.patterns.structural.proxy.cglib;

/**
 * @author codingxcong
 * @date 2023-09-18
 */
public class Client {
    public static void main(String[] args) {
        Station proxy = CglibProxy.createProxy(new Station());
        proxy.sellTicket();
    }
}
