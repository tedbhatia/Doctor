package com.example.doctor.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doctor.R;
import com.example.doctor.support.service.ApiClient;
import com.example.doctor.support.service.RequestInterface;
import com.example.doctor.ui.activity.MedicineDetail;
import com.example.doctor.ui.activity.ProcedureDetail;
import com.example.doctor.ui.adapter.MedicineAdapter;
import com.example.doctor.ui.adapter.ProcedureAdapter;
import com.example.doctor.ui.model.MedicineModel;
import com.example.doctor.ui.model.ProcedureModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.doctor.ui.activity.MainActivity.drawer;
import static com.example.doctor.ui.activity.MainActivity.navigationView;

/**
 * Created by tejas on 13/6/17.
 */

public class ProcedureFragment extends android.support.v4.app.Fragment implements ProcedureAdapter.MyClickListener, SearchView.OnQueryTextListener{
    private RecyclerView recyclerView;
    private ProcedureAdapter procedureAdapter;
    private SearchView actionSearch;
    private View rootView;
    private TextView username;
    private TextView email;
    List<ProcedureModel> model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView=inflater.inflate(R.layout.fragment_procedure_list,container,false);
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

                    drawer.closeDrawer(GravityCompat.START);
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
        Call<List<ProcedureModel>> call = request.getJSONproc();
        call.enqueue(new Callback<List<ProcedureModel>>() {
            @Override
            public void onResponse(Call<List<ProcedureModel>> call, Response<List<ProcedureModel>> response) {
                try {
//                    Toast.makeText(getApplicationContext(),response.body().string(),Toast.LENGTH_SHORT).show();
                    model = response.body();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                procedureAdapter = new ProcedureAdapter(getContext(),model);
                recyclerView.setAdapter(procedureAdapter);
            }

            @Override
            public void onFailure(Call<List<ProcedureModel>> call, Throwable t) {

            }
        });
    }

    private void bindViews(View rootView) {
        username=(TextView)rootView.findViewById(R.id.username);
        email=(TextView)rootView.findViewById(R.id.email);
    }

    /*private void prepareData() {

        model.add(new ProcedureModel("Heart Surgery"));
        model.add(new ProcedureModel("Dialysis"));
        model.add(new ProcedureModel("Kidney Replacement "));
        model.add(new ProcedureModel("Accupuncture "));
        model.add(new ProcedureModel("Root Canal "));
        model.add(new ProcedureModel("Henria Fix "));
        model.add(new ProcedureModel("Colonoscopy "));
        model.add(new ProcedureModel("Heart Surgery"));
        model.add(new ProcedureModel("Dialysis"));
        model.add(new ProcedureModel("Kidney Replacement "));
        model.add(new ProcedureModel("Accupuncture "));
        model.add(new ProcedureModel("Root Canal "));
        model.add(new ProcedureModel("Henria Fix "));
        model.add(new ProcedureModel("Colonoscopy "));

        procedureAdapter.addAll(model);
    }
*/

    private void initRecyclerView(View rootView) {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);

        procedureAdapter = new ProcedureAdapter(getActivity(), model);
        procedureAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(procedureAdapter);
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

        actionSearch.setQueryHint("Procedure Name");
        actionSearch.setOnQueryTextListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if(id == R.id.editicon)
        {
            startActivity(new Intent(getActivity(),ProcedureDetail.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        procedureAdapter.getFilter().filter(newText);
        return true;
    }

    @Override
    public void onItemClick(int position, View v) {
        Intent intent = new Intent(getActivity(),ProcedureDetail.class);
        intent.putExtra("procs",model.get(position));
        startActivity(intent);
    }
}
