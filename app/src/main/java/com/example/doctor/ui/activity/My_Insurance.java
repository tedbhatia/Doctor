package com.example.doctor.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.adapter.Insurance_Adapter;
import com.example.doctor.ui.model.Insurance;

import java.util.ArrayList;
import java.util.List;

public class My_Insurance extends AppCompatActivity implements Insurance_Adapter.MyClickListener{

    private RecyclerView recyclerView;
    private Insurance_Adapter adapter;
    private List<Insurance> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_insurance);
        setTitle("My Insurance");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        data = new ArrayList<>();

        prepareData();
        initRecyclerView();

    }

    private void prepareData() {
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
        adapter = new Insurance_Adapter(My_Insurance.this,data);
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.insurance_list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(My_Insurance.this,1,false));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public void onItemClick(int position, View v) {
        if(position==0){
            Toast.makeText(My_Insurance.this,"My Doctor",Toast.LENGTH_SHORT).show();
        }
        else if(position==1){
            Toast.makeText(My_Insurance.this,"My Diseases",Toast.LENGTH_SHORT).show();
        }
        else if(position==2){
            Toast.makeText(My_Insurance.this,"My Appointments",Toast.LENGTH_SHORT).show();
        }
        else if(position==3){
            Toast.makeText(My_Insurance.this,"My Medicines",Toast.LENGTH_SHORT).show();
        }
        else if(position==4){
            Toast.makeText(My_Insurance.this,"My Documents",Toast.LENGTH_SHORT).show();
        }
        else if(position==5){
            Toast.makeText(My_Insurance.this,"My Insurance",Toast.LENGTH_SHORT).show();
        }
        else if(position==6){
            Toast.makeText(My_Insurance.this,"My Measurements",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        else {
            Intent intent = new Intent(My_Insurance.this,InsuranceEdit.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
