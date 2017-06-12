package com.example.doctor.ui.model;

import java.io.Serializable;

/**
 * Created by tejas on 2/6/17.
 */

public class Insurance implements Serializable {
    private String plan;
    private String date;
    private String duration;
    private String notes;

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
