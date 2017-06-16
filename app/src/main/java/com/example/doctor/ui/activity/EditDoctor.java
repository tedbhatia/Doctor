package com.example.doctor.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.model.Doctor;
import com.example.doctor.ui.model.Insurance;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditDoctor extends AppCompatActivity {

    private Doctor doctor;
    private Button button;
    private CircleImageView circleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_doctor);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button = (Button) findViewById(R.id.EditButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditDoctor.this,"Doctor Added",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        circleImageView = (CircleImageView) findViewById(R.id.display_picture);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,4000);
            }
        });

        ((TextView)findViewById(R.id.InsuranceText)).setText("Add Doctor");
        //((TextView)findViewById(R.id.InsuranceText)).setText("Add Insurance");
        if(getIntent().hasExtra("doctor")){
            doctor = (Doctor) getIntent().getSerializableExtra("doctor");
            bindView();
        }
    }

    private void bindView() {
        button = (Button) findViewById(R.id.EditButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(EditDoctor.this,"Insurance Edited",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        if(!(doctor.getName().equals(""))) {
            ((EditText) findViewById(R.id.name_edit)).setText(doctor.getName());
            //((EditText) findViewById(R.id.plan_edit)).setSelection(((EditText) findViewById(R.id.plan_edit)).length());
        }
        if(!(doctor.getType().equals(""))) {
            ((EditText) findViewById(R.id.type_edit)).setText(doctor.getType());
            //((EditText) findViewById(R.id.date_edit)).setSelection(((EditText) findViewById(R.id.date_edit)).length());
        }
        if(!(doctor.getAddress().equals(""))) {
            ((EditText) findViewById(R.id.address_edit)).setText(doctor.getAddress());
            //((EditText) findViewById(R.id.duration_edit)).setSelection(((EditText) findViewById(R.id.duration_edit)).length());
        }
        if(!(doctor.getPhone().equals(""))) {
            ((EditText) findViewById(R.id.phone_edit)).setText(doctor.getPhone());
            //((EditText) findViewById(R.id.note_edit)).setSelection(((EditText) findViewById(R.id.note_edit)).length());
        }
        if(!(doctor.getNotes().equals(""))) {
            ((EditText) findViewById(R.id.note_edit)).setText(doctor.getNotes());
            //((EditText) findViewById(R.id.note_edit)).setSelection(((EditText) findViewById(R.id.note_edit)).length());
        }
        ((Button)findViewById(R.id.EditButton)).setText("SAVE");
        ((TextView)findViewById(R.id.InsuranceText)).setText("Edit Doctor Details");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 4000:
                if(resultCode == RESULT_OK){
                    Uri uri = data.getData();
                    circleImageView.setImageURI(uri);
                }
        }
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
