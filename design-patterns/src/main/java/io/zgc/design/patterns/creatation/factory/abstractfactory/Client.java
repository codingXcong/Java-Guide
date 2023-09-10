package io.zgc.design.patterns.creatation.factory.abstractfactory;

/**
 * @author codingxcong
 * @date 2023-09-10
 */
public class Client {
    public static void main(String[] args) {
        WulinFactory wulinFactory = new WulinWuHanMaskFactory();
        AbstractCar abstractCar = wulinFactory.newCar();

        AbstractMask abstractMask = wulinFactory.newMask();
        abstractMask.protectedMe();


        wulinFactory = new WulinHangZhouMaskFactory();
        AbstractMask abstractMask1 = wulinFactory.newMask();
        abstractMask1.protectedMe();
    }
}
