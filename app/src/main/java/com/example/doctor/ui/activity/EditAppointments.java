package com.example.doctor.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.model.Appointments;

public class EditAppointments extends AppCompatActivity {

    private Button button;
    Appointments appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_appointments);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button=(Button)findViewById(R.id.editButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditAppointments.this,"New Appointment Added.",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        ((TextView)findViewById(R.id.AppointmentText)).setText("Add Appointment");

        if(getIntent().hasExtra("appointment")){

            appointment=(Appointments)getIntent().getSerializableExtra("appointment");
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

        ((TextView)findViewById(R.id.AppointmentText)).setText("Edit Appointment");

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
