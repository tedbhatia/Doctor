package com.example.doctor.ui.model;


import java.io.Serializable;

import de.hdodenhof.circleimageview.CircleImageView;

public class find_doctor_model implements Serializable{
    private String name, description, mobile_number, speciality, address, timings;
    private int id;

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public find_doctor_model(String name, String description, String mobile_number, String speciality, String address, String timings, int id) {
        this.name = name;
        this.description = description;
        this.mobile_number = mobile_number;
        this.speciality = speciality;
        this.address = address;
        this.timings = timings;
        this.id = id;
    }
}
