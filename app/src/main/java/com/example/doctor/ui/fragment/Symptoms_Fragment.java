package com.example.doctor.ui.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.support.service.ApiClient;
import com.example.doctor.support.service.RequestInterface;
import com.example.doctor.ui.model.Body_Parts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.doctor.ui.activity.MainActivity.navigationView;

/**
 * Created by Aviral on 09-06-2017.
 */

public class Symptoms_Fragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    android.support.v4.app.FragmentManager fm;
    public static int gender = 0;
    private ProgressDialog progressDialog;
    public static List<Body_Parts> body_parts;
    private int flag = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_symptoms,container,false);

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

        loadJSON();

//        Spinner spinner = (Spinner)rootView.findViewById(R.id.spin);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(position==0){
//                    fm.beginTransaction().replace(R.id.content_frame2,new Symptoms_Male_Fragment()).commit();
//                }
//                else if(position==1){
//                    fm.beginTransaction().replace(R.id.content_frame2,new Symptoms_Female_Fragment()).commit();
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        RadioButton rb_male, rb_female;
        rb_male = (RadioButton) rootView.findViewById(R.id.rb_male);
        rb_female = (RadioButton) rootView.findViewById(R.id.rb_female);
        rb_male.setOnClickListener(this);
        rb_female.setOnClickListener(this);

        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {

                    Health_Acc_Fragment health_acc_fragment = new Health_Acc_Fragment();

                    navigationView.getMenu().getItem(0).setChecked(true);

                    FragmentTransaction transaction=getFragmentManager().beginTransaction();
                    transaction.replace(R.id.content_frame,health_acc_fragment);

                    transaction.commit();


                    return true;
                }
                return false;
            }
        });


        return rootView;
    }

    private void loadJSON() {
        final RequestInterface request = ApiClient.getClient().create(RequestInterface.class);
        Call<List<Body_Parts>> call = request.getBODY();
        call.enqueue(new Callback<List<Body_Parts>>() {
            @Override
            public void onResponse(Call<List<Body_Parts>> call, Response<List<Body_Parts>> response) {
                try {
//                    Toast.makeText(getApplicationContext(),response.body().string(),Toast.LENGTH_SHORT).show();
                    body_parts = response.body();
                    progressDialog.dismiss();
                    fm = getChildFragmentManager();
                    fm.beginTransaction().replace(R.id.content_frame2,new Symptoms_Male_Fragment()).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
               /* bodyPartsAdapter = new Body_Parts_Adapter(getContext(),bodyPartSupers);
                mRecyclerView.setAdapter(bodyPartsAdapter);*/
            }

            @Override
            public void onFailure(Call<List<Body_Parts>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.rb_male: fm.beginTransaction().replace(R.id.content_frame2,new Symptoms_Male_Fragment()).commit();
                                break;
            case R.id.rb_female: fm.beginTransaction().replace(R.id.content_frame2,new Symptoms_Female_Fragment()).commit();
                break;
        }
    }
}
