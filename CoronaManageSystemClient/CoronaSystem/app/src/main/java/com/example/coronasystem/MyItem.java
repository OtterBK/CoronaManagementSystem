package com.example.coronasystem;

import android.graphics.drawable.Drawable;

public class MyItem {

    private Drawable genderImg;
    private String id;
    private String date;
    private String address;

    public Drawable getIcon() {
        return genderImg;
    }

    public void setIcon(Drawable genderImg) {
        this.genderImg = genderImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}