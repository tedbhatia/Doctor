package com.example.doctor.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.doctor.R;
import com.example.doctor.ui.adapter.MapDoctorAdapter;
import com.example.doctor.ui.model.MapsDoctorModel;

import java.util.ArrayList;
import java.util.List;

public class MapsList extends AppCompatActivity {


    private RecyclerView recyclerView;
    private MapDoctorAdapter mapDoctorAdapter;
    List<MapsDoctorModel> model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_list);
        setTitle("Nearby Doctors");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        model = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.docListMap);

        if (getIntent().hasExtra("list")) {

            model = (List<MapsDoctorModel>) getIntent().getSerializableExtra("list");

            initRecyclerView();
        }


    }

  /*  private void prepareData() {
        GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
        model = getNearbyPlacesData.GetPlacesList();
    }*/

    private void initRecyclerView() {


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);

        mapDoctorAdapter = new MapDoctorAdapter(this, model);
        //mapDoctorAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(mapDoctorAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
