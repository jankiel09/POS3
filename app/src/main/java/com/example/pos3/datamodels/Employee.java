package com.example.pos3.datamodels;

public class Employee {
    private int id;
    private String user;
    private String pass;
    private String role;

    public Employee(int id, String user, String pass, String role) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getRole() {
        return role;
    }
}
