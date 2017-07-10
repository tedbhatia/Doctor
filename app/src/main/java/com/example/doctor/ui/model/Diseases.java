package com.example.doctor.ui.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by SHIVIKA NAGPAL on 12-06-2017.
 */

public class Diseases implements Serializable {

    /**
     * disease_name : diesesad 12
     * disease_date : 2014-11-30
     * symptom : [2,3,4]
     * medicine : [1]
     * procedure : [1]
     * user : [1,2]
     */

    private String disease_name;
    private String disease_date;
    private List<Integer> symptom;
    private List<Integer> medicine;
    private List<Integer> procedure;
    private List<Integer> user;

    public String getDisease_name() {
        return disease_name;
    }

    public void setDisease_name(String disease_name) {
        this.disease_name = disease_name;
    }

    public String getDisease_date() {
        return disease_date;
    }

    public void setDisease_date(String disease_date) {
        this.disease_date = disease_date;
    }

    public List<Integer> getSymptom() {
        return symptom;
    }

    public void setSymptom(List<Integer> symptom) {
        this.symptom = symptom;
    }

    public List<Integer> getMedicine() {
        return medicine;
    }

    public void setMedicine(List<Integer> medicine) {
        this.medicine = medicine;
    }

    public List<Integer> getProcedure() {
        return procedure;
    }

    public void setProcedure(List<Integer> procedure) {
        this.procedure = procedure;
    }

    public List<Integer> getUser() {
        return user;
    }

    public void setUser(List<Integer> user) {
        this.user = user;
    }
}
