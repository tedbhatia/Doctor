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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DiseaseDetail extends AppCompatActivity implements View.OnClickListener {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    TextView share;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disease_detail);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        toolbar.setTitlzzzzze("Chest pain");
        setSupportActionBar(toolbar);*/
        //toolbar.setTitle("Chest Pain");
        //toolbar.setTitle("My title");
        //setSupportActionBar(toolbar);
        setTitle("Disease Details");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        prepareListData();
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
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
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

        // Listview Group collasped listener
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
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        listDataHeader.add("Discription");
        listDataHeader.add("Tests");
        listDataHeader.add("Related Videos");

        List<String> description = new ArrayList<String>();
        description.add("The first thing you may think of is heart attack. Certainly chest pain is not something to ignore. But you should know that it has many possible causes. In fact, as much as a quarter of the U.S. population experiences chest pain that is not related to the heart. Chest pain may also be caused by problems in your lungs, esophagus, muscles, ribs, or nerves, for example. Some of these conditions are serious and life threatening. Others are not. If you have unexplained chest pain, the only way to confirm its cause is to have a doctor evaluate you.");
        List<String> tests = new ArrayList<String>();
        tests.add("ECG");
        tests.add("EEG");
        List<String> videos = new ArrayList<String>();
        videos.add("Video_link1");
        videos.add("Video_link2");
        listDataChild.put(listDataHeader.get(0), description); // Header, Child data
        listDataChild.put(listDataHeader.get(1), tests);
        listDataChild.put(listDataHeader.get(2), videos);
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


/*

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.doctor.R;

import java.util.ArrayList;

*/
/**
 * Created by BANGAR on 09-06-2017.
 *//*


public class DiseaseDetail extends AppCompatActivity implements View.OnClickListener, Adapter.MyClickListener {

    private RecyclerView recyclerView;
    private Adapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.Disease_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        toolbar.setTitle("My title");
        //toolbar.setTitle("My title");
        setSupportActionBar(toolbar);

        initRecyclerview();
        prepareData();

        TextView description,tests,share;
        description=(TextView)findViewById(R.id.description_view);
        tests=(TextView)findViewById(R.id.test_view);
        share=(TextView)findViewById(R.id.share);
        //videolink1=(TextView)findViewById(R.id.videolinks_1);
        //videolink2=(TextView)findViewById(R.id.videolinks_2);
        share.setOnClickListener(this);
        */
/*videolink1.setOnClickListener(this);
        videolink2.setOnClickListener(this);*//*

    }

    private void prepareData() {
        ArrayList<DemoModel> demoModels = new ArrayList<>();

        demoModels.add(new DemoModel("Link_1 name"));
        demoModels.add(new DemoModel("Link_2 name"));
        demoModels.add(new DemoModel( "Link_3 name"));
        demoModels.add(new DemoModel("Link_4 name"));
        demoModels.add(new DemoModel( "Link_4 name"));
        demoModels.add(new DemoModel("Link_5 name"));
        demoModels.add(new DemoModel( "Link_6 name"));
        demoModels.add(new DemoModel("Link_7 name"));

        adapter.addAll(demoModels);

    }

    private void initRecyclerview() {
        recyclerView = (RecyclerView) findViewById(R.id.recycleview_links);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

       // GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        */
/*StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        *//*

        //recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new Adapter(this, new ArrayList<DemoModel>());
        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.share){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{ "emailaddress@example.com"});
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "This is Subject");
            sendIntent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
           }
        */
/*else if(v.getId()==R.id.videolinks_1){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=j4dRiaNf39M")));
        }
        else if(v.getId()==R.id.videolinks_2){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=9E0xxCROnqY")));
        }*//*

    }

    @Override
    public void onItemClick(int position, View v) {
        if(position==0){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=j4dRiaNf39M")));
        }
        else if(position==1){
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=9E0xxCROnqY")));
        }
    }
}
*/
