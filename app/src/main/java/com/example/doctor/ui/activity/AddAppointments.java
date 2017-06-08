package com.example.doctor.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.doctor.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddAppointments extends AppCompatActivity implements View.OnClickListener{

    private Button addButton;
    private EditText dateView,timeView;

    private Calendar myCalendar = Calendar.getInstance();
    private int hour=myCalendar.get(Calendar.HOUR_OF_DAY),minute=myCalendar.get(Calendar.MINUTE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointments);

        dateView = (EditText) findViewById(R.id.dateView);
        dateView.setOnClickListener(this);

        timeView = (EditText) findViewById(R.id.timeView);
        timeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddAppointments.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeView.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, false);
                timePickerDialog.show();

            }
        });

        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddAppointments.this,"New Appointment Added.",Toast.LENGTH_SHORT).show();
            }
        });

    }


    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateLabel();
                }

    };
        @Override
        public void onClick(View v){

            new DatePickerDialog(AddAppointments.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }

    private void updateLabel() {

        DateFormat df=DateFormat.getDateInstance(DateFormat.LONG,Locale.ENGLISH);
        dateView.setText(df.format(myCalendar.getTime()));
    }

}
