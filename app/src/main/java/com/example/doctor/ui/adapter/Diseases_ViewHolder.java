package com.example.doctor.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.activity.MainActivity;
import com.example.doctor.ui.activity.ProcedureDetail;
import com.example.doctor.ui.adapter.Body_Parts_Adapter;
import com.example.doctor.ui.fragment.Symptoms_Male_Fragment;
import com.example.doctor.ui.model.Diseases;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by Aviral on 12-06-2017.
 */

public class Diseases_ViewHolder extends ChildViewHolder implements View.OnClickListener {

    private TextView male_disease_name;
    private Context context;

    public Diseases_ViewHolder(View itemView,Context context){
        super(itemView);
        male_disease_name= (TextView) itemView.findViewById(R.id.male_disease);
        itemView.findViewById(R.id.disease_layout).setOnClickListener(this);
        this.context=context;
    }
    public void setMale_disease_name(String name){
        male_disease_name.setText(name);
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(context,male_disease_name.getText(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context,ProcedureDetail.class);
        //intent.putExtra("procs",model.get(position));
        context.startActivity(intent);
    }
}
