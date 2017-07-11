package com.example.doctor.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.SearchView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.support.service.ApiClient;
import com.example.doctor.support.service.RequestInterface;
import com.example.doctor.ui.activity.EditProfile;
import com.example.doctor.ui.activity.MyDiseases;
import com.example.doctor.ui.adapter.DiseasesAdapter;
import com.example.doctor.ui.model.Diseases;
import com.example.doctor.ui.model.ProcedureModel;
import com.example.doctor.ui.model.ProfileModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.doctor.ui.activity.LoginScreen.myProfile;
import static com.example.doctor.ui.activity.LoginScreen.userid;
import static com.example.doctor.ui.activity.MainActivity.drawer;
import static com.example.doctor.ui.activity.MainActivity.navigationView;

/**
 * Created by tejas on 9/6/17.
 */

public class MyProfileFragment extends Fragment {

    private View rootView;
    private ImageView image;
    private TextView username,email,first_name,last_name,dob,address,mobile_number,blood_group,gender;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView=inflater.inflate(R.layout.activity_my_profile,container,false);

        bindViews(rootView);
        //loadJSON();
        setData();

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
                    drawer.closeDrawer(GravityCompat.START);


                    return true;
                }
                return false;
            }
        });

        return rootView;

    }

    /*private void loadJSON() {
        final RequestInterface request = ApiClient.getClient().create(RequestInterface.class);
        Call<ProfileModel> call = request.getProfile(userid);
        call.enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                try {
                 //   Toast.makeText(getActivity(),"success",Toast.LENGTH_SHORT).show();
                    myProfile = response.body();
                    setData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                Toast.makeText(getActivity(),"failure",Toast.LENGTH_SHORT).show();
            }
        });

    }*/

    private void bindViews(View rootView) {
        image = (CircleImageView) rootView.findViewById(R.id.image);
        username = (TextView)    rootView.findViewById(R.id.username);
        email = (TextView)    rootView.findViewById(R.id.email);
        first_name = (TextView)    rootView.findViewById(R.id.first_name);
        last_name = (TextView)    rootView.findViewById(R.id.last_name);
        dob = (TextView)    rootView.findViewById(R.id.dob);
        address = (TextView) rootView.findViewById(R.id.address);
        mobile_number = (TextView) rootView.findViewById(R.id.mobile_number);
        blood_group = (TextView) rootView.findViewById(R.id.blood_group);
        gender = (TextView) rootView.findViewById(R.id.gender);
    }

    private void setData() {

//        if(!myProfile.getProfile_pic().equals(""))
//        Picasso.with(getContext()).load((Uri) myProfile.getProfile_pic()).into(image);

        if(!myProfile.getUsername().equals(""))
        username.setText(myProfile.getUsername());

        if(!myProfile.getEmail().equals(""))
        email.setText(myProfile.getEmail());

        if(!myProfile.getFirst_name().equals(""))
        first_name.setText(myProfile.getFirst_name());

        if(!myProfile.getLast_name().equals(""))
        last_name.setText(myProfile.getLast_name());

        if(!myProfile.getDob().equals(""))
        dob.setText((CharSequence) myProfile.getDob());

        if(!myProfile.getAddress().equals(""))
        address.setText((CharSequence) myProfile.getAddress());

        if(!myProfile.getPhone_number().equals(""))
        mobile_number.setText((CharSequence) myProfile.getPhone_number());

        if(!myProfile.getBlood_group().equals(""))
        blood_group.setText((CharSequence) myProfile.getBlood_group());

        if(!myProfile.getGender().equals(""))
        gender.setText((CharSequence) myProfile.getGender());


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.edit_icon, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if(id == R.id.editicon)
        {
            startActivity(new Intent(getActivity(),EditProfile.class));
        }
        return super.onOptionsItemSelected(item);
    }



}
