package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class UsersOrderInformation {

    @SerializedName("order_id")
    private int order_id;

    @SerializedName("user_name")
    private String user_name;

    @SerializedName("order_name")
    private String order_name;

    @SerializedName("size")
    private String size;

    @SerializedName("qty")
    private int qty;

    @SerializedName("contact_person")
    private String contact_person;

    @SerializedName("contact_number")
    private int contact_number;

    @SerializedName("location")
    private String location;

    @SerializedName("note")
    private String note;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
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

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public int getContact_number() {
        return contact_number;
    }

    public void setContact_number(int contact_number) {
        this.contact_number = contact_number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}
