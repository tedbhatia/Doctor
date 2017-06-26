package com.example.doctor.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.doctor.R;
import com.example.doctor.ui.model.find_doctor_model;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorDetail extends AppCompatActivity implements View.OnClickListener{

    private TextView name, mobile_number, speciality, address, timings, description;
    private CircleImageView display_picture;
    private Button add_to_my_doctors;
    private find_doctor_model doctorDetailModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
//        doctorDetailModel = new find_doctor_model();
        bindViews();
//        setDummyData();
        if(getIntent().hasExtra("details")){
            doctorDetailModel = (find_doctor_model) getIntent().getSerializableExtra("details");
        }
        setData();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setDummyData() {
        doctorDetailModel.setName("Dr Olga Malkin");
        doctorDetailModel.setMobile_number("212-355-4510");
        doctorDetailModel.setSpeciality("Dentistry");
        doctorDetailModel.setAddress("23 Warren Street, Suite 10, New York, NY 10007");
        doctorDetailModel.setTimings("8.00 AM - 2.00 PM\n5.00 PM - 9.30 PM");
        doctorDetailModel.setDescription("Dr. Olga Malkin is a dentistry and prosthodontics doctor with offices in New York, New York.");
    }

    private void setData() {
        setTitle(doctorDetailModel.getName());
        name.setText(doctorDetailModel.getName());
        mobile_number.setText(doctorDetailModel.getMobile_number());
        speciality.setText(doctorDetailModel.getSpeciality());
        address.setText(doctorDetailModel.getAddress());
        timings.setText(doctorDetailModel.getTimings());
        description.setText(doctorDetailModel.getDescription());
        display_picture.setImageResource(doctorDetailModel.getId());

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home){

            this.finish();

        }
        return super.onOptionsItemSelected(item);

    }
    private void bindViews() {
        name = (TextView) findViewById(R.id.name);
        mobile_number = (TextView) findViewById(R.id.mobile_number);
        speciality = (TextView) findViewById(R.id.speciality);
        address = (TextView) findViewById(R.id.address);
        timings = (TextView) findViewById(R.id.timings);
        description = (TextView) findViewById(R.id.description);
        display_picture = (CircleImageView) findViewById(R.id.display_picture);
        add_to_my_doctors = (Button) findViewById(R.id.add_to_my_doctors);

//        display_picture.setOnClickListener(this);
//        add_to_my_doctors.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
//            case R.id.display_picture:
//                break;
            case R.id.add_to_my_doctors:
                break;
        }
    }
}
