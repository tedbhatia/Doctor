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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.support.service.ApiClient;
import com.example.doctor.support.service.RequestInterface;
import com.example.doctor.ui.adapter.Insurance_Adapter;
import com.example.doctor.ui.adapter.MyDoctorAdapter;
import com.example.doctor.ui.fragment.Symptoms_Male_Fragment;
import com.example.doctor.ui.model.Body_Parts;
import com.example.doctor.ui.model.Doctor;
import com.example.doctor.ui.model.Insurance;
import com.example.doctor.ui.model.Measurement_Info;
import com.nikhilpanju.recyclerviewenhanced.OnActivityTouchListener;
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;
import com.baoyz.widget.PullRefreshLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyDoctor extends AppCompatActivity implements MyDoctorAdapter.MyClickListener, RecyclerTouchListener.RecyclerTouchListenerHelper {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MyDoctorAdapter adapter;
    private List<Doctor> data, fData;
    private int flag = 0;
    private ProgressDialog progressDialog;

    private OnActivityTouchListener touchListener;
    private RecyclerTouchListener onTouchListener;

    PullRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_doctor);
        setTitle("My Doctor");

        /*progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.isIndeterminate();
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (flag == 0) {
                    Toast.makeText(getApplicationContext(), "Poor Connection, Try Again", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }, 20000);

        loadJSON();*/

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        data = new ArrayList<>();
        fData = new ArrayList<>();

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

  /*  private void loadJSON() {
        final RequestInterface request = ApiClient.getClient().create(RequestInterface.class);
        Call<List<Doctor>> call = request.getMYDoctor();
        call.enqueue(new Callback<List<Doctor>>() {
            @Override
            public void onResponse(Call<List<Doctor>> call, Response<List<Doctor>> response) {
                try {
//                    Toast.makeText(getApplicationContext(),response.body().string(),Toast.LENGTH_SHORT).show();
                    data = response.body();
                    flag = 1;
                    progressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
               *//* bodyPartsAdapter = new Body_Parts_Adapter(getContext(),bodyPartSupers);
                mRecyclerView.setAdapter(bodyPartsAdapter);*//*
            }

            @Override
            public void onFailure(Call<List<Doctor>> call, Throwable t) {

            }
        });
    }*/

    private void prepareData() {
        String[] name = {"Dr Olga Malkin(DDS - Dentistry and Prosthodontics)", "Dr Alison M Maresh (MD - Ear, Nose, and Throat)","Dr Martin Quirno(MD - Orthopedic Suregery)"};
        String[] type = {"Dentistry","ENT Specialist","Orthopedic"};
        String[] note = {"Dr. Olga Malkin is a dentistry and prosthodontics doctor with offices in New York, New York.", "Dr. Alison M Maresh is an ear, nose, and throat (ent) doctor with offices in New York, New York.", "Dr. Martin Quirno is an orthopedic surgery doctor with offices in New York, New York."};
        String[] address = {"23 Warren Street, Suite 10, New York, NY 10007","156 William Street, New York, NY 10038","281, Broadway, 2nd Floor, New York, NY 10007"};
        String[] phone = {"212-355-4510","646-962-2225","646-596-7386"};

        for (int i = 0; i < name.length; i++) {
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
        ((ImageView)dialog.findViewById(R.id.display_picture)).setImageResource(R.drawable.doctor4);
        ((TextView)dialog.findViewById(R.id.name_edit)).setText(a.getName());
        ((TextView)dialog.findViewById(R.id.type_edit)).setText(a.getType());
        ((TextView)dialog.findViewById(R.id.address_edit)).setText(a.getAddress());
        ((TextView)dialog.findViewById(R.id.phone_edit)).setText(a.getPhone());
        ((TextView)dialog.findViewById(R.id.note_edit)).setText(a.getNotes());
        dialog.show();
    }

}