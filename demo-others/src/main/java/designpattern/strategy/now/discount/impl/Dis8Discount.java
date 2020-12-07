package designpattern.strategy.now.discount.impl;

import designpattern.strategy.now.discount.AbstractDiscount;

public class Dis8Discount extends AbstractDiscount {
    public Dis8Discount() {
        super("打八折");
    }

    @Override
    public double discount(double price) {
        finalPrice = price * 0.8;
        return finalPrice;
    }
}