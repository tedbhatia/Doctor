package com.example.doctor.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.baoyz.widget.PullRefreshLayout;
import com.example.doctor.R;
import com.example.doctor.support.service.ApiClient;
import com.example.doctor.support.service.RequestInterface;
import com.example.doctor.ui.adapter.AppointmentsAdapter;
import com.example.doctor.ui.adapter.FindDoctorAdapter;
import com.example.doctor.ui.adapter.My_Health_Acc_Adapter;
import com.example.doctor.ui.model.AppointmentSuper;
import com.example.doctor.ui.model.Appointments;
import com.example.doctor.ui.model.Doctor;
import com.example.doctor.ui.model.find_doctor_model;
import com.nikhilpanju.recyclerviewenhanced.OnActivityTouchListener;
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAppointments extends AppCompatActivity implements My_Health_Acc_Adapter.MyClickListener, RecyclerTouchListener.RecyclerTouchListenerHelper {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private AppointmentsAdapter adapter;

    private List<Appointments> model;
    private AppointmentSuper listItem;
    private List<find_doctor_model> doc;
    private List<AppointmentSuper> hybrid;

    private ProgressDialog progressDialog;

    private OnActivityTouchListener touchListener;
    private RecyclerTouchListener onTouchListener;

    public static final int idDoc = 0;

    PullRefreshLayout refreshLayout;
    private int flag1;
    private int flag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointments);

        setTitle("My Appointments");

        model = new ArrayList<>();
        doc = new ArrayList<>();
        hybrid = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyAppointments.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new AppointmentsAdapter(MyAppointments.this, hybrid);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.isIndeterminate();
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        /*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progressDialog.dismiss();
            }
        }, 3000);*/

        loadJSON();
        loadJSON1();
   //     doSomething();

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


//        listItems=new ArrayList<>();
//
//        listItems.add(0,new Appointments("Dr Olga Malkin","212-355-4510","23 Warren Street, Suite 10, New York, NY 10007","Dentistry and Prosthodontics","Regular Checkup","","01/07/2017","1100 hours"));
//        listItems.add(1,new Appointments("Dr Alison M Maresh","646-962-2225","156 William Street, New York, NY 10038","Ear, Nose, and Throat","Regular Checkup","","03/07/17","1230 hours"));
//        listItems.add(2,new Appointments("Dr Dr Martin Quirno","646-596-7386","281, Broadway, 2nd Floor, New York, NY 10007","Orthopedic Suregery","Regular Checkup","","04/07/17","1230 hours"));


        adapter.setOnItemClickListener(this);
        onTouchListener = new RecyclerTouchListener(this, recyclerView);
        onTouchListener
                .setIndependentViews(R.id.editButton)
                .setViewsToFade(R.id.editButton)
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                        //Toast.makeText(MyAppointments.this,"View",Toast.LENGTH_SHORT).show();
                        details(position);
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {
                        Toast.makeText(MyAppointments.this, "Button", Toast.LENGTH_SHORT).show();
                    }
                })
                .setLongClickable(true, new RecyclerTouchListener.OnRowLongClickListener() {
                    @Override
                    public void onRowLongClicked(int position) {
                        Toast.makeText(MyAppointments.this, "View long clicked!", Toast.LENGTH_SHORT).show();
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
                    MyAppointments.this.getSupportActionBar().hide();
                } else if (currentFirstVisibleItem < this.mLastFirstVisibleItem) {
                    MyAppointments.this.getSupportActionBar().show();
                }

                this.mLastFirstVisibleItem = currentFirstVisibleItem;
            }
        });

    }

    private void doSomething() {
        if(flag1!=0 && flag2!=0){
            progressDialog.dismiss();
        }
        for(int i=0;i<model.size()&& doc.size()!=0;i++){
            hybrid.add(i,new AppointmentSuper(doc.get(model.get(i).getDoctor()-1).getDoctor_name(),
                    doc.get(model.get(i).getDoctor()-1).getDoctor_phone_number(),doc.get(model.get(i).getDoctor()-1).getDoctor_address(),
                    doc.get(model.get(i).getDoctor()-1).getDoctor_speciality(),model.get(i).getDate(),model.get(i).getTime(),
                    model.get(i).getReason(),model.get(i).getNotes()));
        }
        adapter = new AppointmentsAdapter(MyAppointments.this, hybrid);
        recyclerView.setAdapter(adapter);
    }

    private void loadJSON() {
        final RequestInterface request = ApiClient.getClient().create(RequestInterface.class);
        Call<List<Appointments>> call = request.getAppointment();
        call.enqueue(new Callback<List<Appointments>>() {
            @Override
            public void onResponse(Call<List<Appointments>> call, Response<List<Appointments>> response) {
                try {
//                    Toast.makeText(getApplicationContext(),response.body().string(),Toast.LENGTH_SHORT).show();
                    model = response.body();
                    flag1 = 1;
                } catch (Exception e) {
                    e.printStackTrace();
                }
     //           doSomething();
                /*adapter = new AppointmentsAdapter(MyAppointments.this, model);
                recyclerView.setAdapter(adapter);*/
            }

            @Override
            public void onFailure(Call<List<Appointments>> call, Throwable t) {

            }
        });
    }

    private void loadJSON1() {
        final RequestInterface request = ApiClient.getClient().create(RequestInterface.class);
        Call<List<find_doctor_model>> call = request.getJSON();
        call.enqueue(new Callback<List<find_doctor_model>>() {
            @Override
            public void onResponse(Call<List<find_doctor_model>> call, Response<List<find_doctor_model>> response) {
                try {
//                    Toast.makeText(getApplicationContext(),response.body().string(),Toast.LENGTH_SHORT).show();
                    doc = response.body();
                    flag2 = 1;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                doSomething();
                /*adapter = new AppointmentsAdapter(MyAppointments.this, model);
                recyclerView.setAdapter(adapter);*/
            }

            @Override
            public void onFailure(Call<List<find_doctor_model>> call, Throwable t) {

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
    public void onItemClick(int position, View v) {

        listItem = hybrid.get(position);

        Intent intent = new Intent(MyAppointments.this, EditAppointments.class);
        intent.putExtra("appointment", listItem);
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

        if (item.getItemId() == android.R.id.home) {

            this.finish();

        } else {

            Intent intent = new Intent(MyAppointments.this, EditAppointments.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);

    }

    private void details(int position) {
        AppointmentSuper a = hybrid.get(position);

        //
        Dialog dialog = new Dialog(this, R.style.Theme_AppCompat_DialogWhenLarge);
        dialog.setContentView(R.layout.display_appointments);
        //dialog.findViewById(R.id.imageZoom);
        //((ImageView)dialog.findViewById(R.id.imageZoom)).setImageURI(Uri.parse(person1.getUri()));
        ((TextView)dialog.findViewById(R.id.name_edit)).setText(a.getDocsName());
        ((TextView)dialog.findViewById(R.id.phone_edit)).setText(a.getPhone());
        ((TextView)dialog.findViewById(R.id.address_edit)).setText(a.getAddress());
        ((TextView)dialog.findViewById(R.id.speciality_edit)).setText(a.getSpec());
        ((TextView) dialog.findViewById(R.id.date_edit)).setText(a.getDate());
        ((TextView) dialog.findViewById(R.id.time_edit)).setText(a.getTime());
        ((TextView) dialog.findViewById(R.id.reason_edit)).setText(a.getReason());
        ((TextView) dialog.findViewById(R.id.notes_edit)).setText(a.getNotes());
        dialog.show();
    }

}