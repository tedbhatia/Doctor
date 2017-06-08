package com.example.doctor.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.example.doctor.R;
import com.example.doctor.ui.adapter.FindDoctorAdapter;
import com.example.doctor.ui.model.find_doctor_model;

/**
 * Created by tejas on 8/6/17.
 */

public class FindDoctorsFragment extends android.support.v4.app.Fragment implements FindDoctorAdapter.MyClickListener, SearchView.OnQueryTextListener{

    private RecyclerView recyclerView;
    private FindDoctorAdapter findDoctorAdapter;
    private SearchView actionSearch;
    List<find_doctor_model> model;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_find_doctor,container,false);
        model = new ArrayList<>();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);

        initRecyclerView();  //initialising RecyclerView
        prepareData(model);


        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.dashboard, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        actionSearch= (SearchView) searchItem.getActionView();

        actionSearch.setQueryHint("Name or Info");
        actionSearch.setOnQueryTextListener(this);

        EditText searchEditText = (EditText) actionSearch.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(android.R.color.white));
        searchEditText.setHintTextColor(getResources().getColor(android.R.color.white));

    }

    private void prepareData(List<find_doctor_model> model) {


        model.add(new find_doctor_model("Aviral"+"\n", " MD - Dermatology MBBS\n" +
                "35 years experience\n" +
                "Dermatologist .", R.drawable.doctor, "8004854630"+"\n"));
        model.add(new find_doctor_model("Seth"+"\n", " MBBS MS - General Surgery MCh - Urology\n" +
                "15 years experience\n" +
                "Urological Surgeon , Urologist.", R.drawable.doctor, "8004854630"+"\n"));
        model.add(new find_doctor_model("Kamlesh"+"\n", " MBBS MS - General Surgery MCh - Neuro Surgery\n" +
                "12 years experience\n" +
                "Spine Surgeon , Neurosurgeon.", R.drawable.doctor, "8004854630"+"\n"));
        model.add(new find_doctor_model("Pankaj"+"\n", " BDS MDS - Oral & Maxillofacial Surgery\n" +
                "6 years experience\n" +
                "Dentist.", R.drawable.doctor, "8004854630"+"\n"));
        model.add(new find_doctor_model("Shrut"+"\n", " MBBS MCh - Cardio Thoracic and Vascular Surgery\n" +
                "4 years experience\n" +
                "Cardiothoracic and Vascular Surgeon.", R.drawable.doctor, "8004854630"+"\n"));
        model.add(new find_doctor_model("Varun"+"\n", " MBBS MS - General Surgery DNB - Surgical Oncology\n" +
                "10 years experience\n" +
                "Surgical Oncologist.", R.drawable.doctor, "8004854630"+"\n"));
        model.add(new find_doctor_model("Lakhan"+"\n", " MSc - Dietitics / Nutrition\n" +
                "12 years experience\n" +
                "Dietitian/Nutritionist.", R.drawable.doctor, "8004854630"+"\n"));
        model.add(new find_doctor_model("Tejas"+"\n", " MBBS MS - Ophthalmology\n" +
                "11 years experience\n" +
                "Ophthalmologist/ Eye Surgeon.", R.drawable.doctor, "8004854630"+"\n"));
        model.add(new find_doctor_model("Bhavesh"+"\n", " MBBS DNB - Obstetrics & Gynecology\n" +
                "12 years experience\n" +
                "Gynecologist.", R.drawable.doctor, "8004854630"+"\n"));
        model.add(new find_doctor_model("Priyanshu"+"\n", " MBBS DNB - Obstetrics & Gynecology\n" +
                "4 years experience\n" +
                "Gynecologist , Obstetrician , Infertility Specialist.", R.drawable.doctor, "8004854630"+"\n"));
        model.add(new find_doctor_model("Bajrang"+"\n", " MBBS DNB - General Medicine\n" +
                "16 years experience\n" +
                "General Physician.", R.drawable.doctor, "8004854630"+"\n"));

        findDoctorAdapter.addAll(model);
    }

    private void initRecyclerView() {


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);

        findDoctorAdapter = new FindDoctorAdapter(getActivity(), model);
        findDoctorAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(findDoctorAdapter);
    }

    @Override
    public void onItemClick(int position, View v) {
        Toast.makeText(getActivity(), "Position : " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        findDoctorAdapter.getFilter().filter(newText);
        return true;
    }

}
