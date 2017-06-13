package com.example.doctor.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doctor.R;
import com.example.doctor.ui.adapter.Body_Parts_Adapter;
import com.example.doctor.ui.model.Body_Parts;
import com.example.doctor.ui.model.Diseases;
import com.example.doctor.ui.model.DiseasesList;
import com.thoughtbot.expandablerecyclerview.listeners.GroupExpandCollapseListener;
import com.thoughtbot.expandablerecyclerview.listeners.OnGroupClickListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aviral on 09-06-2017.
 */

public class Symptoms_Female_Fragment extends android.support.v4.app.Fragment {
    private RecyclerView mRecyclerView;
    public Body_Parts_Adapter bodyPartsAdapter;
    private List<Body_Parts> body_parts;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View roootView = inflater.inflate(R.layout.symptoms_child,container,false);

        mRecyclerView=(RecyclerView)roootView.findViewById(R.id.symptoms_recycler);
        getMale_Body_Parts();
        bodyPartsAdapter =new Body_Parts_Adapter(body_parts,getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(bodyPartsAdapter);


        return roootView;
    }

    public void getMale_Body_Parts() {
        body_parts =new ArrayList<>(6);
        for(int i = 0;i<6;i++){
            List<DiseasesList> diseases=new ArrayList<>(3);
            diseases.add(new DiseasesList("Hand Pain"));
            diseases.add(new DiseasesList("Hand Fracture"));
            diseases.add(new DiseasesList("Hand Burns"));
            body_parts.add(new Body_Parts("Hair" +i,diseases));
        }

    }
}
