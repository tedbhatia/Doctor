package com.example.doctor.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

        data = new ArrayList<>();

        prepareData();
        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.measurement_list);
        recyclerView.setAdapter(adapter);

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

    }
}
