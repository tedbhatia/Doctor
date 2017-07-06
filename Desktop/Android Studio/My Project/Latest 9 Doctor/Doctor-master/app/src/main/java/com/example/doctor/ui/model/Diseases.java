package com.example.doctor.ui.model;

import java.io.Serializable;

/**
 * Created by SHIVIKA NAGPAL on 12-06-2017.
 */

public class Diseases implements Serializable {

    String diseaseName,date,notes;

    public Diseases(String diseaseName, String date, String notes) {
        this.diseaseName = diseaseName;
        this.date = date;
        this.notes = notes;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public String getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }
}
