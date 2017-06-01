package com.example.doctor.ui.activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.doctor.R;
import com.example.doctor.ui.adapter.Insurance_Adapter;
import com.example.doctor.ui.adapter.My_Health_Acc_Adapter;
import com.example.doctor.ui.fragment.Health_Acc_Fragment;
import com.example.doctor.ui.fragment.Insurance_Fragment;
import com.example.doctor.ui.model.Insurance;
import com.example.doctor.ui.model.My_Health_Acc_Info;

import java.util.ArrayList;
import java.util.List;

public class My_Insurance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_insurance);
        setTitle("My Insurance");

        android.app.FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.insurance_frame,new Insurance_Fragment()).commit();


    }
}
