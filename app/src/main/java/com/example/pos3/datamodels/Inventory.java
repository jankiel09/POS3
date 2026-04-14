package com.example.pos3.datamodels;

public class Inventory {
    private int id;
    private String name;
    private double price;
    private int qty;
    private String category;

    public Inventory(int id, String name, double price, int qty, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.category = category;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQty() {
        return qty;
    }

    public String getCategory() {
        return category;
    }
}
