package com.example.doctor.ui.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tejas on 2/6/17.
 */

public class Insurance implements Serializable {

    /**
     * insurance_plan : Buy One Get One Free
     * expiry_date : 2017-12-08
     * start_date : 2017-06-26
     * premium : 10
     * notes : Hands Free
     * user : []
     */

    private String insurance_plan;
    private String expiry_date;
    private String start_date;
    private int premium;
    private String notes;
    private List<?> user;

    public String getInsurance_plan() {
        return insurance_plan;
    }

    public void setInsurance_plan(String insurance_plan) {
        this.insurance_plan = insurance_plan;
    }

    public String getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(String expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public int getPremium() {
        return premium;
    }

    public void setPremium(int premium) {
        this.premium = premium;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<?> getUser() {
        return user;
    }

    public void setUser(List<?> user) {
        this.user = user;
    }
}
