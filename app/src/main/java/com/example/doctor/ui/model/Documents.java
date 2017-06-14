package com.example.doctor.ui.model;

import java.io.Serializable;

/**
 * Created by tejas on 12/6/17.
 */

public class Documents implements Serializable{

    private boolean isChecked;

    private String url;
    private String docName;

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

    public boolean isChecked(){
        return isChecked;
    }
    public void toggleChecked(){
        isChecked = !isChecked;
    }

}
