package com.zk.mall.entity;

public class recommend {
    private int userId;
    private int milk;
    private int meat;
    private int wine;
    private int blanket;

    public recommend() {
    }

    public recommend(int userId, int milk, int meat, int wine, int blanket) {
        this.userId = userId;
        this.milk = milk;
        this.meat = meat;
        this.wine = wine;
        this.blanket = blanket;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public int getMeat() {
        return meat;
    }

    public void setMeat(int meat) {
        this.meat = meat;
    }

    public int getWine() {
        return wine;
    }

    public void setWine(int wine) {
        this.wine = wine;
    }

    public int getBlanket() {
        return blanket;
    }

    public void setBlanket(int blanket) {
        this.blanket = blanket;
    }
}
