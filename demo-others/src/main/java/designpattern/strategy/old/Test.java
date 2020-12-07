package designpattern.strategy.old;


/*
    这样的代码可以解决问题，但是从代码设计的角度还是存在一些问题

            * 增加或者修改打折方案时必须修改 BuyGoods 类源代码，违反了面向对象设计的 "开闭原则"，代码的灵活性和扩展性较差。
            * 打折方案代码聚合在一起，如果其他项目需要重用某个打折方案的代码，只能复制粘贴对应代码，无法以类组件的方式进行重用，代码的复用性差。
            * BuyGoods 类的 calculate() 方法随着优惠方案的增多会非常庞大，代码中会出现很多if分支，可维护性差。

    此时，我们可以使用策略模式对 BuyGoods 类进行重构，将打折方案逻辑（算法）的定义和使用分离。
    抽象策略类 AbstractDiscount，它是所有具体打折方案（算法）的父类，定义了一个 `discount` 抽象方法
    */
public class Test {
    public static void main(String[] args) {
        BuyGoods buyGoods1 = new BuyGoods("Java编程思想", 99.00);
        buyGoods1.calculate("dis9");

        BuyGoods buyGoods2 = new BuyGoods("罗技鼠标", 66 );
        buyGoods2.calculate("dis8");

        BuyGoods buyGoods3 = new BuyGoods("苹果笔记本", 15000.00);
        buyGoods3.calculate("cash10");

        BuyGoods buyGoods4 = new BuyGoods("佳能相机", 1900);
        buyGoods4.calculate(null);
    }
}