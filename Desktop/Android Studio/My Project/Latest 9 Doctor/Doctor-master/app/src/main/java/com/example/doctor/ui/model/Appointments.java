package com.example.doctor.ui.model;


import java.io.Serializable;

/**
 * Created by SHIVIKA NAGPAL on 06-06-2017.
 */

public class Appointments implements Serializable{
    /**
     * doctor : 1
     * user : 2
     * date : 2017-06-22
     * time : 10:02:25
     * reason : Aisehi
     * notes : Poor Panky
     */

    private int doctor;
    private int user;
    private String date;
    private String time;
    private String reason;
    private String notes;

    public int getDoctor() {
        return doctor;
    }

    public void setDoctor(int doctor) {
        this.doctor = doctor;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


//        String name,phone,address,speciality,reason,notes,date,time;
//        public Appointments(String name, String phone, String address, String speciality, String reason, String notes, String date, String time) {
//        this.name = name;
//        this.phone = phone;
//        this.address = address;
//        this.speciality = speciality;
//        this.reason = reason;
//        this.notes = notes;
//        this.date = date;
//        this.time = time;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public String getSpeciality() {
//        return speciality;
//    }
//
//    public String getReason() {
//        return reason;
//    }
//
//    public String getNotes() {
//        return notes;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public void setSpeciality(String speciality) {
//        this.speciality = speciality;
//    }
//
//    public void setReason(String reason) {
//        this.reason = reason;
//    }
//
//    public void setNotes(String notes) {
//        this.notes = notes;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }
}
