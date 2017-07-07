package com.example.doctor.ui.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.support.service.ApiClient;
import com.example.doctor.support.service.RequestInterface;
import com.example.doctor.ui.activity.DiseaseDetail;
import com.example.doctor.ui.activity.MyAppointments;
import com.example.doctor.ui.activity.My_Insurance;
import com.example.doctor.ui.activity.SymptomDetail;
import com.example.doctor.ui.adapter.AppointmentsAdapter;
import com.example.doctor.ui.adapter.Insurance_Adapter;
import com.example.doctor.ui.model.AppointmentSuper;
import com.example.doctor.ui.model.BodyPartSuper;
import com.example.doctor.ui.model.Body_Parts;
import com.example.doctor.ui.adapter.Body_Parts_Adapter;
import com.example.doctor.ui.model.DiseasesList;
import com.example.doctor.ui.model.Insurance;
import com.example.doctor.ui.model.SymptomModel;
import com.example.doctor.ui.model.find_doctor_model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.doctor.ui.activity.MainActivity.navigationView;
import static com.example.doctor.ui.fragment.Symptoms_Fragment.body_parts;

/**
 * Created by Aviral on 09-06-2017.
 */

public class Symptoms_Male_Fragment extends android.support.v4.app.Fragment implements Body_Parts_Adapter.MyChildClickListener {
    private RecyclerView mRecyclerView;
    public Body_Parts_Adapter bodyPartsAdapter;
    //private List<Body_Parts> body_parts;
    private List<SymptomModel> symptoms;
    private List<BodyPartSuper> bodyPartSupers;
    private ProgressDialog progressDialog;
    public FragmentManager fm;
    private int flag = 0;
//    public transient Context mContext=getActivity();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.symptoms_child, container, false);

        //get_Body_Parts();

        //body_parts = new ArrayList<>();
        symptoms = new ArrayList<>();
        bodyPartSupers = new ArrayList<>();
        final Health_Acc_Fragment health_acc_fragment = new Health_Acc_Fragment();
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.symptoms_recycler);
        mRecyclerView.setHasFixedSize(true);
        bodyPartsAdapter = new Body_Parts_Adapter(getContext(), bodyPartSupers);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(bodyPartsAdapter);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.isIndeterminate();
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (flag == 0) {
                    Toast.makeText(getContext(), "Poor Connection, Try Again", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }, 20000);

        /*progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_BACK && !event.isCanceled()) {
                    if(progressDialog.isShowing()) {
                        //your logic here for back button pressed event
                    }
                    return true;
                }
                return false;
            }
        });*/

//        rootView.setFocusableInTouchMode(true);
//        rootView.requestFocus();
//        rootView.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//                    progressDialog.dismiss();
//
//                    Health_Acc_Fragment health_acc_fragment = new Health_Acc_Fragment();
//
//                    navigationView.getMenu().getItem(0).setChecked(true);
//
//                    FragmentTransaction transaction=getFragmentManager().beginTransaction();
//                    transaction.replace(R.id.content_frame,health_acc_fragment);
//
//                    transaction.commit();
//
//
//                    return true;
//                }
//                return false;
//            }
//        });

        /*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progressDialog.dismiss();
            }
        }, 3000);*/

        //loadJSON();
        loadJSON1();
        return rootView;


    }

    /*public void get_Body_Parts() {
        List<String> description = new ArrayList<String>();
        description.add("The first thing you may think of is heart attack. Certainly chest pain is not something to ignore.");
        List<String> tests = new ArrayList<String>();
        tests.add("ECG");
        tests.add("EEG");
        List<String> videos = new ArrayList<String>();
        videos.add("Video_link1");
        videos.add("Video_link2");
        List<String> diseasesList = new ArrayList<String>();
        diseasesList.add("Cancer");
        diseasesList.add("TB");
        SymptomModel handpain = new SymptomModel("handpain",description,tests,videos, diseasesList);
        SymptomModel handpain1 = new SymptomModel("Swelling",description,tests,videos, diseasesList);
        SymptomModel handpain2 = new SymptomModel("Bone fracture",description,tests,videos, diseasesList);
        SymptomModel handpain3 = new SymptomModel("handpain",description,tests,videos, diseasesList);
        SymptomModel handpain4 = new SymptomModel("Paralysis",description,tests,videos, diseasesList);
        SymptomModel handpain5 = new SymptomModel("handpain",description,tests,videos, diseasesList);
        SymptomModel handpain6 = new SymptomModel("Contracture",description,tests,videos, diseasesList);
        SymptomModel handpain7 = new SymptomModel("Nerve injury",description,tests,videos, diseasesList);

        Body_Parts hand = new Body_Parts("hand", Arrays.asList(handpain, handpain1, handpain2, handpain3));
        Body_Parts hand1 = new Body_Parts("leg", Arrays.asList(handpain, handpain1, handpain4, handpain5));
        Body_Parts hand2 = new Body_Parts("Chest", Arrays.asList(handpain, handpain1, handpain6, handpain7));
        Body_Parts hand3 = new Body_Parts("joint", Arrays.asList(handpain, handpain1, handpain6, handpain7));
        body_parts = Arrays.asList(hand, hand1, hand2, hand3);
    }
*/
    @Override
    public void onChildClickListener(int parent_positon, int child_position, View v) {

        Intent intent = new Intent(getContext(), SymptomDetail.class);
        intent.putExtra("symptomdetail", bodyPartSupers.get(parent_positon).getChildList().get(child_position));
        startActivity(intent);
    }

    /*private void loadJSON() {
        final RequestInterface request = ApiClient.getClient().create(RequestInterface.class);
        Call<List<Body_Parts>> call = request.getBODY();
        call.enqueue(new Callback<List<Body_Parts>>() {
            @Override
            public void onResponse(Call<List<Body_Parts>> call, Response<List<Body_Parts>> response) {
                try {
//                    Toast.makeText(getApplicationContext(),response.body().string(),Toast.LENGTH_SHORT).show();
                    body_parts = response.body();
                } catch (Exception e) {
                    e.printStackTrace();
                }
               *//* bodyPartsAdapter = new Body_Parts_Adapter(getContext(),bodyPartSupers);
                mRecyclerView.setAdapter(bodyPartsAdapter);*//*
            }

            @Override
            public void onFailure(Call<List<Body_Parts>> call, Throwable t) {

            }
        });
    }*/

    private void loadJSON1() {
        final RequestInterface request = ApiClient.getClient().create(RequestInterface.class);
        Call<List<SymptomModel>> call = request.getSymptoms();
        call.enqueue(new Callback<List<SymptomModel>>() {
            @Override
            public void onResponse(Call<List<SymptomModel>> call, Response<List<SymptomModel>> response) {
                try {
//                    Toast.makeText(getApplicationContext(),response.body().string(),Toast.LENGTH_SHORT).show();
                    symptoms = response.body();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                doSomething();
                /*adapter = new AppointmentsAdapter(MyAppointments.this, model);
                recyclerView.setAdapter(adapter);*/
            }

            @Override
            public void onFailure(Call<List<SymptomModel>> call, Throwable t) {

            }
        });
    }

    private void doSomething() {
        if (body_parts.size() != 0 && symptoms.size() != 0) {
            flag = 1;
            progressDialog.dismiss();
        }
        for (int i = 0; i < body_parts.size() && symptoms.size() != 0; i++) {
            /*hybrid.add(i,new AppointmentSuper(doc.get(model.get(i).getDoctor()-1).getDoctor_name(),
                    doc.get(model.get(i).getDoctor()-1).getDoctor_phone_number(),doc.get(model.get(i).getDoctor()-1).getDoctor_address(),
                    doc.get(model.get(i).getDoctor()-1).getDoctor_speciality(),model.get(i).getDate(),model.get(i).getTime(),
                    model.get(i).getReason(),model.get(i).getNotes()));*/
            List<SymptomModel> SymList = new ArrayList<>();
            List<Integer> list = body_parts.get(i).getBPsymptom();
            for (int j = 0; j < list.size(); j++) {

                SymList.add(j, symptoms.get(list.get(j) - 1));
                //SymList.add(j,symptoms.get(findbyID(list.get(j))));
            }
            bodyPartSupers.add(i, new BodyPartSuper(body_parts.get(i).getBodypart(), SymList));
        }
        bodyPartsAdapter = new Body_Parts_Adapter(getContext(), bodyPartSupers);
        bodyPartsAdapter.setOnChildClickListener(this);
        mRecyclerView.setAdapter(bodyPartsAdapter);
    }

    private int findbyID(Integer integer) {
        for (int k = 0; k < symptoms.size(); k++) {
            if (symptoms.get(k).getId() == integer) {
                return k;
            }
        }
        return -1;
    }
}
