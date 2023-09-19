package io.zgc.design.patterns.structural.bridge;

/**
 * 线上渠道
 */
public class OnlineSale extends AbstractSale{
    public OnlineSale(String type, Integer price) {
        super(type, price);
    }
}