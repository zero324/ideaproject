package designpattern.strategy.now.discount.impl;

import designpattern.strategy.now.discount.AbstractDiscount;

public class NoneDiscount extends AbstractDiscount {
    public NoneDiscount() {
        super("不参与优惠活动");
    }

    @Override
    public double discount(double price) {
        finalPrice = price;
        return finalPrice;
    }
}