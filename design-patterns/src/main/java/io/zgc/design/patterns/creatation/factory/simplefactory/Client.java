package io.zgc.design.patterns.creatation.factory.simplefactory;

/**
 * @author codingxcong
 * @date 2023-09-10
 */
public class Client {
    public static void main(String[] args) {
        WuLinSimpleFactory factory = new WuLinSimpleFactory();

        AbstractCar van = factory.newCar("van");
        AbstractCar mini = factory.newCar("mini");
        van.run();
        mini.run();
    }
}
