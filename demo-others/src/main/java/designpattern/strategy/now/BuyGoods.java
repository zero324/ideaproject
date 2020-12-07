package designpattern.strategy.now;

import designpattern.strategy.now.discount.AbstractDiscount;

import java.text.MessageFormat;

public class BuyGoods {
    private String goods;
    private double price;
    private AbstractDiscount AbstractDiscount;

    public BuyGoods(String goods, double price, AbstractDiscount AbstractDiscount) {
        this.goods = goods;
        this.price = price;
        this.AbstractDiscount = AbstractDiscount;
    }

    public double calculate() {
        double finalPrice = AbstractDiscount.discount(this.price);
        String desc = AbstractDiscount.getDesc();
        System.out.println(MessageFormat.format("购买的物品：{0}，原始价格：{1}，{2}，最终价格为：{3}", goods, price, desc, finalPrice));
        return finalPrice;
    }
}