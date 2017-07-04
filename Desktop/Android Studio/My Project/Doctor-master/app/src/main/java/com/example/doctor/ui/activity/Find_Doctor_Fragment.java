package com.example.doctor.ui.fragment;

import android.app.Fragment;

/**
 * Created by Aviral on 07-06-2017.
 */

public class Find_Doctor_Fragment extends Fragment MyClickListener, SearchView.OnQueryTextListener {
private RecyclerView recyclerView;
private FindDoctorAdapter findDoctorAdapter;
private SearchView actionSearch;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_find_doctor);
        Toolbar findDoctorToolbar = (Toolbar) findViewById(R.id.find_Doctor_Toolbar);
        setSupportActionBar(findDoctorToolbar);
        setTitle("Find Doctor");
        findDoctorToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        initRecyclerView();  //initialising RecyclerView

        prepareData();

        setupSearchView(); //for search view
        }


private void prepareData() {

        ArrayList<find_doctor_model> model = new ArrayList<find_doctor_model>();


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
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);

        findDoctorAdapter = new FindDoctorAdapter(this, new ArrayList<find_doctor_model>());
        recyclerView.setAdapter(findDoctorAdapter);
        }

private void setupSearchView() {
        actionSearch = (SearchView) findViewById(R.id.action_search);
        actionSearch.setQueryHint("Name or Info");
        actionSearch.setOnQueryTextListener(this);
        actionSearch.setMaxWidth(Integer.MAX_VALUE);

        EditText searchEditText = (EditText) actionSearch.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(android.R.color.white));
        searchEditText.setHintTextColor(getResources().getColor(android.R.color.white));
        }

@Override
public void onItemClick(int position, View v) {
        Toast.makeText(this, "Position : " + position, Toast.LENGTH_SHORT).show();
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
