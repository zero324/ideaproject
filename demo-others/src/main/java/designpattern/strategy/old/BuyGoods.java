package designpattern.strategy.old;

import java.text.MessageFormat;

public class BuyGoods {
    private String goods;
    private double price;
    private double finalPrice;
    private String desc;

    public BuyGoods(String goods, double price) {
        this.goods = goods;
        this.price = price;
    }

    public double calculate(String discountType) {
        if ("dis9".equals(discountType)) {
            finalPrice = price * 0.9;
            desc = "打九折";
        } else if ("dis8".equals(discountType)) {
            finalPrice = price * 0.8;
            desc = "打八折";
        } else if ("cash10".equals(discountType)) {
            finalPrice = price >= 10 ? price - 10 : 0;
            desc = "返现10元";
        } else {
            finalPrice = price;
            desc = "不参与优惠活动";
        }
        System.out.println(MessageFormat.format("购买的物品：{0}，原始价格：{1}，{2}，最终价格为：{3}", goods, price, desc, finalPrice));
        return finalPrice;
    }
}