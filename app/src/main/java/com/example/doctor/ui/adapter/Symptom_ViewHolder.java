package com.example.doctor.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.example.doctor.R;
import com.example.doctor.ui.model.DiseasesList;
import com.example.doctor.ui.model.SymptomModel;

/**
 * Created by Pankaj on 6/29/2017.
 */

public class Symptom_ViewHolder extends ChildViewHolder {

    private TextView symptom_name;

    public TextView getSymptom_name() {
        return symptom_name;
    }

    public void setSymptom_name(TextView symptom_name) {
        this.symptom_name = symptom_name;
    }

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public Symptom_ViewHolder(@NonNull View itemView) {
        super(itemView);
        symptom_name = (TextView) itemView.findViewById(R.id.symptom_name);
    }
    public void bind(SymptomModel symptoms){
        symptom_name.setText(symptoms.getSymptom_name());
    }
}
