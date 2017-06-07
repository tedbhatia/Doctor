package com.example.doctor.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.doctor.R;
import com.example.doctor.ui.model.Insurance;

public class InsuranceEdit extends AppCompatActivity {

    private Insurance insurance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_edit);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(getIntent().hasExtra("insurance")){
            insurance = (Insurance) getIntent().getSerializableExtra("insurance");
            bindView();
        }

    }

    private void bindView() {
        if(!(insurance.getPlan().equals(""))) {
            ((EditText) findViewById(R.id.plan_edit)).setText(insurance.getPlan());
            ((EditText) findViewById(R.id.plan_edit)).setSelection(((EditText) findViewById(R.id.plan_edit)).length());
        }
        if(!(insurance.getDate().equals(""))) {
            ((EditText) findViewById(R.id.date_edit)).setText(insurance.getDate());
            ((EditText) findViewById(R.id.date_edit)).setSelection(((EditText) findViewById(R.id.date_edit)).length());
        }
        if(insurance.getDuration()!=null) {
            ((EditText) findViewById(R.id.duration_edit)).setText(insurance.getDuration());
            ((EditText) findViewById(R.id.duration_edit)).setSelection(((EditText) findViewById(R.id.duration_edit)).length());
        }
        if(!(insurance.getNote().equals(""))) {
            ((EditText) findViewById(R.id.note_edit)).setText(insurance.getNote());
            ((EditText) findViewById(R.id.note_edit)).setSelection(((EditText) findViewById(R.id.note_edit)).length());
        }
        ((Button)findViewById(R.id.EditButton)).setText("SAVE");
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
