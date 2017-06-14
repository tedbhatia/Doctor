package com.example.doctor.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doctor.R;
import com.example.doctor.ui.model.Body_Parts;
import com.example.doctor.ui.model.Diseases;
import com.example.doctor.ui.model.DiseasesList;
import com.thoughtbot.expandablecheckrecyclerview.models.CheckedExpandableGroup;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by Aviral on 12-06-2017.
 */

public class Body_Parts_Adapter extends ExpandableRecyclerViewAdapter<Body_Parts_Viewholder,Diseases_ViewHolder> {

    private Context context;

    @Override
    public List<? extends ExpandableGroup> getGroups() {
        return super.getGroups();
    }

    public Body_Parts_Adapter(List<? extends ExpandableGroup> groups,Context context) {
        super(groups);
        this.context=context;
    }
    @Override
    public Body_Parts_Viewholder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_bodyparts,parent,false);
        return new Body_Parts_Viewholder(view);
    }

    @Override
    public Diseases_ViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_diseases,parent,false);
        return new Diseases_ViewHolder(view,context);
    }

    @Override
    public void onBindChildViewHolder(Diseases_ViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        DiseasesList diseases = (DiseasesList) group.getItems().get(childIndex);

        holder.setMale_disease_name(diseases.getName());

    }

    @Override
    public void onBindGroupViewHolder(Body_Parts_Viewholder holder, int flatPosition, ExpandableGroup group) {
        holder.setMale_body_parts(group.getTitle());
    }

}
