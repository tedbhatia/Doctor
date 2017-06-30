package com.example.doctor.ui.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Pankaj on 6/27/2017.
 */

public class SymptomModel implements Serializable {
    private String symptom_name;
    private List<String> symptom_description;
    private List<String> symptom_tests;
    private List<String> symptom_videos;
    private List<String> possible_diseases;

    public SymptomModel(String symptom_name, List<String> symptom_description, List<String> symptom_tests, List<String> symptom_videos, List<String> possible_diseases) {
        this.symptom_name = symptom_name;
        this.symptom_description = symptom_description;
        this.symptom_tests = symptom_tests;
        this.symptom_videos = symptom_videos;
        this.possible_diseases = possible_diseases;
    }

    public String getSymptom_name() {
        return symptom_name;
    }

    public void setSymptom_name(String symptom_name) {
        this.symptom_name = symptom_name;
    }

    public List<String> getSymptom_description() {
        return symptom_description;
    }

    public void setSymptom_description(List<String> symptom_description) {
        this.symptom_description = symptom_description;
    }

    public List<String> getSymptom_tests() {
        return symptom_tests;
    }

    public void setSymptom_tests(List<String> symptom_tests) {
        this.symptom_tests = symptom_tests;
    }

    public List<String> getPossible_diseases() {
        return possible_diseases;
    }

    public void setPossible_diseases(List<String> possible_diseases) {
        this.possible_diseases = possible_diseases;
    }

    public List<String> getSymptom_videos() {
        return symptom_videos;
    }

    public void setSymptom_videos(List<String> symptom_videos) {
        this.symptom_videos = symptom_videos;
    }
}
