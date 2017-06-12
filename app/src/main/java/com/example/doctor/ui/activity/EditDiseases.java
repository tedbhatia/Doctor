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
import com.example.doctor.ui.model.Diseases;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditDiseases extends AppCompatActivity {

    private Button button;
    private Diseases disease;
    private EditText date_edit;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diseases);

        date_edit = (EditText) findViewById(R.id.date_edit);
        date_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar mCurrentDate = Calendar.getInstance();
                mYear = mCurrentDate.get(Calendar.YEAR);
                mMonth = mCurrentDate.get(Calendar.MONTH);
                mDay = mCurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(EditDiseases.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        mCurrentDate.set(Calendar.YEAR, year);
                        mCurrentDate.set(Calendar.MONTH, month);
                        mCurrentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String myFormat = "dd/MM/yyyy";
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

                        date_edit.setText(sdf.format(mCurrentDate.getTime()));

                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();

            }
        });

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button = (Button) findViewById(R.id.editButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditDiseases.this, "New Disease Added.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        ((TextView) findViewById(R.id.DiseaseText)).setText("Add Disease");

        if (getIntent().hasExtra("disease")) {

            disease = (Diseases) getIntent().getSerializableExtra("disease");
            bindView();

        }

    }

    private void bindView() {

        if (!(disease.getDiseaseName().equals(""))) {
            ((EditText) findViewById(R.id.disease_name_edit)).setText(disease.getDiseaseName());
            ((EditText) findViewById(R.id.disease_name_edit)).setSelection(((EditText) findViewById(R.id.disease_name_edit)).length());
        }

        if (!(disease.getDate().equals(""))) {
            ((EditText) findViewById(R.id.date_edit)).setText(disease.getDate());
            ((EditText) findViewById(R.id.date_edit)).setSelection(((EditText) findViewById(R.id.date_edit)).length());
        }

        if (!(disease.getNotes().equals(""))) {
            ((EditText) findViewById(R.id.notes_edit)).setText(disease.getNotes());
            ((EditText) findViewById(R.id.notes_edit)).setSelection(((EditText) findViewById(R.id.notes_edit)).length());
        }

        ((TextView) findViewById(R.id.DiseaseText)).setText("Edit Disease");

        button = (Button) findViewById(R.id.editButton);
        button.setText("SAVE CHANGES");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(EditDiseases.this, "Disease Edited.", Toast.LENGTH_SHORT).show();
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



