package com.example.doctor.ui.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Aviral on 12-06-2017.
 */

public class ProcedureModel implements Serializable {


    /**
     * procedure_name : Surgery
     * procedure_description : Operation
     * possible_complication : Infected stitches
     * doctor : [2]
     * bodypart : [5]
     * symptom : [1]
     * medicine : [1]
     */

    private String procedure_name;
    private String procedure_description;
    private String possible_complication;
    private List<Integer> doctor;
    private List<Integer> bodypart;
    private List<Integer> symptom;
    private List<Integer> medicine;

    public String getProcedure_name() {
        return procedure_name;
    }

    public void setProcedure_name(String procedure_name) {
        this.procedure_name = procedure_name;
    }

    public String getProcedure_description() {
        return procedure_description;
    }

    public void setProcedure_description(String procedure_description) {
        this.procedure_description = procedure_description;
    }

    public String getPossible_complication() {
        return possible_complication;
    }

    public void setPossible_complication(String possible_complication) {
        this.possible_complication = possible_complication;
    }

    public List<Integer> getDoctor() {
        return doctor;
    }

    public void setDoctor(List<Integer> doctor) {
        this.doctor = doctor;
    }

    public List<Integer> getBodypart() {
        return bodypart;
    }

    public void setBodypart(List<Integer> bodypart) {
        this.bodypart = bodypart;
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
}
