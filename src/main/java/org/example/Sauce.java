package org.example;

public class Sauce extends Topping {

    public Sauce(Dish dish) {
        super(dish);
    }

    @Override
    public String getDescription() {
        return dish.getDescription() + " + огненный соус";
    }

    @Override
    public int getPrice() {
        return dish.getPrice() + 10;
    }
}