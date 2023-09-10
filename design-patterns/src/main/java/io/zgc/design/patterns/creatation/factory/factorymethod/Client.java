package io.zgc.design.patterns.creatation.factory.factorymethod;

/**
 * @author codingxcong
 * @date 2023-09-10
 */
public class Client {
    public static void main(String[] args) {
        AbstractCarFactory carFactory = new WulinMinCarFactory();
        AbstractCar abstractCar = carFactory.newCar();
        abstractCar.run();


        carFactory = new WulinVanCarFactory();
        AbstractCar abstractCar1 = carFactory.newCar();
        abstractCar1.run();
    }
}
