package com.example.doctor.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.activity.MainActivity;
import com.example.doctor.ui.adapter.My_Health_Acc_Adapter;
import com.example.doctor.ui.model.My_Health_Acc_Info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tejas on 1/6/17.
 */

public class Health_Acc_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private My_Health_Acc_Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_health_acc,container,false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_list);

        adapter = new My_Health_Acc_Adapter(getActivity(),getData());

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        return rootView;
    }

    public static List<My_Health_Acc_Info> getData(){
        List<My_Health_Acc_Info> data = new ArrayList<>();

        int[] icons={R.drawable.my_doc_96,R.drawable.my_documents_96,R.drawable.my_appointments_96,R.drawable.my_med_96,R.drawable.my_documents_96,R.drawable.my_insurance_96,R.drawable.scale_96};
        String[] title = {"My Doctors", "My Diseases", "My Appointments", "My Medicines", "My Documents", "My Insurance", "My Measurements"};

        for(int i=0;i<title.length && i<icons.length; i++){
            My_Health_Acc_Info current = new My_Health_Acc_Info();
            current.setIconId(icons[i]);
            current.setTitle(title[i]);
            data.add(current);
        }

        return data;
    }
}
