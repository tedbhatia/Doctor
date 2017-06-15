package com.example.doctor.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.example.doctor.R;
import com.example.doctor.ui.model.DiseasesList;

/**
 * Created by Aviral on 12-06-2017.
 */

public class Diseases_ViewHolder extends ChildViewHolder {

    private TextView disease_name;

    public TextView getDisease_name() {
        return disease_name;
    }

    public void setDisease_name(TextView disease_name) {
        this.disease_name = disease_name;
    }

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public Diseases_ViewHolder(@NonNull View itemView) {
        super(itemView);
        disease_name = (TextView) itemView.findViewById(R.id.disease_name);
    }
    public void bind(DiseasesList diseases){
        disease_name.setText(diseases.getName());
    }
}
