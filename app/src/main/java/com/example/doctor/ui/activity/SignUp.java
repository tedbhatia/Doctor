package com.example.doctor.ui.activity;

import android.Manifest;
import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.doctor.R;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_GALLERY_LAUNCH = 2;
    ImageView image;
    Button register;
    EditText name,email,password,confirm_password;
    RadioButton rb_male,rb_female;
    Picasso picasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Sign Up");
        bindViews();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void bindViews() {
        image = (CircleImageView) findViewById(R.id.image);
        name = (EditText)    findViewById(R.id.name);
        email = (EditText)    findViewById(R.id.email);
        password = (EditText)    findViewById(R.id.password);
        confirm_password = (EditText)    findViewById(R.id.confirm_password);
        register = (Button) findViewById(R.id.register);
        rb_male = (RadioButton) findViewById(R.id.rb_male);
        rb_female = (RadioButton) findViewById(R.id.rb_female);
        register.setOnClickListener(this);
        image.setOnClickListener(this);
//        picasso.with(Screen3.this).load("https://www.hello.com/img_/hello_logo_hero.png").into(image);
    }

    private void launchDialog() {
        final CharSequence[] items = {"Take Photo", "Choose from Gallery"};

        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
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
            image.setImageBitmap(photo);

//            if (checkSelfPermission(Manifest.permission.CAMERA)
//                    != PackageManager.PERMISSION_GRANTED) {
//
//                requestPermissions(new String[]{Manifest.permission.CAMERA},
//                        REQUEST_IMAGE_CAPTURE);
//            }
//
//            Uri uri = (Uri) data.getData();
//
//            Picasso.with(this).load(Uri.parse(uri.toString())).into(image);
//            showToast("Image laodded");
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
        switch(v.getId()) {
            case R.id.register:
                if(emptyFields())   showToast("One or more fields empty");
                else if(!passwordsMatch())  showToast("Passwords do not match");
                else if(!validPassword())   showToast("Password must be atleast 6 characters long");
                else if(!validEmail())  showToast("Enter valid email address");
                else{
                    showToast("Registration Successful");
                }

                break;

            case R.id.image:
                launchDialog();
                break;
        }
    }

    private boolean validEmail() {
        String emailString = email.getText().toString();
        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailString).matches();
    }

    private boolean validPassword() {
        return password.getText().toString().length()>=6;
    }

    private boolean emptyFields() {
        boolean empty=false;
        if(isEmpty(name))   empty=true;
        if(isEmpty(email))  empty=true;
        if(isEmpty(password))  empty=true;
        if(isEmpty(confirm_password))  empty=true;
        if(!rb_male.isChecked() && !rb_female.isChecked())  empty=true;
        return empty;
    }

    private boolean isEmpty(EditText field) {
        return field.getText().toString().equals("");
    }

    private void showToast(String string){
        Toast.makeText(this,string,Toast.LENGTH_SHORT).show();
    }

    private boolean passwordsMatch(){
        return password.getText().toString().equals(confirm_password.getText().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home){

            this.finish();

        }
        return super.onOptionsItemSelected(item);

    }
}