package com.denis.myapplication.data;

import com.google.gson.annotations.SerializedName;

public class Speciality {

    @SerializedName("specialty_id")
    private int id;
    private String name;

    public Speciality(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
