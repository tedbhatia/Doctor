package com.example.doctor.ui.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.fragment.MyProfileFragment;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {
    private TextView emailText;
    private EditText username;
    private TextView passwordText;
    private EditText password;
    private Button btnLogin;
    private Button btnSignup;
    private TextView forgotPassword;
    private ImageButton iconFb;
    private ImageButton iconGoogle;
    private TextView termsConditions;
    private SharedPreferences sharedPreferences;
    public static final String MY_SHARED_PREFERENCES = "MyPrefs";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        setTitle("Doctor");
        sharedPreferences = getSharedPreferences(MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);

// Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(LoginScreen.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(LoginScreen.this,
                    Manifest.permission.CAMERA)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(LoginScreen.this,
                        new String[]{Manifest.permission.CAMERA}, 400);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        bindViews();


    }

    private void bindViews() {
        username = (EditText) findViewById(R.id.input_name);
        password = (EditText) findViewById(R.id.input_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        forgotPassword = (TextView) findViewById(R.id.forgot_password);
        iconFb = (ImageButton) findViewById(R.id.icon_fb);
        iconGoogle = (ImageButton) findViewById(R.id.icon_google);
        termsConditions = (TextView) findViewById(R.id.terms_conditions);
        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        termsConditions.setOnClickListener(this);
        iconGoogle.setOnClickListener(this);
        iconFb.setOnClickListener(this);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.isIndeterminate();
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                //work
                String restoredUsername = sharedPreferences.getString("username", null);
                if (restoredUsername != null) {
                    username.setText(restoredUsername);
                    username.setSelection(username.getText().length());
                }
                String restoredPassword = sharedPreferences.getString("password", null);
                if (restoredPassword != null) {
                    password.setText(restoredPassword);
                    password.setSelection(password.getText().length());
                }
                if (restoredUsername != null && restoredPassword != null) {
                    callinglogin();
                }

                progressDialog.dismiss();
            }
        }, 3000);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.terms_conditions:
                termsandconditions();
                break;
            case R.id.forgot_password:
                forgotpassword();
                break;
            case R.id.btn_login: {

                if (validPassword())
                    callinglogin();
                else
                    Toast.makeText(LoginScreen.this, "Password Should be greater than 6 characters", Toast.LENGTH_SHORT).show();

            }
            break;
            case R.id.btn_signup:
                callingSignup();
                break;
            case R.id.icon_fb:
                callingFb();
                break;
            case R.id.icon_google:
                callingGoogle();
                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 400: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    private boolean validPassword() {
        if (password.length() >= 6) {
            return true;
        } else
            return false;
    }

    private void callingGoogle() {
    }

    private void callingFb() {
    }

    private void callingSignup() {
        startActivity(new Intent(LoginScreen.this, SignUp.class));
    }

    private void callinglogin() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username.getText().toString());
        editor.putString("password", password.getText().toString());
        editor.commit();
        Toast.makeText(this, "Logged in as " + username.getText().toString(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginScreen.this, MainActivity.class));

    }

    private void forgotpassword() {
        LayoutInflater li = LayoutInflater.from(LoginScreen.this);
        View v = li.inflate(R.layout.forgotpasswordprompt, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(v);

        builder.setTitle("Forgot Password");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();


    }

    private void termsandconditions() {
    }

    public boolean onKeyDown(int keyCode, KeyEvent event){

        if(keyCode==KeyEvent.KEYCODE_BACK){
            exitByBackKey();
            return true;
        }
        return super.onKeyDown(keyCode,event);

    }

    protected void exitByBackKey() {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you wish to exit?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }


}
