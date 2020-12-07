package designpattern.strategy.now.discount.impl;

import designpattern.strategy.now.discount.AbstractDiscount;

public class Dis9Discount extends AbstractDiscount {
    public Dis9Discount() {
        super("打九折");
    }

    @Override
    public double discount(double price) {
        finalPrice = price * 0.9;
        return finalPrice;
    }
}