package com.example.doctor.ui.model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by Aviral on 09-06-2017.
 */

public class Body_Parts extends ExpandableGroup{
    private String male_body_parts;

    public Body_Parts(String title, List items) {
        super(title, items);
    }


    public String get_body_parts() {
        return male_body_parts;
    }

    public void set_body_parts(String male_body_parts) {
        this.male_body_parts = male_body_parts;
    }
}
