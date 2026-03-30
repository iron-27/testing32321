package org.example;

public class Venison extends Topping{

    public Venison(Dish dish) {
        super(dish);
    }

    @Override
    public String getDescription() {
        return dish.getDescription() + " + двойная порция оленины";
    }

    @Override
    public int getPrice() {
        return dish.getPrice() + 20;
    }
}
