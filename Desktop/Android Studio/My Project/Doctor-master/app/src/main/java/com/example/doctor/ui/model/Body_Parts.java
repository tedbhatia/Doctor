package com.example.doctor.ui.adapter;

import com.bignerdranch.expandablerecyclerview.model.Parent;
import com.example.doctor.ui.model.Diseases;

import java.util.List;

/**
 * Created by Aviral on 15-06-2017.
 */

public class Body_Parts implements Parent<Diseases> {

    private String name;
    List<Diseases> diseasesList;

    public Body_Parts(String name,List<Diseases> diseasesList) {
        this.name=name;
        this.diseasesList = diseasesList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Diseases getDiseases(int position){
        return diseasesList.get(position);
    }

    public Diseases setDiseases(int position){
        return diseasesList.get(position);
    }


    @Override
    public List<Diseases> getChildList() {
        return diseasesList;
    }

    @Override
    public boolean isInitiallyExpanded() {
        return false;
    }
}
