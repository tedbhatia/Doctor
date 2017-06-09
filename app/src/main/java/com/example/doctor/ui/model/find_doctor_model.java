package com.example.doctor.ui.model;


public class find_doctor_model {
    String name, brief_info, phone_number;
    int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrief_info() {
        return brief_info;
    }

    public void setBrief_info(String brief_info) {
        this.brief_info = brief_info;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public find_doctor_model(String name, String brief_info, int id, String phone_number) {
        this.name = name;
        this.brief_info = brief_info;
        this.id = id;
        this.phone_number = phone_number;
    }
}
