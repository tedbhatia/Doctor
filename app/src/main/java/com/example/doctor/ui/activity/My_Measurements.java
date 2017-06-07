package com.example.doctor.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.adapter.Measurement_Adapter;
import com.example.doctor.ui.model.Measurement_Info;

import java.util.ArrayList;
import java.util.List;

public class My_Measurements extends AppCompatActivity implements Measurement_Adapter.MyClickListener {

    private RecyclerView recyclerView;
    private Measurement_Adapter adapter;
    private List<Measurement_Info> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_measurements);
        setTitle("My Measurement");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        data = new ArrayList<>();

        prepareData();
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.measurement_list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(My_Measurements.this,1,false));
    }

    private void prepareData() {
        String[] type = {"Aplha", "Beta", "Gamma", "Theta", "Psi", "Phi", "Omega"};
        String[] date = {"Aplha", "Beta", "Gamma", "Theta", "Psi", "Phi", "Omega"};
        String[] note = {"Aplha", "Beta", "Gamma", "Theta", "Psi", "Phi", "Omega"};

        for (int i = 0; i < type.length && i < date.length && i < note.length; i++) {
            Measurement_Info current = new Measurement_Info();
            current.setType(type[i]);
            current.setDate(date[i]);
            current.setNote(note[i]);
            data.add(current);
        }
        adapter = new Measurement_Adapter(My_Measurements.this,data);
    }

    @Override
    public void onItemClick(int position, View v) {
        if(position==0){
            Toast.makeText(My_Measurements.this,"My Doctor",Toast.LENGTH_SHORT).show();
        }
        else if(position==1){
            Toast.makeText(My_Measurements.this,"My Diseases",Toast.LENGTH_SHORT).show();
        }
        else if(position==2){
            Toast.makeText(My_Measurements.this,"My Appointments",Toast.LENGTH_SHORT).show();
        }
        else if(position==3){
            Toast.makeText(My_Measurements.this,"My Medicines",Toast.LENGTH_SHORT).show();
        }
        else if(position==4){
            Toast.makeText(My_Measurements.this,"My Documents",Toast.LENGTH_SHORT).show();
        }
        else if(position==5){
            Toast.makeText(My_Measurements.this,"My Insurance",Toast.LENGTH_SHORT).show();
        }
        else if(position==6){
            Toast.makeText(My_Measurements.this,"My Measurements",Toast.LENGTH_SHORT).show();
        }
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
