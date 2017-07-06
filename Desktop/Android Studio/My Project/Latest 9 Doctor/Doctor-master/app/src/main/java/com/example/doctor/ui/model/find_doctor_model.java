package com.example.doctor.ui.model;


import java.io.Serializable;
import java.util.List;

public class find_doctor_model implements Serializable{

    /**
     * doctor_name : Dr 1
     * doctor_phone_number :
     * doctor_description : Cardio
     * doctor_address : a,A
     * doctor_speciality : CARDIOLOGIST
     * doctor_timings : 06 AM to 06 PM
     * doctor_pic : http://192.168.10.25:8000/doctors_api/doctors/Arp4x.jpg
     * user : [2]
     */

    private String doctor_name;
    private String doctor_phone_number;
    private String doctor_description;
    private String doctor_address;
    private String doctor_speciality;
    private String doctor_timings;
    private String doctor_pic;
    private List<Integer> user;

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDoctor_phone_number() {
        return doctor_phone_number;
    }

    public void setDoctor_phone_number(String doctor_phone_number) {
        this.doctor_phone_number = doctor_phone_number;
    }

    public String getDoctor_description() {
        return doctor_description;
    }

    public void setDoctor_description(String doctor_description) {
        this.doctor_description = doctor_description;
    }

    public String getDoctor_address() {
        return doctor_address;
    }

    public void setDoctor_address(String doctor_address) {
        this.doctor_address = doctor_address;
    }

    public String getDoctor_speciality() {
        return doctor_speciality;
    }

    public void setDoctor_speciality(String doctor_speciality) {
        this.doctor_speciality = doctor_speciality;
    }

    public String getDoctor_timings() {
        return doctor_timings;
    }

    public void setDoctor_timings(String doctor_timings) {
        this.doctor_timings = doctor_timings;
    }

    public String getDoctor_pic() {
        return doctor_pic;
    }

    public void setDoctor_pic(String doctor_pic) {
        this.doctor_pic = doctor_pic;
    }

    public List<Integer> getUser() {
        return user;
    }

    public void setUser(List<Integer> user) {
        this.user = user;
    }
}
