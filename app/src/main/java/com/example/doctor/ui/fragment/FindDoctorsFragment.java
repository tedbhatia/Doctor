package com.example.doctor.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.example.doctor.R;
import com.example.doctor.support.service.ApiClient;
import com.example.doctor.support.service.RequestInterface;
import com.example.doctor.ui.activity.DoctorDetail;
import com.example.doctor.ui.activity.EditDoctor;
import com.example.doctor.ui.activity.MapsActivity;
import com.example.doctor.ui.activity.MyDoctor;
import com.example.doctor.ui.adapter.FindDoctorAdapter;
import com.example.doctor.ui.model.find_doctor_model;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.doctor.ui.activity.MainActivity.navigationView;

/**
 * Created by tejas on 8/6/17.
 */

public class FindDoctorsFragment extends Fragment implements FindDoctorAdapter.MyClickListener, SearchView.OnQueryTextListener{

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
//        prepareData(model);
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

   //Load Json

    private void loadJSON(){
        final RequestInterface request = ApiClient.getClient().create(RequestInterface.class);
        Call<List<find_doctor_model>> call = request.getJSON();
        call.enqueue(new Callback<List<find_doctor_model>>() {
            @Override
            public void onResponse(Call<List<find_doctor_model>> call, Response<List<find_doctor_model>> response) {
                try {
//                    Toast.makeText(getApplicationContext(),response.body().string(),Toast.LENGTH_SHORT).show();
                    model = response.body();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                findDoctorAdapter = new FindDoctorAdapter(getContext(),model);
                recyclerView.setAdapter(findDoctorAdapter);
            }

            @Override
            public void onFailure(Call<List<find_doctor_model>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.dashboard, menu);
        inflater.inflate(R.menu.map,menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        actionSearch= (SearchView) searchItem.getActionView();

        actionSearch.setQueryHint("Name or Info");
        actionSearch.setOnQueryTextListener(this);

    }

//    private void prepareData(List<find_doctor_model> model) {
//
////        String name, String description, String mobile_number, String speciality, String address, String timings, CircleImageView
////        display_picture, int id) {
//        model.add(new find_doctor_model("Dr. Smith", "Reputed heart surgeon \n25 years experience\nHighly Recomended",
//                "8004854630","Dermatologist","local street, mumbai","10 AM - 5 PM" ,R.drawable.doctor));
//        model.add(new find_doctor_model("Dr. Smith", "Reputed heart surgeon \n25 years experience\nHighly Recomended",
//                "8004854630","Dermatologist","local street, mumbai","10 AM - 5 PM" ,R.drawable.doctor));
//        model.add(new find_doctor_model("Dr. Smith", "Reputed heart surgeon \n25 years experience\nHighly Recomended",
//                "8004854630","Dermatologist","local street, mumbai","10 AM - 5 PM" ,R.drawable.doctor));
//        model.add(new find_doctor_model("Dr. Smith", "Reputed heart surgeon \n25 years experience\nHighly Recomended",
//                "8004854630","Dermatologist","local street, mumbai","10 AM - 5 PM" ,R.drawable.doctor));
//        model.add(new find_doctor_model("Dr. Smith", "Reputed heart surgeon \n25 years experience\nHighly Recomended",
//                "8004854630","Dermatologist","local street, mumbai","10 AM - 5 PM" ,R.drawable.doctor));
//        model.add(new find_doctor_model("Dr. Smith", "Reputed heart surgeon \n25 years experience\nHighly Recomended",
//                "8004854630","Dermatologist","local street, mumbai","10 AM - 5 PM" ,R.drawable.doctor));
//        model.add(new find_doctor_model("Dr. Smith", "Reputed heart surgeon \n25 years experience\nHighly Recomended",
//                "8004854630","Dermatologist","local street, mumbai","10 AM - 5 PM" ,R.drawable.doctor));
//        model.add(new find_doctor_model("Dr. Smith", "Reputed heart surgeon \n25 years experience\nHighly Recomended",
//                "8004854630","Dermatologist","local street, mumbai","10 AM - 5 PM" ,R.drawable.doctor));
//        model.add(new find_doctor_model("Dr. Smith", "Reputed heart surgeon \n25 years experience\nHighly Recomended",
//                "8004854630","Dermatologist","local street, mumbai","10 AM - 5 PM" ,R.drawable.doctor));
//
//
//        findDoctorAdapter.addAll(model);
//    }

    private void initRecyclerView() {


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(linearLayoutManager);

        findDoctorAdapter = new FindDoctorAdapter(getActivity(), model);
        findDoctorAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(findDoctorAdapter);
    }

    @Override
    public void onItemClick(int position, View v, List<find_doctor_model> searchList) {
          Intent intent = new Intent (getActivity(), DoctorDetail.class);
          intent.putExtra("details", searchList.get(position));
          startActivity(intent);
//        Toast.makeText(getActivity(), "Position : " + position, Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.g_search) {
            Toast.makeText(getActivity(),"Map",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getActivity(), MapsActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
