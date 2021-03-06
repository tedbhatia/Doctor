package com.example.doctor.ui.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tejas on 27/6/17.
 */

public class AppointmentSuper implements Serializable {
    private String docsName;
    private String phone;
    private String address;
    private String spec;
    private String date;
    private String time;
    private String reason;
    private String notes;

    public AppointmentSuper(String docsName, String phone, String address, String spec, String date, String time, String reason, String notes) {
        this.docsName = docsName;
        this.phone = phone;
        this.address = address;
        this.spec = spec;
        this.date = date;
        this.time = time;
        this.reason = reason;
        this.notes = notes;
    }

    public String getDocsName() {
        return docsName;
    }

    public void setDocsName(String docsName) {
        this.docsName = docsName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
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
}
