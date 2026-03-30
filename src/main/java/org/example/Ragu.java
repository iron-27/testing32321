package org.example;

public class Ragu implements Dish {

    public int price=50;

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getDescription(){
        return "Нордское рагу";
    }




}
