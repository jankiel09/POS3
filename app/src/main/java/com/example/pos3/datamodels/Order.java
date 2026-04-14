package com.example.pos3.datamodels;

public class Order {
    private int id;
    private String status;
    private String date;
    private String details;

    public Order(int id, String status, String date, String details) {
        this.id = id;
        this.status = status;
        this.date = date;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public String getDetails() {
        return details;
    }
}

