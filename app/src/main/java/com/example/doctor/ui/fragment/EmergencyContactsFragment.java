package com.example.doctor.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.doctor.R;

import static com.example.doctor.ui.activity.MainActivity.navigationView;

/**
 * Created by SHIVIKA NAGPAL on 01-06-2017.
 */

public class EmergencyContactsFragment extends Fragment implements View.OnClickListener {

    ImageButton contactButton1,contactButton2;
    TextView emergencyContactNumber1, emergencyContactNumber2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_emergency_contacts,container,false);

        contactButton1=(ImageButton) rootView.findViewById(R.id.contact_button_1);
        contactButton2=(ImageButton) rootView.findViewById(R.id.contact_button_2);
        emergencyContactNumber1=(TextView)rootView.findViewById(R.id.emergency_contact_number_1);
        emergencyContactNumber2=(TextView)rootView.findViewById(R.id.emergency_contact_number_2);

        contactButton1.setOnClickListener(this);
        contactButton2.setOnClickListener(this);
        emergencyContactNumber1.setOnClickListener(this);
        emergencyContactNumber2.setOnClickListener(this);


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

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.emergency_contact_number_1:

            case R.id.contact_button_1:{

                String str=emergencyContactNumber1.getText().toString();
                Intent phoneIntent=new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:"+str));

                startActivity(phoneIntent);

            }break;

            case R.id.emergency_contact_number_2:

            case R.id.contact_button_2:{

                String str=emergencyContactNumber2.getText().toString();
                Intent phoneIntent=new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:"+str));

                startActivity(phoneIntent);

            }break;


        }

    }

}