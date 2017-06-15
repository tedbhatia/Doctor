package com.example.doctor.ui.model;

import com.bignerdranch.expandablerecyclerview.model.Parent;
import com.example.doctor.ui.model.Diseases;

import java.util.List;

/**
 * Created by Aviral on 15-06-2017.
 */

public class Body_Parts implements Parent<DiseasesList> {

    private String name;
    List<DiseasesList> diseasesList;

    public Body_Parts(String name,List<DiseasesList> diseasesList) {
        this.name=name;
        this.diseasesList = diseasesList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DiseasesList getDiseases(int position){
        return diseasesList.get(position);
    }

    public DiseasesList setDiseases(int position){
        return diseasesList.get(position);
    }


    @Override
    public List<DiseasesList> getChildList() {
        return diseasesList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
