package com.example.doctor.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.doctor.R;

import static com.example.doctor.ui.activity.MainActivity.navigationView;

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

        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {

                    Health_Acc_Fragment health_acc_fragment = new Health_Acc_Fragment();

                    navigationView.getMenu().getItem(0).setChecked(true);

                    FragmentTransaction transaction=getFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_frame,health_acc_fragment);

                    transaction.commit();


                    return true;
                }
                return false;
            }
        });


        return rootView;
    }
}
