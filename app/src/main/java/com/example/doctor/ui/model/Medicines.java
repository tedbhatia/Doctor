package com.example.doctor.ui.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SHIVIKA NAGPAL on 06-06-2017.
 */

public class Medicines implements Serializable {

    /**
     * id : 1
     * medicine_name : Paracetamol
     * dosage_amt : 160 mg ; 80 mg/0.8 mL
     * method : t
     * frequency : 1
     * medicine_date : 2017-07-07
     * doctor : [4]
     * usage_instructions :
     * overdose_instructions :
     * possible_sideeffects :
     * brand_names :
     * user : [1,5,10]
     */

    private int id;
    private String medicine_name;
    private String dosage_amt;
    private String method;
    private int frequency;
    private String medicine_date;
    private String usage_instructions;
    private String overdose_instructions;
    private String possible_sideeffects;
    private String brand_names;
    private List<Integer> doctor;
    private List<Integer> user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public String getDosage_amt() {
        return dosage_amt;
    }

    public void setDosage_amt(String dosage_amt) {
        this.dosage_amt = dosage_amt;
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

    public String getMedicine_date() {
        return medicine_date;
    }

    public void setMedicine_date(String medicine_date) {
        this.medicine_date = medicine_date;
    }

    public String getUsage_instructions() {
        return usage_instructions;
    }

    public void setUsage_instructions(String usage_instructions) {
        this.usage_instructions = usage_instructions;
    }

    public String getOverdose_instructions() {
        return overdose_instructions;
    }

    public void setOverdose_instructions(String overdose_instructions) {
        this.overdose_instructions = overdose_instructions;
    }

    public String getPossible_sideeffects() {
        return possible_sideeffects;
    }

    public void setPossible_sideeffects(String possible_sideeffects) {
        this.possible_sideeffects = possible_sideeffects;
    }

    public String getBrand_names() {
        return brand_names;
    }

    public void setBrand_names(String brand_names) {
        this.brand_names = brand_names;
    }

    public List<Integer> getDoctor() {
        return doctor;
    }

    public void setDoctor(List<Integer> doctor) {
        this.doctor = doctor;
    }

    public List<Integer> getUser() {
        return user;
    }

    public void setUser(List<Integer> user) {
        this.user = user;
    }
}
