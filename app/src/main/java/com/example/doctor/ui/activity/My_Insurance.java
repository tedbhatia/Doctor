package com.example.doctor.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

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

        recyclerView.setLayoutManager(new LinearLayoutManager(My_Insurance.this,1,false));
    }

    @Override
    public void onItemClick(int position, View v) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
