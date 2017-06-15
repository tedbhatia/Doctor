package com.example.doctor.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

import com.example.doctor.R;
import com.example.doctor.ui.activity.EditProfile;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tejas on 9/6/17.
 */

public class MyProfileFragment extends android.support.v4.app.Fragment {

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
        setData();

        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {


                    Health_Acc_Fragment health_acc_fragment = new Health_Acc_Fragment();

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
        username.setText("pankaj0010");
        email.setText("pankaj0010g@gmail.com");
        first_name.setText("Paankaj");
        last_name.setText("Kumar");
        dob.setText("1.1.1");
        address.setText("steerrt 5, db road, hello world, india");
        mobile_number.setText("4325347");
        blood_group.setText("B+");
        gender.setText("Male");
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
