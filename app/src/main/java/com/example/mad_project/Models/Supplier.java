package com.example.mad_project.Models;

public class Supplier {

    private String full_name;
    private String address;
    private String e_mail;
    private String password;
    private String re_password;

    public void setRe_password(String re_password) {
        this.re_password = re_password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getRe_password() {
        return re_password;
    }

    public String getPassword() {
        return password;
    }

    public String getE_mail() {
        return e_mail;
    }

    public String getAddress() {
        return address;
    }

    public String getFull_name() {
        return full_name;
    }
}
