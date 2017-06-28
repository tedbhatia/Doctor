package com.example.doctor.ui.adapter;

import java.io.Serializable;

/**
 * Created by tejas on 28/6/17.
 */

public class MapsDoctorModel implements Serializable{

    public MapsDoctorModel(String name, String address) {
        this.name = name;
        this.address = address;
    }

    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
