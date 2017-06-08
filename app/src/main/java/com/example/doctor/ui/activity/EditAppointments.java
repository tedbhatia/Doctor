package com.example.doctor.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.model.Appointments;

public class EditAppointments extends AppCompatActivity implements View.OnClickListener {

    private EditText name,phone,address,speciality,date,time,reason,notes;
    private Button saveButton;
    Appointments listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appointments);

        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        address=(EditText)findViewById(R.id.address);
        speciality=(EditText)findViewById(R.id.speciality);
        date=(EditText)findViewById(R.id.dateView);
        time=(EditText)findViewById(R.id.timeView);
        reason=(EditText)findViewById(R.id.reason);
        notes=(EditText)findViewById(R.id.notes);

        saveButton=(Button)findViewById(R.id.saveButton);
        
        Intent intent=new Intent();
        listItem=(Appointments)intent.getExtras().get("itemInfo");

        name.setText(listItem.getName());
        phone.setText(listItem.getPhone());
        address.setText(listItem.getAddress());
        speciality.setText(listItem.getSpeciality());
        date.setText(listItem.getDate());
        time.setText(listItem.getTime());
        reason.setText(listItem.getReason());
        notes.setText(listItem.getNotes());

        saveButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent=new Intent();
        intent.putExtra("name",name.getText());
        intent.putExtra("date",date.getText());
        intent.putExtra("time",time.getText());
        intent.putExtra("reason",reason.getText());
        intent.putExtra("notes",notes.getText());

        setResult(RESULT_OK,intent);
        finish();

        Toast.makeText(EditAppointments.this,"Changes Saved.",Toast.LENGTH_SHORT).show();
    }
}
