package com.example.doctor.ui.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Aviral on 12-06-2017.
 */

public class MedicineModel implements Serializable {

    /**
     * medicine_name : ChickenPox med
     * dosage_amt : 1ml/dose
     * method : l
     * frequency : 3
     * medicine_date : 2017-06-22
     * doctor : [1]
     * usage_instructions : Dont chat with bot
     * overdose_instructions : Don't overdose
     * possible_sideeffects : Cold
     * brand_names : Ranbaxy
     * user : [2]
     */

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

    public MedicineModel(String medicine_name, String dosage_amt, String method, int frequency, String medicine_date, String usage_instructions, String overdose_instructions, String possible_sideeffects, String brand_names, List<Integer> doctor, List<Integer> user) {
        this.medicine_name = medicine_name;
        this.dosage_amt = dosage_amt;
        this.method = method;
        this.frequency = frequency;
        this.medicine_date = medicine_date;
        this.usage_instructions = usage_instructions;
        this.overdose_instructions = overdose_instructions;
        this.possible_sideeffects = possible_sideeffects;
        this.brand_names = brand_names;
        this.doctor = doctor;
        this.user = user;
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
