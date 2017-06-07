package com.example.doctor.ui.model;

import java.io.Serializable;

/**
 * Created by tejas on 2/6/17.
 */

public class Measurement_Info implements Serializable{
    private String note;
    private String type;
    private String date;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
