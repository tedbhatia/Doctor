package com.example.doctor.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.support.service.ApiClient;
import com.example.doctor.support.service.RequestInterface;
import com.example.doctor.ui.activity.EditProfile;
import com.example.doctor.ui.activity.MedicineDetail;
import com.example.doctor.ui.adapter.FindDoctorAdapter;
import com.example.doctor.ui.adapter.MedicineAdapter;
import com.example.doctor.ui.model.MedicineModel;
import com.example.doctor.ui.model.find_doctor_model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.doctor.ui.activity.MainActivity.navigationView;

/**
 * Created by tejas on 13/6/17.
 */

public class MedicineFragment extends android.support.v4.app.Fragment implements MedicineAdapter.MyClickListener, SearchView.OnQueryTextListener{
    private RecyclerView recyclerView;
    private MedicineAdapter medicineAdapter;
    private SearchView actionSearch;
    private View rootView;
    private TextView username;
    private TextView email;
    List<MedicineModel> model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView=inflater.inflate(R.layout.fragment_medicine_list,container,false);
        model = new ArrayList<>();
        bindViews(rootView);
        initRecyclerView(rootView);

        loadJSON();


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
        Call<List<MedicineModel>> call = request.getJSONmed();
        call.enqueue(new Callback<List<MedicineModel>>() {
            @Override
            public void onResponse(Call<List<MedicineModel>> call, Response<List<MedicineModel>> response) {
                try {
//                    Toast.makeText(getApplicationContext(),response.body().string(),Toast.LENGTH_SHORT).show();
                    model = response.body();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                medicineAdapter = new MedicineAdapter(getContext(),model);
                recyclerView.setAdapter(medicineAdapter);
            }

            @Override
            public void onFailure(Call<List<MedicineModel>> call, Throwable t) {

            }
        });
    }

    private void bindViews(View rootView) {
        username=(TextView)rootView.findViewById(R.id.username);
        email=(TextView)rootView.findViewById(R.id.email);
    }

    /*private void prepareData() {

        model.add(new MedicineModel("Abacavir","2 doses daily","Not more than 2 a day.","Headache","Ziagen"));
        model.add(new MedicineModel("Balsalazide","3 doses daily","Not more than 3 a day.","Diarrhea","Colazal"));
        model.add(new MedicineModel("Banophen","2 doses daily","Not more than 2 a day.","Dizziness","Banzel"));
        model.add(new MedicineModel("Calcid","3 doses daily","Not more than 4 a day.","Belching","Miralac"));
        model.add(new MedicineModel("Ceftin","2 doses daily","Not more than 2 a day.","Vomiting","Ceftin"));
        model.add(new MedicineModel("Derifenacin","1 doses daily","Not more than 1 a day.","Constipation","Enablex"));
        model.add(new MedicineModel("Ecotrin","4 doses daily","Not more than 6 a day.","Nausea","Acuprin"));
        model.add(new MedicineModel("Ganciclovir","3 doses daily","Not more than 6 a day.","Mouth Sores","Cytovene"));
        model.add(new MedicineModel("Ibuprofen","3 doses daily","Not more than 4 a day.","Unexplained weight gain","Addaprin"));
        model.add(new MedicineModel("Karidium","1 doses daily","Not more than 1 a day.","Seizures","ACT"));
        model.add(new MedicineModel("Magnaprin","3 doses daily","Not more than 4 a day.","Nausea","Empirin"));
        model.add(new MedicineModel("Paliperidone","1 doses daily","Not more than 1 a day.","Increased Saliva","Invega"));

        medicineAdapter.addAll(model);
    }*/


    private void initRecyclerView(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);

        medicineAdapter = new MedicineAdapter(getActivity(), model);
        medicineAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(medicineAdapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.dashboard, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        actionSearch= (SearchView) searchItem.getActionView();

        actionSearch.setQueryHint("Medicine Name");
        actionSearch.setOnQueryTextListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if(id == R.id.editicon)
        {
            startActivity(new Intent(getActivity(),MedicineDetail.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        medicineAdapter.getFilter().filter(newText);
        return true;
    }

    @Override
    public void onItemClick(int position, View v) {
        Intent intent = new Intent(getActivity(),MedicineDetail.class);
        intent.putExtra("meds",model.get(position));
        startActivity(intent);
    }
}
