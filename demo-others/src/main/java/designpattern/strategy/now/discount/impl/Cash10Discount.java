package designpattern.strategy.now.discount.impl;

import designpattern.strategy.now.discount.AbstractDiscount;

public class Cash10Discount extends AbstractDiscount {
    public Cash10Discount() {
        super("返现10元");
    }

    @Override
    public double discount(double price) {
        this.finalPrice = price >= 10 ? price - 10 : 0;
        return finalPrice;
    }
}