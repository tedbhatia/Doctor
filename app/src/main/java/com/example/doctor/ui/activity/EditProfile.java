package com.example.doctor.ui.activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.fragment.MyProfileFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfile extends AppCompatActivity implements View.OnClickListener{

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_GALLERY_LAUNCH = 2;
    CircleImageView image;
    Button update_profile, change_password;
    EditText username,email,first_name,last_name,dob,address,mobile_number,blood_group;
    RadioButton rb_male,rb_female;
    private int mYear,mMonth,mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setTitle("Edit Profile");
        bindViews();
        collectData();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void collectData() {
//        image.setImageBitmap("@drawable/test1");
        first_name.setText("Pankaj", TextView.BufferType.EDITABLE);
        first_name.setSelection(first_name.getText().length());
        email.setText("pankaj0010g@gmail.com", TextView.BufferType.EDITABLE);
        email.setSelection(email.getText().length());
        rb_male.setChecked(true);
    }

    private void bindViews() {
        image = (CircleImageView) findViewById(R.id.image);
        username = (EditText)    findViewById(R.id.username);
        email = (EditText)    findViewById(R.id.email);
        first_name = (EditText)    findViewById(R.id.first_name);
        last_name = (EditText)    findViewById(R.id.last_name);
        dob = (EditText)    findViewById(R.id.dob);
        address = (EditText) findViewById(R.id.address);
        mobile_number = (EditText) findViewById(R.id.mobile_number);
        blood_group = (EditText) findViewById(R.id.blood_group);
        change_password = (Button) findViewById(R.id.change_password);
        rb_male = (RadioButton) findViewById(R.id.rb_male);
        rb_female = (RadioButton) findViewById(R.id.rb_female);
        change_password.setOnClickListener(this);
        image.setOnClickListener(this);
        dob.setOnClickListener(this);
    }

//    public void onImageClick(View view) {
//
//        if (view.getId() == R.id.image) {
//            launchDialog();
//        }
//    }

    private void launchDialog() {
        final CharSequence[] items = {"Take Photo", "Choose from Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfile.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
//                boolean result=Utility.checkPermission(MainActivity.this);
                if (items[item].equals("Take Photo")) {
//                    userChoosenTask="Take Photo";
//                    if(result)
                    launchCamera();
                } else if (items[item].equals("Choose from Gallery")) {
                    launchGallery();
                }

            }
        });
        builder.show();
    }

    private void launchGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        startActivityForResult(intent, REQUEST_GALLERY_LAUNCH);
    }

    private void launchCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap photo = (Bitmap) extras.get("data");
//            photo = getRoundedRectBitmap(photo, 200);
            image.setImageBitmap(photo);
        }
        if (requestCode == REQUEST_GALLERY_LAUNCH && resultCode == RESULT_OK) {
            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                image.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.update_profile:
                showToast("Updated successfully");
                finish();
                break;
            case R.id.change_password:
                startActivity(new Intent(EditProfile.this, ChangePassword.class));
                break;
            case R.id.image:
                launchDialog();
                break;
            case R.id.dob:
                final Calendar mCurrentDate=Calendar.getInstance();
                mYear=mCurrentDate.get(Calendar.YEAR);
                mMonth=mCurrentDate.get(Calendar.MONTH);
                mDay=mCurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(EditProfile.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        mCurrentDate.set(Calendar.YEAR,year);
                        mCurrentDate.set(Calendar.MONTH,month);
                        mCurrentDate.set(Calendar.DAY_OF_MONTH,dayOfMonth);

                        String myFormat="dd/MM/yyyy";
                        SimpleDateFormat sdf=new SimpleDateFormat(myFormat, Locale.ENGLISH);

                        dob.setText(sdf.format(mCurrentDate.getTime()));

                    }
                },mYear,mMonth,mDay);
//                mDatePicker.setTitle("Select Date");
                mDatePicker.show();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home){

            this.finish();

        }
        return super.onOptionsItemSelected(item);

    }


    private void showToast(String string){
        Toast.makeText(this,string,Toast.LENGTH_SHORT).show();
    }}