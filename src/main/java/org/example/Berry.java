package org.example;

public class Berry extends Topping {

    public Berry(Dish dish) {
        super(dish);
    }

    @Override
    public String getDescription() {
        return dish.getDescription() + " + снежные ягоды";
    }

    @Override
    public int getPrice() {
        return dish.getPrice() + 6;
    }
}
