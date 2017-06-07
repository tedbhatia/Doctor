package com.example.doctor.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.doctor.R;
import com.example.doctor.ui.model.Insurance;
import com.example.doctor.ui.model.Measurement_Info;

public class MeasurementsEdit extends AppCompatActivity {

    private Measurement_Info measurement_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurements_edit);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(getIntent().hasExtra("measurement")){
            measurement_info = (Measurement_Info) getIntent().getSerializableExtra("measurement");
            bindView();
        }
    }

    private void bindView() {
        if(!(measurement_info.getHeight().equals(""))) {
            ((EditText) findViewById(R.id.height_edit)).setText(measurement_info.getHeight());
            ((EditText) findViewById(R.id.height_edit)).setSelection(((EditText) findViewById(R.id.height_edit)).length());
        }
        if(!(measurement_info.getWeight().equals(""))) {
            ((EditText) findViewById(R.id.weight_edit)).setText(measurement_info.getWeight());
            ((EditText) findViewById(R.id.weight_edit)).setSelection(((EditText) findViewById(R.id.weight_edit)).length());
        }
        if(measurement_info.getBloodPressure()!=null) {
            ((EditText) findViewById(R.id.BP_edit)).setText(measurement_info.getBloodPressure());
            ((EditText) findViewById(R.id.BP_edit)).setSelection(((EditText) findViewById(R.id.BP_edit)).length());
        }
        if(!(measurement_info.getBloodSugar().equals(""))) {
            ((EditText) findViewById(R.id.bSugar_edit)).setText(measurement_info.getBloodSugar());
            ((EditText) findViewById(R.id.bSugar_edit)).setSelection(((EditText) findViewById(R.id.bSugar_edit)).length());
        }
        if(!(measurement_info.getCholesterol().equals(""))) {
            ((EditText) findViewById(R.id.cholesterol_edit)).setText(measurement_info.getCholesterol());
            ((EditText) findViewById(R.id.cholesterol_edit)).setSelection(((EditText) findViewById(R.id.cholesterol_edit)).length());
        }
        ((Button)findViewById(R.id.EditButton2)).setText("SAVE");
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
