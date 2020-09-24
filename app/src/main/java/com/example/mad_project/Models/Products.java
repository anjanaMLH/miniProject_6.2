package com.example.mad_project.Models;

public class Products {
    private String item_name;
    private Double old_price;
    private Double new_price;
    private Integer quantity;
    private int position;

    public Products(){
        //empty constructor
    }

    public Products(int position){

        this.position = position;

    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Double getOld_price() {
        return old_price;
    }

    public void setOld_price(Double old_price) {
        this.old_price = old_price;
    }

    public Double getNew_price() {
        return new_price;
    }

    public void setNew_price(Double new_price) {
        this.new_price = new_price;
    }

    public Integer getQantity() {
        return quantity;
    }

    public void setQantity(Integer qantity) {
        this.quantity = qantity;
    }
}
