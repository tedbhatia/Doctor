package com.example.doctor.ui.activity;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.adapter.ExpandableListAdapter;
import com.example.doctor.ui.model.DiseasesList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiseaseDetail extends AppCompatActivity implements View.OnClickListener {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    DiseasesList diseases;
    HashMap<String,String> listDataChild;
    TextView share;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disease_detail);
        setTitle("Disease Details");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(getIntent().hasExtra("diseasedetail")){
            diseases = (DiseasesList) getIntent().getSerializableExtra("diseasedetail");
            prepareListData();
        }

        share = (TextView) findViewById(R.id.share);
        expListView = (ExpandableListView) findViewById(R.id.dis_detail_listview);
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        share.setOnClickListener(this);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });
        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collapsed listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if (groupPosition == 2) {
                    if (childPosition == 0) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=j4dRiaNf39M")));
                    } else if (childPosition == 1) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=9E0xxCROnqY")));
                    }
                }
                return true;
            }
        });
    }

    private void prepareListData() {
    /*    listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        listDataHeader.add("Description");
        listDataHeader.add("Medicines");
        listDataHeader.add("Symptoms");
        listDataHeader.add("Procedures");
        listDataChild.put(listDataHeader.get(0), diseases.getDisease_description()); // Header, Child data
        listDataChild.put(listDataHeader.get(1), diseases.getDisease_medicine());
        listDataChild.put(listDataHeader.get(2), diseases.getDisease_symptoms());
        listDataChild.put(listDataHeader.get(2), diseases.getDisease_procedure());*/
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"emailaddress@example.com"});
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "This is Subject");
            sendIntent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }


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

