package com.example.doctor.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.adapter.MedicinesAdapter;
import com.example.doctor.ui.adapter.My_Health_Acc_Adapter;
import com.example.doctor.ui.model.Medicines;

import java.util.ArrayList;
import java.util.List;

public class MyMedicines extends AppCompatActivity implements My_Health_Acc_Adapter.MyClickListener {

    private RecyclerView recyclerView;
    private MedicinesAdapter adapter;

    private List<Medicines> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_medicines);

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems=new ArrayList<>();


        listItems.add(0,new Medicines("Name1","1 capsule","qwerty","twice a day"));
        listItems.add(1,new Medicines("Name2","0.5 capsule","qwerty","twice a day"));
        listItems.add(2,new Medicines("Name3","0.5 capsule","qwerty","twice a day"));
        listItems.add(3,new Medicines("Name4","0.5 capsule","qwerty","twice a day"));
        listItems.add(4,new Medicines("Name5","1 capsule","qwerty","twice a day"));

        adapter=new MedicinesAdapter(this,listItems);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(int position, View v) {

        Toast.makeText(this,listItems.get(position).getMedName().toString(),Toast.LENGTH_LONG).show();

    }
}
