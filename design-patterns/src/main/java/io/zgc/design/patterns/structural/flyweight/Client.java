package io.zgc.design.patterns.structural.flyweight;


/**
 *
 */
public class Client {

    public static void main(String[] args) {

        //1、我
        AbstractWaitressFlyweight waitress = ZuDao.getWaitress("");
        waitress.service();
        System.out.println(waitress);

    }
}
