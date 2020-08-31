package com.adt.pojo;

public class Order {
    private Integer id;
    private String ordertime;
    private Double total;
    //实体类型
    private  User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", ordertime='" + ordertime + '\'' +
                ", total=" + total +
                ", user=" + user +
                '}';
    }

   /* @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", ordertime='" + ordertime + '\'' +
                ", total=" + total +
                '}';
    }*/
}
