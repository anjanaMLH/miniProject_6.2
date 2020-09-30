package com.example.mad_project.Models;

public class Order {

    String food;
    String resturent;
    String email;

    public Order() {
    }

    public Order(String food, String resturent, String email) {
        this.food = food;
        this.resturent = resturent;
        this.email = email;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getResturent() {
        return resturent;
    }

    public void setResturent(String resturent) {
        this.resturent = resturent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


