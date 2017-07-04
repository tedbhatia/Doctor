package com.example.doctor.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.doctor.ui.activity.MedicineDetail;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Aviral on 11-06-2017.
 */

public class DiseasesList implements Serializable {

    private String disease_name;
    private List<String> disease_description;
    private List<String> disease_medicine;
    private List<String> disease_symptoms;
    private List<String> disease_procedure;

    public DiseasesList(String disease_name, List<String> disease_description, List<String> disease_medicine, List<String> disease_symptoms, List<String> disease_procedure) {
        this.disease_name = disease_name;
        this.disease_description = disease_description;
        this.disease_medicine = disease_medicine;
        this.disease_symptoms = disease_symptoms;
        this.disease_procedure = disease_procedure;
    }

    public String getDisease_name() {
        return disease_name;
    }

    public void setDisease_name(String disease_name) {
        this.disease_name = disease_name;
    }

    public List<String> getDisease_description() {
        return disease_description;
    }

    public void setDisease_description(List<String> disease_description) {
        this.disease_description = disease_description;
    }

    public List<String> getDisease_medicine() {
        return disease_medicine;
    }

    public void setDisease_medicine(List<String> disease_medicine) {
        this.disease_medicine = disease_medicine;
    }

    public List<String> getDisease_symptoms() {
        return disease_symptoms;
    }

    public void setDisease_symptoms(List<String> disease_symptoms) {
        this.disease_symptoms = disease_symptoms;
    }

    public List<String> getDisease_procedure() {
        return disease_procedure;
    }

    public void setDisease_procedure(List<String> disease_procedure) {
        this.disease_procedure = disease_procedure;
    }
}
