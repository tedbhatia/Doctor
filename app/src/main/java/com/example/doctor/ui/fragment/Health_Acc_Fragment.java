package com.example.doctor.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
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
import com.example.doctor.ui.activity.MyAppointments;
import com.example.doctor.ui.activity.MyDoctor;
import com.example.doctor.ui.activity.MyDocuments;
import com.example.doctor.ui.activity.MyDiseases;
import com.example.doctor.ui.activity.MyMedicines;
import com.example.doctor.ui.activity.My_Insurance;
import com.example.doctor.ui.activity.My_Measurements;
import com.example.doctor.ui.adapter.My_Health_Acc_Adapter;
import com.example.doctor.ui.model.My_Health_Acc_Info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tejas on 1/6/17.
 */

public class Health_Acc_Fragment extends android.support.v4.app.Fragment implements My_Health_Acc_Adapter.MyClickListener{

    private RecyclerView recyclerView;
    private My_Health_Acc_Adapter adapter;
    private List<My_Health_Acc_Info> data;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_health_acc,container,false);
        data = new ArrayList<>();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        getData(data);
        adapter = new My_Health_Acc_Adapter(getActivity(),data);

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);

        return rootView;
    }

    public static void getData(List<My_Health_Acc_Info> data){

        int[] icons={R.drawable.doctor,R.drawable.disease,R.drawable.appointment,R.drawable.pill3,R.drawable.documents,R.drawable.insurance,R.drawable.measurement};
        String[] title = {"My Doctors", "My Diseases", "My Appointments", "My Medicines", "My Documents", "My Insurance", "My Measurements"};

        for(int i=0;i<title.length && i<icons.length; i++){
            My_Health_Acc_Info current = new My_Health_Acc_Info();
            current.setIconId(icons[i]);
            current.setTitle(title[i]);
            data.add(current);
        }

    }

    @Override
    public void onItemClick(int position, View v) {
        if(position==0){
            Toast.makeText(getActivity(),"My Doctor",Toast.LENGTH_SHORT).show();
        }
        else if(position==1){
            Toast.makeText(getActivity(),"My Diseases",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getActivity(),MyDiseases.class);
            startActivity(intent);
        }
        else if(position==2){
            Toast.makeText(getActivity(),"My Appointments",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(),MyAppointments.class);
            startActivity(intent);
        }
        else if(position==3){
            Toast.makeText(getActivity(),"My Medicines",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(),MyMedicines.class);
            startActivity(intent);
        }
        else if(position==4){
            Toast.makeText(getActivity(),"My Documents",Toast.LENGTH_SHORT).show();
        }
        else if(position==5){
            Toast.makeText(getActivity(),"My Insurance",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(),My_Insurance.class);
            startActivity(intent);
        }
        else if(position==6){
            Toast.makeText(getActivity(),"My Measurements",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(),My_Measurements.class);
            startActivity(intent);
        }
    }
}