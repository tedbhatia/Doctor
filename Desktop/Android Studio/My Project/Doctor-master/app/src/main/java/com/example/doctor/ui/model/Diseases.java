package com.example.doctor.ui.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Aviral on 11-06-2017.
 */

public class Male_Body_Parts_Diseases  {

    private String disease_name;

    public Male_Body_Parts_Diseases(String name) {
        this.disease_name = name;
    }

    public String getName() {
        return disease_name;
    }

    public void setName(String name) {
        this.disease_name = name;
    }
}
