package com.example.doctor.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.example.doctor.R;
import com.example.doctor.ui.adapter.Measurement_Adapter;
import com.example.doctor.ui.model.Measurement_Info;
import com.nikhilpanju.recyclerviewenhanced.OnActivityTouchListener;
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class My_Measurements extends AppCompatActivity implements Measurement_Adapter.MyClickListener,RecyclerTouchListener.RecyclerTouchListenerHelper {

    private RecyclerView recyclerView;
    private Measurement_Adapter adapter;

    private List<Measurement_Info> listItems;
    private Measurement_Info listItem;

    private OnActivityTouchListener touchListener;
    private RecyclerTouchListener onTouchListener;

    PullRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_measurements);
        setTitle("My Measurement");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listItems = new ArrayList<>();

        prepareData();
        initRecyclerView();

        onTouchListener=new RecyclerTouchListener(this,recyclerView);
        onTouchListener
                .setIndependentViews(R.id.editButton)
                .setViewsToFade(R.id.editButton)
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                        Toast.makeText(My_Measurements.this,"View",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {
                        Toast.makeText(My_Measurements.this,"Button",Toast.LENGTH_SHORT).show();
                    }
                })
                .setLongClickable(true, new RecyclerTouchListener.OnRowLongClickListener() {
                    @Override
                    public void onRowLongClicked(int position) {
                        Toast.makeText(My_Measurements.this,"View long clicked!",Toast.LENGTH_SHORT).show();
                    }
                })
                .setSwipeOptionViews(R.id.delete)
                .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                    }
                });

        refreshLayout = (PullRefreshLayout) findViewById(R.id.swipeLayoutMeasurement);
        refreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_MATERIAL);
        refreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.postDelayed(new TimerTask() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });


    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(My_Measurements.this,1,false));
    }

    private void prepareData() {

        String[] date = {"23/05/2017", "23/05/2017", "25/05/2017", "30/05/2017", "30/05/2017"};
        String[] height = {"5'6\"","5'6\"","5'6\"","5'6\"","5'6\""};
        String[] weight={"67 kg","68 kg","68 kg","65 kg","65 kg"};
        String[] bloodPressure={"xxx","yyy","zzz","aaa","bbb"};
        String[] bloodSugar={"qwerty 1","qwerty 2","qwerty 3","qwerty 4","qwerty 5"};
        String[] cholesterol={"111","222","333","444","555"};

        for (int i = 0; i < date.length; i++) {
            Measurement_Info current = new Measurement_Info();
            current.setDate(date[i]);
            current.setHeight(height[i]);
            current.setWeight(weight[i]);
            current.setBloodPressure(bloodPressure[i]);
            current.setBloodSugar(bloodSugar[i]);
            current.setCholesterol(cholesterol[i]);

            listItems.add(current);
        }
        adapter = new Measurement_Adapter(My_Measurements.this,listItems);

    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.addOnItemTouchListener(onTouchListener); }

    @Override
    protected void onPause() {
        super.onPause();
        recyclerView.removeOnItemTouchListener(onTouchListener);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (touchListener != null) touchListener.getTouchCoordinates(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void setOnActivityTouchListener(OnActivityTouchListener listener) {
        this.touchListener = listener;
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
        intent.putExtra("measurement",listItems.get(position));
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
