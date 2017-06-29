package com.example.doctor.ui.model;

import java.io.Serializable;

/**
 * Created by SHIVIKA NAGPAL on 06-06-2017.
 */

public class Medicines implements Serializable {

    String medName,dosageAmount,method,date,doctorName,notes;
    int frequency;

    public Medicines(String medName, String dosageAmount, String method, int frequency, String date, String doctorName, String notes) {
        this.medName = medName;
        this.dosageAmount = dosageAmount;
        this.method = method;
        this.frequency = frequency;
        this.date = date;
        this.doctorName = doctorName;
        this.notes = notes;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getDosageAmount() {
        return dosageAmount;
    }

    public void setDosageAmount(String dosageAmount) {
        this.dosageAmount = dosageAmount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
