package com.example.doctor.ui.model;

import java.io.Serializable;

/**
 * Created by Aviral on 12-06-2017.
 */

public class MedicineModel implements Serializable {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MedicineModel(String name) {
        this.name = name;
    }

}
