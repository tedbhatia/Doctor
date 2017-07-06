package com.example.doctor.ui.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.activity.MainActivity;
import com.example.doctor.ui.fragment.Symptoms_Male_Fragment;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by Aviral on 12-06-2017.
 */

public class Male_Diseases_ViewHolder extends ChildViewHolder {

    private TextView male_disease_name;

    public Male_Diseases_ViewHolder(View itemView) {
        super(itemView);
        male_disease_name= (TextView) itemView.findViewById(R.id.male_disease);
    }
    public void setMale_disease_name(String name){
        male_disease_name.setText(name);
    }


}
