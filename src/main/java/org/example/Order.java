package org.example;

public class Order {
    private String time;
    private String description;
    private int price;

    public Order(String time, String description, int price) {
        this.time = time;
        this.description = description;
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
}