package com.example.doctor.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doctor.R;
import com.example.doctor.ui.activity.My_Insurance;
import com.example.doctor.ui.adapter.Insurance_Adapter;
import com.example.doctor.ui.model.Insurance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tejas on 2/6/17.
 */

public class Insurance_Fragment extends Fragment{
    private RecyclerView recyclerView;
    private Insurance_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_my_insurance,container,false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.insurance_list);

        adapter = new Insurance_Adapter(getActivity(),getData());

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),1,false));

        return rootView;
    }


    public static List<Insurance> getData(){
        List<Insurance> data = new ArrayList<>();

        String[] plan = {"My Doctors", "My Diseases", "My Appointments", "My Medicines", "My Documents", "My Insurance", "My Measurements"};
        String[] duration = {"My Doctors", "My Diseases", "My Appointments", "My Medicines", "My Documents", "My Insurance", "My Measurements"};
        String[] note = {"My Doctors", "My Diseases", "My Appointments", "My Medicines", "My Documents", "My Insurance", "My Measurements"};

        for(int i=0;i<plan.length && i<duration.length && i<note.length; i++){
            Insurance current = new Insurance();
            current.setPlan(plan[i]);
            current.setDuration(duration[i]);
            current.setNote(note[i]);
            data.add(current);
        }

        return data;
    }
}
