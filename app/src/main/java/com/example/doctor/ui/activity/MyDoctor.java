package com.example.doctor.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.adapter.Insurance_Adapter;
import com.example.doctor.ui.adapter.MyDoctorAdapter;
import com.example.doctor.ui.model.Doctor;
import com.example.doctor.ui.model.Insurance;
import com.example.doctor.ui.model.Measurement_Info;
import com.nikhilpanju.recyclerviewenhanced.OnActivityTouchListener;
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;
import com.baoyz.widget.PullRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;


public class MyDoctor extends AppCompatActivity implements MyDoctorAdapter.MyClickListener, RecyclerTouchListener.RecyclerTouchListenerHelper {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MyDoctorAdapter adapter;
    private List<Doctor> data;

    private ProgressDialog progressDialog;

    private OnActivityTouchListener touchListener;
    private RecyclerTouchListener onTouchListener;

    PullRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_doctor);
        setTitle("My Doctor");

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

        data = new ArrayList<>();

        prepareData();
        initRecyclerView();

        onTouchListener = new RecyclerTouchListener(this, recyclerView);

        onTouchListener
                .setIndependentViews(R.id.editButton)
                .setViewsToFade(R.id.editButton)
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                        //Toast.makeText(MyDoctor.this, "View", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {
                        Toast.makeText(MyDoctor.this, "Button", Toast.LENGTH_SHORT).show();
                    }
                })
                .setLongClickable(true, new RecyclerTouchListener.OnRowLongClickListener() {
                    @Override
                    public void onRowLongClicked(int position) {
                        Toast.makeText(MyDoctor.this, "View long clicked!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setSwipeOptionViews(R.id.delete)
                .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                    }
                });

        refreshLayout = (PullRefreshLayout) findViewById(R.id.swipeLayoutDoctor);
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
                    MyDoctor.this.getSupportActionBar().hide();
                } else if (currentFirstVisibleItem < this.mLastFirstVisibleItem) {
                    MyDoctor.this.getSupportActionBar().show();
                }

                this.mLastFirstVisibleItem = currentFirstVisibleItem;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.addOnItemTouchListener(onTouchListener);
    }

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
        String[] name = {"Dr Smith", "Dr Smith", "Dr Smith", "Dr Smith", "Dr Smith", "Dr Smith", "Dr Smith"};
        String[] type = {"Heart Surgeon", "Heart Surgeon","Heart Surgeon","Heart Surgeon","Heart Surgeon", "Heart Surgeon", "Heart Surgeon"};
        String[] note = {"My Doctors", "My Diseases", "My Appointments", "My Medicines", "My Documents", "My Insurance", "My Measurements"};
        String[] address = {"Ahmedabad","Ahmedabad","Ahmedabad","Ahmedabad","Ahmedabad","Ahmedabad","Ahmedabad"};
        String[] phone = {"9876543210","9876543210","9876543210","9876543210","9876543210","9876543210","9876543210"};

        for (int i = 0; i < name.length && i < type.length && i < phone.length; i++) {
            Doctor current = new Doctor();
            current.setName(name[i]);
            current.setType(type[i]);
            current.setAddress(address[i]);
            current.setPhone(phone[i]);
            current.setNotes(note[i]);
            data.add(current);
        }
        adapter = new MyDoctorAdapter(MyDoctor.this, data);
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public void onItemClick(int position, View v) {
        if(v.getId()==R.id.requestButton){
            Toast.makeText(this,"Request",Toast.LENGTH_SHORT).show();
        }
        else if(v.getId() == R.id.viewDetails){
            details(position);
        }
        else {
            Intent intent = new Intent(MyDoctor.this, EditDoctor.class);
            intent.putExtra("doctor", data.get(position));
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
        } else {
            Intent intent = new Intent(MyDoctor.this, EditDoctor.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void details(int position) {
        Doctor a = data.get(position);

        //
        Dialog dialog = new Dialog(this,R.style.Theme_AppCompat_DialogWhenLarge);
        dialog.setContentView(R.layout.display_doctor);
        //dialog.findViewById(R.id.imageZoom);
        //((ImageView)dialog.findViewById(R.id.imageZoom)).setImageURI(Uri.parse(person1.getUri()));
        ((TextView)dialog.findViewById(R.id.name_edit)).setText(a.getName());
        ((TextView)dialog.findViewById(R.id.type_edit)).setText(a.getType());
        ((TextView)dialog.findViewById(R.id.address_edit)).setText(a.getAddress());
        ((TextView)dialog.findViewById(R.id.phone_edit)).setText(a.getPhone());
        ((TextView)dialog.findViewById(R.id.note_edit)).setText(a.getNotes());
        dialog.show();
    }

}