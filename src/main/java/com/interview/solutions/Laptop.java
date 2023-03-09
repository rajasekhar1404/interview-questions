package com.interview.solutions;
public class Laptop {}
class Lenovo extends Laptop {}
class BuyLaptop {
    public Laptop getLaptop() {
        return new Laptop();
    }
}
class BuyLenovoLaptop extends BuyLaptop {
    @Override
    public Lenovo getLaptop() {
        return new Lenovo();
    }
}