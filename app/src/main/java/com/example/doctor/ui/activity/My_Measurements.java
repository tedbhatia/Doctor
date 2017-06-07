package com.example.doctor.ui.activity;

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
        String[] height = {"5.6", "5.7", "5.8", "5.9", "5.5", "5.56", "5.84"};
        String[] weight = {"65", "58", "52", "48", "68", "78", "58"};
        String[] bp = {"BP 1", "BP 2", "BP 3", "BP 4", "BP 5", "BP6 ", "BP 7"};
        String[] bSugar = {"Aplha", "Beta", "Gamma", "Theta", "Psi", "Phi", "Omega"};
        String[] cho = {"cho 1", "cho 2", "cho 3", "cho 4", "cho 5", "cho 6", "cho 7"};

        for (int i = 0; i < height.length && i < weight.length && i < bp.length; i++) {
            Measurement_Info current = new Measurement_Info();
            current.setHeight(height[i]);
            current.setWeight(weight[i]);
            current.setBloodPressure(bp[i]);
            current.setBloodSugar(bSugar[i]);
            current.setCholesterol(cho[i]);
            data.add(current);
        }
        adapter = new Measurement_Adapter(My_Measurements.this,data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onItemClick(int position, View v) {
        Intent intent = new Intent(My_Measurements.this,MeasurementsEdit.class);
        intent.putExtra("measurement",data.get(position));
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        else {
            Intent intent = new Intent(My_Measurements.this,MeasurementsEdit.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
