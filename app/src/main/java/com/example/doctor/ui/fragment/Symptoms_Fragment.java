package com.example.doctor.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.doctor.R;

/**
 * Created by Aviral on 09-06-2017.
 */

public class Symptoms_Fragment extends android.support.v4.app.Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_symptoms,container,false);
        final android.support.v4.app.FragmentManager fm = getChildFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame2,new Symptoms_Male_Fragment()).commit();
        Spinner spinner = (Spinner)rootView.findViewById(R.id.spin);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    fm.beginTransaction().replace(R.id.content_frame2,new Symptoms_Male_Fragment()).commit();
                }
                else if(position==1){
                    fm.beginTransaction().replace(R.id.content_frame2,new Symptoms_Female_Fragment()).commit();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return rootView;
    }
}
