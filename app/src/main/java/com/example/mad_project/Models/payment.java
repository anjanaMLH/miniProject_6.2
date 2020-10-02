package com.example.mad_project.Models;

public class payment {
    private String name;
    private String cardnumber;
    private String cvcnumber;
    private String expiration;

    public payment() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getCvcnumber() {
        return cvcnumber;
    }

    public void setCvcnumber(String cvcnumber) {
        this.cvcnumber = cvcnumber;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
}

