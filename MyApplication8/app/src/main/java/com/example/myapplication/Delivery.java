package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

public class Delivery {

    @SerializedName("id")
    private int id;

    @SerializedName("order_unique_id")
    private int order_unique_id;

    @SerializedName("contact_person")
    private String contact_person;

    @SerializedName("contact_number")
    private int contact_number;

    @SerializedName("location")
    private String location;

    @SerializedName("note")
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_unique_id() {
        return order_unique_id;
    }

    public void setOrder_unique_id(int order_unique_id) {
        this.order_unique_id = order_unique_id;
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
}

