package org.example;

public class Scone extends Topping {

    public Scone(Dish dish) {
        super(dish);
    }

    @Override
    public String getDescription() {
        return dish.getDescription() + " + Нордская лепешка";
    }

    @Override
    public int getPrice() {
        return dish.getPrice() + 7;
    }
}
