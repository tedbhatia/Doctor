package com.example.doctor.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.example.doctor.R;
import com.example.doctor.ui.activity.DiseaseDetail;
import com.example.doctor.ui.model.Body_Parts;
import com.example.doctor.ui.adapter.Body_Parts_Adapter;
import com.example.doctor.ui.model.Diseases;
import com.example.doctor.ui.model.DiseasesList;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Aviral on 09-06-2017.
 */

public class Symptoms_Female_Fragment extends android.support.v4.app.Fragment implements Body_Parts_Adapter.MyChildClickListener{
    private RecyclerView mRecyclerView;
    public Body_Parts_Adapter bodyPartsAdapter;
    private List<Body_Parts> body_parts;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View roootView = inflater.inflate(R.layout.symptoms_child, container, false);

        mRecyclerView = (RecyclerView) roootView.findViewById(R.id.symptoms_recycler);
        getMale_Body_Parts();
        bodyPartsAdapter = new Body_Parts_Adapter(getContext(), body_parts);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(bodyPartsAdapter);
        bodyPartsAdapter.setOnChildClickListener(this);
        return roootView;
    }

    public void getMale_Body_Parts() {
        List<String> description = new ArrayList<String>();
        description.add("The first thing you may think of is heart attack. Certainly chest pain is not something to ignore.");
        List<String> tests = new ArrayList<String>();
        tests.add("ECG");
        tests.add("EEG");
        List<String> videos = new ArrayList<String>();
        videos.add("Video_link1");
        videos.add("Video_link2");

        DiseasesList handpain = new DiseasesList("handpain",description,tests,videos);
        DiseasesList handpain1 = new DiseasesList("Swelling",description,tests,videos);
        DiseasesList handpain2 = new DiseasesList("Bone fracture",description,tests,videos);
        DiseasesList handpain3 = new DiseasesList("handpain",description,tests,videos);
        DiseasesList handpain4 = new DiseasesList("Paralysis",description,tests,videos);
        DiseasesList handpain5 = new DiseasesList("handpain",description,tests,videos);
        DiseasesList handpain6 = new DiseasesList("Contracture",description,tests,videos);
        DiseasesList handpain7 = new DiseasesList("Nerve injury",description,tests,videos);

        Body_Parts hand = new Body_Parts("hand", Arrays.asList(handpain, handpain1, handpain2, handpain3));
        Body_Parts hand1 = new Body_Parts("hand", Arrays.asList(handpain, handpain1, handpain4, handpain5));
        Body_Parts hand2 = new Body_Parts("hand", Arrays.asList(handpain, handpain1, handpain6, handpain7));
        body_parts = Arrays.asList(hand, hand1, hand2);
    }

    @Override
    public void onChildClickListener(int parent_positon, int child_position, View v) {
        Intent intent = new Intent(getActivity(),DiseaseDetail.class);
        intent.putExtra("diseasedetail",body_parts.get(parent_positon).getChildList().get(child_position));
        startActivity(intent);
    }
}


