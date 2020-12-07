package designpattern.strategy.now.discount;

public abstract class AbstractDiscount {
    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    protected double finalPrice;
    protected String desc;

    public AbstractDiscount(String desc) {
        this.desc = desc;
    }

    public abstract double discount(double price);
}