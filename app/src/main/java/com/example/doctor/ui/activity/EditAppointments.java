package com.example.doctor.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.model.Appointments;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditAppointments extends AppCompatActivity {

    private Button button;
    private Appointments appointment;
    private EditText date_edit,time_edit;
    private int mYear,mMonth,mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appointments);

        date_edit = (EditText) findViewById(R.id.date_edit);
        date_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar mCurrentDate=Calendar.getInstance();
                mYear=mCurrentDate.get(Calendar.YEAR);
                mMonth=mCurrentDate.get(Calendar.MONTH);
                mDay=mCurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(EditAppointments.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        mCurrentDate.set(Calendar.YEAR,year);
                        mCurrentDate.set(Calendar.MONTH,month);
                        mCurrentDate.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                        String myFormat="dd/MM/yyyy";
                        SimpleDateFormat sdf=new SimpleDateFormat(myFormat,Locale.ENGLISH);

                        date_edit.setText(sdf.format(mCurrentDate.getTime()));

                    }
                },mYear,mMonth,mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();

            }
        });

        time_edit=(EditText)findViewById(R.id.time_edit);
        time_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mCurrentTime = Calendar.getInstance();
                int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mCurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(EditAppointments.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time_edit.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        button = (Button) findViewById(R.id.editButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditAppointments.this, "New Appointment Added.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

//        ((TextView) findViewById(R.id.AppointmentText)).setText("Add Appointment");
        setTitle("Add Appointment");

        if (getIntent().hasExtra("appointment")) {

            appointment = (Appointments) getIntent().getSerializableExtra("appointment");
            bindView();

        }
    }

    private void bindView(){

        if(!(appointment.getName().equals(""))) {
            ((EditText) findViewById(R.id.name_edit)).setText(appointment.getName());
            ((EditText) findViewById(R.id.name_edit)).setSelection(((EditText) findViewById(R.id.name_edit)).length());
        }

        if(!(appointment.getPhone().equals(""))) {
            ((EditText) findViewById(R.id.phone_edit)).setText(appointment.getPhone());
            ((EditText) findViewById(R.id.phone_edit)).setSelection(((EditText) findViewById(R.id.phone_edit)).length());
        }

        if(!(appointment.getAddress().equals(""))) {
            ((EditText) findViewById(R.id.address_edit)).setText(appointment.getAddress());
            ((EditText) findViewById(R.id.address_edit)).setSelection(((EditText) findViewById(R.id.address_edit)).length());
        }

        if(!(appointment.getSpeciality().equals(""))) {
            ((EditText) findViewById(R.id.speciality_edit)).setText(appointment.getSpeciality());
            ((EditText) findViewById(R.id.speciality_edit)).setSelection(((EditText) findViewById(R.id.speciality_edit)).length());
        }

        if(!(appointment.getDate().equals(""))) {
            ((EditText) findViewById(R.id.date_edit)).setText(appointment.getDate());
            ((EditText) findViewById(R.id.date_edit)).setSelection(((EditText) findViewById(R.id.date_edit)).length());
        }

        if(!(appointment.getTime().equals(""))) {
            ((EditText) findViewById(R.id.time_edit)).setText(appointment.getTime());
            ((EditText) findViewById(R.id.time_edit)).setSelection(((EditText) findViewById(R.id.time_edit)).length());
        }

        if(!(appointment.getReason().equals(""))) {
            ((EditText) findViewById(R.id.reason_edit)).setText(appointment.getReason());
            ((EditText) findViewById(R.id.reason_edit)).setSelection(((EditText) findViewById(R.id.reason_edit)).length());
        }

        if(!(appointment.getNotes().equals(""))) {
            ((EditText) findViewById(R.id.notes_edit)).setText(appointment.getNotes());
            ((EditText) findViewById(R.id.notes_edit)).setSelection(((EditText) findViewById(R.id.notes_edit)).length());
        }

//        ((TextView)findViewById(R.id.AppointmentText)).setText("Edit Appointment");
        setTitle("Edit Appointment");

        button=(Button)findViewById(R.id.editButton);
        button.setText("SAVE CHANGES");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(EditAppointments.this,"Appointment Edited.",Toast.LENGTH_SHORT).show();
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
