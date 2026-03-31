package org.example;

public abstract class Topping implements Dish {

    protected Dish dish;

    public Topping(Dish dish) {
        this.dish = dish;
    }

    @Override
    public String getDescription() {
        return dish.getDescription();
    }

    @Override
    public int getPrice() {
        return dish.getPrice();
    }
}