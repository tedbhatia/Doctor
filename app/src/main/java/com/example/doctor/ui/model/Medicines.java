package com.example.doctor.ui.model;

/**
 * Created by SHIVIKA NAGPAL on 06-06-2017.
 */

public class Medicines {

    String medName,method,frequency,unit;

    public Medicines(String name, String unit, String method, String frequency) {
        this.frequency = frequency;
        this.method = method;
        this.medName = name;
        this.unit = unit;
    }

    public String getFrequency() {
        return frequency;
    }

    public String getMethod() {
        return method;
    }

    public String getMedName() {
        return medName;
    }

    public String getUnit() {
        return unit;
    }

}
