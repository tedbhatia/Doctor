package com.example.doctor.ui.model;

import java.io.Serializable;

/**
 * Created by SHIVIKA NAGPAL on 13-06-2017.
 */

public class Notifications implements Serializable {

    String title, description;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}