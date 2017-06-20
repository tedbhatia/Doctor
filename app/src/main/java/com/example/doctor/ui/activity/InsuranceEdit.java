package com.example.doctor.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.model.Insurance;

public class InsuranceEdit extends AppCompatActivity {

    private Insurance insurance;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_edit);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button = (Button) findViewById(R.id.EditButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InsuranceEdit.this,"Insurance Added",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

//        ((TextView)findViewById(R.id.InsuranceText)).setText("Add Insurance");

        setTitle("Add Insurance");

        if(getIntent().hasExtra("insurance")){
            insurance = (Insurance) getIntent().getSerializableExtra("insurance");
            bindView();
        }

    }

    private void bindView() {
        button = (Button) findViewById(R.id.EditButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(InsuranceEdit.this,"Insurance Edited",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
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
        if(!(insurance.getNotes().equals(""))) {
            ((EditText) findViewById(R.id.notes_edit)).setText(insurance.getNotes());
            ((EditText) findViewById(R.id.notes_edit)).setSelection(((EditText) findViewById(R.id.notes_edit)).length());
        }
        ((Button)findViewById(R.id.EditButton)).setText("SAVE CHANGES");
//        ((TextView)findViewById(R.id.InsuranceText)).setText("Edit Insurance");
        setTitle("Edit Insurance");
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
