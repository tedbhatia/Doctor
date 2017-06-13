package com.example.doctor.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doctor.R;
import com.example.doctor.ui.activity.MedicineDetail;
import com.example.doctor.ui.activity.ProcedureDetail;
import com.example.doctor.ui.adapter.MedicineAdapter;
import com.example.doctor.ui.adapter.ProcedureAdapter;
import com.example.doctor.ui.model.MedicineModel;
import com.example.doctor.ui.model.ProcedureModel;

import java.util.ArrayList;
import java.util.List;

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

        prepareData();

        return rootView;

    }

    private void bindViews(View rootView) {
        username=(TextView)rootView.findViewById(R.id.username);
        email=(TextView)rootView.findViewById(R.id.email);
    }

    private void prepareData() {

        model.add(new ProcedureModel("Heart Surgery"));
        model.add(new ProcedureModel("Dialysis"));
        model.add(new ProcedureModel("Kidney Replacement "));
        model.add(new ProcedureModel("Accupuncture "));
        model.add(new ProcedureModel("Root Canal "));
        model.add(new ProcedureModel("Henria Fix "));
        model.add(new ProcedureModel("Colonoscopy "));

        procedureAdapter.addAll(model);
    }


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