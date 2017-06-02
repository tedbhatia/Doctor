package com.example.doctor.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.doctor.R;
import com.example.doctor.ui.fragment.Insurance_Fragment;
import com.example.doctor.ui.fragment.Measurement_Fragment;

public class My_Measurements extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_measurements);
        setTitle("My Measurement");

        android.app.FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.measuremt_frame,new Measurement_Fragment()).commit();
    }
}
