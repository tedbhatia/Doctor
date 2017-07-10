package com.example.doctor.ui.model;

import java.util.List;

/**
 * Created by tejas on 10/7/17.
 */

public class DoctorNotes {

    /**
     * doctor_note : This is a sample note
     * doctor : 2
     * user : []
     */

    private String doctor_note;
    private int doctor;
    private List<?> user;

    public String getDoctor_note() {
        return doctor_note;
    }

    public void setDoctor_note(String doctor_note) {
        this.doctor_note = doctor_note;
    }

    public int getDoctor() {
        return doctor;
    }

    public void setDoctor(int doctor) {
        this.doctor = doctor;
    }

    public List<?> getUser() {
        return user;
    }

    public void setUser(List<?> user) {
        this.user = user;
    }
}
