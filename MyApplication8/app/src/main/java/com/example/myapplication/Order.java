package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class Order {

    @SerializedName("id")
    int id;

    @SerializedName("user_id")
    String user_id;

    @SerializedName("name")
    String name;

    @SerializedName("size")
    String size;

    @SerializedName("qty")
    int qty;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
