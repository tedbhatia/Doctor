package com.example.doctor.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Aviral on 11-06-2017.
 */

public class DiseasesList implements Serializable {

    private String disease_name;
    private List<String> disease_desc;
    private List<String> disease_tests;
    private List<String> disease_video;

    public DiseasesList(String disease_name, List<String> disease_desc, List<String> disease_tests, List<String> disease_video) {
        this.disease_name = disease_name;
        this.disease_desc = disease_desc;
        this.disease_tests = disease_tests;
        this.disease_video = disease_video;
    }

    public List<String> getDisease_desc() {
        return disease_desc;
    }

    public void setDisease_desc(List<String> disease_desc) {
        this.disease_desc = disease_desc;
    }

    public List<String> getDisease_tests() {
        return disease_tests;
    }

    public void setDisease_tests(List<String> disease_tests) {
        this.disease_tests = disease_tests;
    }

    public List<String> getDisease_video() {
        return disease_video;
    }

    public void setDisease_video(List<String> disease_video) {
        this.disease_video = disease_video;
    }

    public String getName() {
        return disease_name;
    }

    public void setName(String name) {
        this.disease_name = name;
    }
}
