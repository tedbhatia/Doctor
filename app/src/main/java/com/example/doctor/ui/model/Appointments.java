package com.example.doctor.ui.model;


/**
 * Created by SHIVIKA NAGPAL on 06-06-2017.
 */

public class Appointments {

    String name,reason,notes,date,time;

    public Appointments(String name, String date, String time, String reason, String notes) {
        this.date = date;
        this.name = name;
        this.notes = notes;
        this.reason = reason;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public String getReason() {
        return reason;
    }

    public String getTime() {
        return time;
    }

}
