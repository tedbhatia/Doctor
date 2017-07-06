package com.example.doctor.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doctor.R;
import com.example.doctor.ui.model.Male_Body_Parts;
import com.example.doctor.ui.model.Male_Body_Parts_Diseases;
import com.example.doctor.ui.viewholders.Male_Body_Parts_Viewholder;
import com.example.doctor.ui.viewholders.Male_Diseases_ViewHolder;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by Aviral on 12-06-2017.
 */

public class Male_Body_Parts_Adapter extends ExpandableRecyclerViewAdapter<Male_Body_Parts_Viewholder,Male_Diseases_ViewHolder> {


    public Male_Body_Parts_Adapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }




    @Override
    public Male_Body_Parts_Viewholder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_male_bodyparts,parent,false);
        return new Male_Body_Parts_Viewholder(view);
    }

    @Override
    public Male_Diseases_ViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_male_bodyparts_diseases,parent,false);
        return new Male_Diseases_ViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(Male_Diseases_ViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        Male_Body_Parts_Diseases diseases = (Male_Body_Parts_Diseases) group.getItems().get(childIndex);

        holder.setMale_disease_name(diseases.getName());
    }

    @Override
    public void onBindGroupViewHolder(Male_Body_Parts_Viewholder holder, int flatPosition, ExpandableGroup group) {
        holder.setMale_body_parts(group.getTitle());
    }
}
