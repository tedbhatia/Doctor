package com.example.doctor.ui.activity;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.model.Medicines;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditMedicines extends AppCompatActivity {

    private Button button;
    private Medicines medicine;
    private EditText date_edit;
    private int mYear,mMonth,mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_medicines);

        date_edit = (EditText) findViewById(R.id.date_edit);
        date_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar mCurrentDate=Calendar.getInstance();
                mYear=mCurrentDate.get(Calendar.YEAR);
                mMonth=mCurrentDate.get(Calendar.MONTH);
                mDay=mCurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(EditMedicines.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        mCurrentDate.set(Calendar.YEAR,year);
                        mCurrentDate.set(Calendar.MONTH,month);
                        mCurrentDate.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                        String myFormat="dd/MM/yyyy";
                        SimpleDateFormat sdf=new SimpleDateFormat(myFormat, Locale.ENGLISH);

                        date_edit.setText(sdf.format(mCurrentDate.getTime()));

                    }
                },mYear,mMonth,mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();

            }
        });


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button=(Button)findViewById(R.id.editButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditMedicines.this,"New Medicine Added.",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

//        ((TextView)findViewById(R.id.MedicinesText)).setText("Add Medicine");
        setTitle("Add Medicine");

        if(getIntent().hasExtra("medicine")){

            medicine=(Medicines)getIntent().getSerializableExtra("medicine");
            bindView();

        }

    }

    private void bindView(){

        if(!(medicine.getMedName().equals(""))) {
            ((EditText) findViewById(R.id.med_name_edit)).setText(medicine.getMedName());
            ((EditText) findViewById(R.id.med_name_edit)).setSelection(((EditText) findViewById(R.id.med_name_edit)).length());
        }

        if(!(medicine.getDosageAmount().equals(""))) {
            ((EditText) findViewById(R.id.dosage_amount_edit)).setText(medicine.getDosageAmount());
            ((EditText) findViewById(R.id.dosage_amount_edit)).setSelection(((EditText) findViewById(R.id.dosage_amount_edit)).length());
        }

        if(!(medicine.getUnit().equals(""))) {
            ((EditText) findViewById(R.id.unit_edit)).setText(medicine.getUnit());
            ((EditText) findViewById(R.id.unit_edit)).setSelection(((EditText) findViewById(R.id.unit_edit)).length());
        }

        if(!(medicine.getMethod().equals(""))) {
            ((EditText) findViewById(R.id.method_edit)).setText(medicine.getMethod());
            ((EditText) findViewById(R.id.method_edit)).setSelection(((EditText) findViewById(R.id.method_edit)).length());
        }

        if(!(medicine.getFrequency().equals(""))) {
            ((EditText) findViewById(R.id.frequency_edit)).setText(medicine.getFrequency());
            ((EditText) findViewById(R.id.frequency_edit)).setSelection(((EditText) findViewById(R.id.frequency_edit)).length());
        }

        if(!(medicine.getDate().equals(""))) {
            ((EditText) findViewById(R.id.date_edit)).setText(medicine.getDate());
            ((EditText) findViewById(R.id.date_edit)).setSelection(((EditText) findViewById(R.id.date_edit)).length());
        }

        if(!(medicine.getDoctorName().equals(""))) {
            ((EditText) findViewById(R.id.doctor_name_edit)).setText(medicine.getDoctorName());
            ((EditText) findViewById(R.id.doctor_name_edit)).setSelection(((EditText) findViewById(R.id.doctor_name_edit)).length());
        }

        if(!(medicine.getNotes().equals(""))) {
            ((EditText) findViewById(R.id.notes_edit)).setText(medicine.getNotes());
            ((EditText) findViewById(R.id.notes_edit)).setSelection(((EditText) findViewById(R.id.notes_edit)).length());
        }

//        ((TextView)findViewById(R.id.MedicinesText)).setText("Edit Medicine");
        setTitle("Edit Medicine");

        button=(Button)findViewById(R.id.editButton);
        button.setText("SAVE CHANGES");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(EditMedicines.this,"Medicine Edited.",Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
