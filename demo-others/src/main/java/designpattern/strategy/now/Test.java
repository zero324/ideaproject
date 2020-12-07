package designpattern.strategy.now;

import designpattern.strategy.now.discount.impl.*;


/* 重构后：
    * 增加新的优惠方案时只需要继承抽象策略类即可，修改优惠方案时不需要修改BuyGoods类源码；
    * 代码复用也变得简单，直接复用某一个具体策略类即可；
    * BuyGoods类的calculate变得简洁，没有了原本的if分支；
*/
public class Test {
    public static void main(String[] args) {
        /*BuyGoods shopping1 = new BuyGoods("Java编程思想", 99.00, new Dis9Discount());
        shopping1.calculate();

        BuyGoods shopping2 = new BuyGoods("罗技鼠标", 66, new Dis8Discount());
        shopping2.calculate();

        BuyGoods shopping3 = new BuyGoods("苹果笔记本", 15000.00, new Cash10Discount());
        shopping3.calculate();

        BuyGoods shopping4 = new BuyGoods("佳能相机", 1900, new NoneDiscount());
        shopping4.calculate();*/


        System.out.println("中国人12".hashCode());
    }
}