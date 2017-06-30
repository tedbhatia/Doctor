package com.example.doctor.ui.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Aviral on 15-06-2017.
 */

public class Body_Parts implements Serializable{
    /**
     * bodypart : head
     * BPsymptom : [1]
     */

    private String bodypart;
    private List<Integer> BPsymptom;

    public String getBodypart() {
        return bodypart;
    }

    public void setBodypart(String bodypart) {
        this.bodypart = bodypart;
    }

    public List<Integer> getBPsymptom() {
        return BPsymptom;
    }

    public void setBPsymptom(List<Integer> BPsymptom) {
        this.BPsymptom = BPsymptom;
    }
}
