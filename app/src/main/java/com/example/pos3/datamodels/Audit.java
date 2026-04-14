package com.example.pos3.datamodels;

public class Audit {
    private int id;
    private String action;
    private String timestamp;

    public Audit(int id, String action, String timestamp) {
        this.id = id;
        this.action = action;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
