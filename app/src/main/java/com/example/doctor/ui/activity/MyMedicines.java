package com.example.doctor.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.example.doctor.R;
import com.example.doctor.ui.adapter.MedicinesAdapter;
import com.example.doctor.ui.adapter.My_Health_Acc_Adapter;
import com.example.doctor.ui.model.Appointments;
import com.example.doctor.ui.model.Medicines;
import com.nikhilpanju.recyclerviewenhanced.OnActivityTouchListener;
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class MyMedicines extends AppCompatActivity implements My_Health_Acc_Adapter.MyClickListener,RecyclerTouchListener.RecyclerTouchListenerHelper {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MedicinesAdapter adapter;

    private List<Medicines> listItems;
    private Medicines listItem;

    private ProgressDialog progressDialog;

    private OnActivityTouchListener touchListener;
    private RecyclerTouchListener onTouchListener;

    PullRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_medicines);

        setTitle("My Medicines");

//        progressDialog=new ProgressDialog(this);
//        progressDialog.setMessage("Loading...");
//        progressDialog.setCancelable(false);
//        progressDialog.isIndeterminate();
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDialog.show();
//
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                progressDialog.dismiss();
//            }
//        }, 3000);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        listItems=new ArrayList<>();

        listItems.add(0,new Medicines("Erythromycin","1 tablet","Tablet",3,"2017/06/22","Dr Olga Malkin",""));
        listItems.add(1,new Medicines("Nasonex","1 spray","Inhaler",1,"2017/06/22","","Dr Alison M Maresh"));
        listItems.add(2,new Medicines("Minocin","1 tablet","Tablet",2,"2017/06/22","Dr Martin Quirno",""));

        adapter=new MedicinesAdapter(this,listItems);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(this);

        onTouchListener=new RecyclerTouchListener(this,recyclerView);

        onTouchListener
                .setIndependentViews(R.id.editButton)
                .setViewsToFade(R.id.editButton)
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                        //Toast.makeText(MyMedicines.this,"View",Toast.LENGTH_SHORT).show();
                        details(position);
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {
                        Toast.makeText(MyMedicines.this,"Button",Toast.LENGTH_SHORT).show();
                    }
                })
                .setLongClickable(true, new RecyclerTouchListener.OnRowLongClickListener() {
                    @Override
                    public void onRowLongClicked(int position) {
                        Toast.makeText(MyMedicines.this,"View long clicked!",Toast.LENGTH_SHORT).show();
                    }
                })
                .setSwipeOptionViews(R.id.delete)
                .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                    }
                });

        refreshLayout = (PullRefreshLayout) findViewById(R.id.pullLayout);
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

        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int mLastFirstVisibleItem = 0;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                final int currentFirstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();

                if (currentFirstVisibleItem > this.mLastFirstVisibleItem) {
                    MyMedicines.this.getSupportActionBar().hide();
                } else if (currentFirstVisibleItem < this.mLastFirstVisibleItem) {
                    MyMedicines.this.getSupportActionBar().show();
                }

                this.mLastFirstVisibleItem = currentFirstVisibleItem;
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
    public void onItemClick(int position, View v) {

        listItem=listItems.get(position);

        Intent intent=new Intent(MyMedicines.this,EditMedicines.class);
        intent.putExtra("medicine",listItem);
        startActivity(intent);

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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){

            this.finish();

        }
        else {

            Intent intent=new Intent(MyMedicines.this,EditMedicines.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    private void details(int position) {
        Medicines a = listItems.get(position);

        //
        Dialog dialog = new Dialog(this,R.style.Theme_AppCompat_DialogWhenLarge);
        dialog.setContentView(R.layout.display_medicines);
        //dialog.findViewById(R.id.imageZoom);
        //((ImageView)dialog.findViewById(R.id.imageZoom)).setImageURI(Uri.parse(person1.getUri()));
        ((TextView)dialog.findViewById(R.id.med_name_edit)).setText(a.getMedName());
        ((TextView)dialog.findViewById(R.id.dosage_amount_edit)).setText(a.getDosageAmount());
        ((TextView)dialog.findViewById(R.id.method_edit)).setText(a.getMethod());
        ((TextView)dialog.findViewById(R.id.frequency_edit)).setText(String.valueOf(a.getFrequency()));
        ((TextView)dialog.findViewById(R.id.date_edit)).setText(a.getDate());
        ((TextView)dialog.findViewById(R.id.doctor_name_edit)).setText(a.getDoctorName());
        ((TextView)dialog.findViewById(R.id.notes_edit)).setText(a.getNotes());
        dialog.show();
    }

}
