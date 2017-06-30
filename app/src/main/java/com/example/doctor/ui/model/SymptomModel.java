package com.example.doctor.ui.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Pankaj on 6/27/2017.
 */

public class SymptomModel implements Serializable {


    /**
     * id : 1
     * symptom_name : Hand Pain
     * bodypart : [{"bodypart":"hands","BPsymptom":[1,2]}]
     * symptom_description : Pain in the Hand
     * tests : Consult Doctors
     */

    private int id;
    private String symptom_name;
    private String symptom_description;
    private String tests;
    private List<BodypartBean> bodypart;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymptom_name() {
        return symptom_name;
    }

    public void setSymptom_name(String symptom_name) {
        this.symptom_name = symptom_name;
    }

    public String getSymptom_description() {
        return symptom_description;
    }

    public void setSymptom_description(String symptom_description) {
        this.symptom_description = symptom_description;
    }

    public String getTests() {
        return tests;
    }

    public void setTests(String tests) {
        this.tests = tests;
    }

    public List<BodypartBean> getBodypart() {
        return bodypart;
    }

    public void setBodypart(List<BodypartBean> bodypart) {
        this.bodypart = bodypart;
    }

    public static class BodypartBean {
        /**
         * bodypart : hands
         * BPsymptom : [1,2]
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
}
