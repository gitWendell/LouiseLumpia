package com.example.myapplication;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gd185082 on background/31/2019.
 */

public class Faculties {

    @SerializedName("id")
    private Integer id;

    @SerializedName("firstname")
    private String firstname;

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("department")
    private String department;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }
}
