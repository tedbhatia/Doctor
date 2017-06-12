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

import com.example.doctor.R;
import com.example.doctor.ui.adapter.Insurance_Adapter;
import com.example.doctor.ui.adapter.MyDoctorAdapter;
import com.example.doctor.ui.model.Doctor;
import com.example.doctor.ui.model.Insurance;
import com.nikhilpanju.recyclerviewenhanced.OnActivityTouchListener;
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

public class MyDoctor extends AppCompatActivity implements MyDoctorAdapter.MyClickListener, RecyclerTouchListener.RecyclerTouchListenerHelper{

    private RecyclerView recyclerView;
    private MyDoctorAdapter adapter;
    private List<Doctor> data;

    private OnActivityTouchListener touchListener;
    private RecyclerTouchListener onTouchListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_doctor);
        setTitle("My Doctor");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        data = new ArrayList<>();

        prepareData();
        initRecyclerView();

        onTouchListener=new RecyclerTouchListener(this,recyclerView);

        onTouchListener
                .setIndependentViews(R.id.editButton)
                .setViewsToFade(R.id.editButton)
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                        Toast.makeText(MyDoctor.this,"View",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {
                        Toast.makeText(MyDoctor.this,"Button",Toast.LENGTH_SHORT).show();
                    }
                })
                .setLongClickable(true, new RecyclerTouchListener.OnRowLongClickListener() {
                    @Override
                    public void onRowLongClicked(int position) {
                        Toast.makeText(MyDoctor.this,"View long clicked!",Toast.LENGTH_SHORT).show();
                    }
                })
                .setSwipeOptionViews(R.id.delete)
                .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                    }
                });

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

    private void prepareData() {
        String[] name = {"Plan 1", "Plan 2", "Plan 3", "Plan 4", "Plan 5", "Plan 6", "Plan 7"};
        String[] type = {"1 Month", "2 Month", "3 Month", "4 Month", "5 Month", "6 Month", "7 Month"};
        String[] note = {"My Doctors", "My Diseases", "My Appointments", "My Medicines", "My Documents", "My Insurance", "My Measurements"};
        String[] address = {"01/01/2000","01/01/2001","01/01/2002","01/01/2003","01/01/2004","01/01/2006","01/01/2007"};
        String[] phone = {"01/01/2000","01/01/2001","01/01/2002","01/01/2003","01/01/2004","01/01/2006","01/01/2007"};

        for(int i=0;i<name.length && i<type.length && i<phone.length; i++){
            Doctor current = new Doctor();
            current.setName(name[i]);
            current.setType(type[i]);
            current.setAddress(address[i]);
            current.setPhone(phone[i]);
            current.setNotes(note[i]);
            data.add(current);
        }
        adapter = new MyDoctorAdapter(MyDoctor.this,data);
    }
    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyDoctor.this,1,false));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public void onItemClick(int position, View v) {
        Intent intent = new Intent(MyDoctor.this,EditDoctor.class);
        intent.putExtra("doctor",data.get(position));
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        else {
            Intent intent = new Intent(MyDoctor.this,EditDoctor.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
