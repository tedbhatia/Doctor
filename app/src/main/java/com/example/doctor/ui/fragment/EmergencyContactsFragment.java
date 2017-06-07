package com.example.doctor.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.doctor.R;

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

        return rootView;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.contact_button_1:{

                String str=emergencyContactNumber1.getText().toString();
                Intent phoneIntent=new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:"+str));

                startActivity(phoneIntent);

            }break;

            case R.id.contact_button_2:{

                String str=emergencyContactNumber2.getText().toString();
                Intent phoneIntent=new Intent(Intent.ACTION_DIAL);
                phoneIntent.setData(Uri.parse("tel:"+str));

                startActivity(phoneIntent);

            }break;

        }

    }

}
