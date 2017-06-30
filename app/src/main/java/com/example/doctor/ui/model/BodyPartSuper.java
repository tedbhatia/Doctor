package com.example.doctor.ui.model;

import com.bignerdranch.expandablerecyclerview.model.Parent;

import java.util.List;

/**
 * Created by Pankaj on 6/29/2017.
 */

public class BodyPartSuper implements Parent<SymptomModel> {
    private String body_part_name;
    private List<SymptomModel> symptomModels;

    public BodyPartSuper(String body_part_name, List<SymptomModel> symptomModels) {
        this.body_part_name = body_part_name;
        this.symptomModels = symptomModels;
    }

    public String getBody_part_name() {
        return body_part_name;
    }

    public void setBody_part_name(String body_part_name) {
        this.body_part_name = body_part_name;
    }

    public List<SymptomModel> getSymptomModels() {
        return symptomModels;
    }

    public void setSymptomModels(List<SymptomModel> symptomModels) {
        this.symptomModels = symptomModels;
    }

    @Override
    public List<SymptomModel> getChildList() {
        return symptomModels;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
