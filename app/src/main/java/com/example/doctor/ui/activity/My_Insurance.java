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
        String[] plan = {"Plan 1", "Plan 2", "Plan 3", "Plan 4", "Plan 5", "Plan 6", "Plan 7"};
        String[] duration = {"1 Month", "2 Month", "3 Month", "4 Month", "5 Month", "6 Month", "7 Month"};
        String[] note = {"My Doctors", "My Diseases", "My Appointments", "My Medicines", "My Documents", "My Insurance", "My Measurements"};
        String[] date = {"01/01/2000","01/01/2001","01/01/2002","01/01/2003","01/01/2004","01/01/2006","01/01/2007"};

        for(int i=0;i<plan.length && i<duration.length && i<note.length; i++){
            Insurance current = new Insurance();
            current.setPlan(plan[i]);
            current.setDuration(duration[i]);
            current.setNote(note[i]);
            current.setDate(date[i]);
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
        Intent intent = new Intent(My_Insurance.this,InsuranceEdit.class);
        intent.putExtra("insurance",data.get(position));
        startActivity(intent);
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
