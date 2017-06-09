package com.example.doctor.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
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
    private Medicines listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_medicines);

        setTitle("My Medicines");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems=new ArrayList<>();


        listItems.add(0,new Medicines("medName1","xxx","xxx","abc","yyy","12/12/12","qwerty","notes"));
        listItems.add(1,new Medicines("medName1","xxx","xxx","abc","yyy","12/12/12","qwerty","notes"));
        listItems.add(2,new Medicines("medName1","xxx","xxx","abc","yyy","12/12/12","qwerty","notes"));
        listItems.add(3,new Medicines("medName1","xxx","xxx","abc","yyy","12/12/12","qwerty","notes"));
        listItems.add(4,new Medicines("medName1","xxx","xxx","abc","yyy","12/12/12","qwerty","notes"));

        adapter=new MedicinesAdapter(this,listItems);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(int position, View v) {

        listItem=listItems.get(position);

        Intent intent=new Intent(MyMedicines.this,EditMedicines.class);
        intent.putExtra("medicine",listItem);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){

            this.finish();

        }
        else {

            Intent intent=new Intent(MyMedicines.this,EditMedicines.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}
