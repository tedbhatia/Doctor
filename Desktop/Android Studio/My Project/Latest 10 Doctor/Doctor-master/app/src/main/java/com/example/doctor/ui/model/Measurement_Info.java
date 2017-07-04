package com.example.doctor.ui.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tejas on 2/6/17.
 */

public class Measurement_Info implements Serializable{

    /**
     * blood_pressure : 120/80
     * blood_sugar : 125
     * cholesterol : 111
     * height : 5.0
     * weight : 67.0
     * user : []
     * date : 2017-06-26
     * notes : notes
     */

    private String blood_pressure;
    private String blood_sugar;
    private String cholesterol;
    private double height;
    private double weight;
    private String date;
    private String notes;
    private List<?> user;

    public String getBlood_pressure() {
        return blood_pressure;
    }

    public void setBlood_pressure(String blood_pressure) {
        this.blood_pressure = blood_pressure;
    }

    public String getBlood_sugar() {
        return blood_sugar;
    }

    public void setBlood_sugar(String blood_sugar) {
        this.blood_sugar = blood_sugar;
    }

    public String getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(String cholesterol) {
        this.cholesterol = cholesterol;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
