package com.example.doctor.ui.model;

import com.bignerdranch.expandablerecyclerview.model.Parent;
import com.example.doctor.ui.model.Diseases;

import java.util.List;

/**
 * Created by Aviral on 15-06-2017.
 */

public class Body_Parts implements Parent<SymptomModel> {

    private String name;
    List<SymptomModel> symptomList;

    public Body_Parts(String name,List<SymptomModel> symptomList) {
        this.name=name;
        this.symptomList = symptomList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SymptomModel getSymptom(int position){
        return symptomList.get(position);
    }

    public SymptomModel setSymptom(int position){
        return symptomList.get(position);
    }


    @Override
    public List<SymptomModel> getChildList() {
        return symptomList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
