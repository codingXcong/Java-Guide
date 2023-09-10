package io.zgc.design.patterns.creatation.factory.abstractfactory;

/**
 * 分厂：VanCar
 */
public class WulinVanCarFactory extends WulinCarFactory{
    @Override
    AbstractCar newCar() {
        return new VanCar();
    }

}
