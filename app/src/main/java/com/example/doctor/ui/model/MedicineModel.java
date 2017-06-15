package com.example.doctor.ui.model;

import java.io.Serializable;

/**
 * Created by Aviral on 12-06-2017.
 */

public class MedicineModel implements Serializable {
    String name,usuage,overdose,side_effects,brand_names;

    public MedicineModel(String name, String usuage, String overdose, String side_effects, String brand_names) {
        this.name = name;
        this.usuage = usuage;
        this.overdose = overdose;
        this.side_effects = side_effects;
        this.brand_names = brand_names;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsuage() {
        return usuage;
    }

    public void setUsuage(String usuage) {
        this.usuage = usuage;
    }

    public String getOverdose() {
        return overdose;
    }

    public void setOverdose(String overdose) {
        this.overdose = overdose;
    }

    public String getSide_effects() {
        return side_effects;
    }

    public void setSide_effects(String side_effects) {
        this.side_effects = side_effects;
    }

    public String getBrand_names() {
        return brand_names;
    }

    public void setBrand_names(String brand_names) {
        this.brand_names = brand_names;
    }
}
