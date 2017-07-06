package com.example.doctor.ui.model;

import java.io.Serializable;

/**
 * Created by tejas on 12/6/17.
 */

public class Documents implements Serializable{
    private boolean isChecked;
    private String url;
    private String docName;

    public Documents() {
    }

    public boolean getChecked(){
        return isChecked;
    }
    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public void toggleChecked(){
        isChecked = !isChecked;
    }
}
