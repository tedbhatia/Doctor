package com.example.doctor.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        setTitle("Doctor");
        bindViews();

    }

    private void bindViews() {
        username= (EditText) findViewById(R.id.input_name);
        password= (EditText) findViewById(R.id.input_password);
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
                if(validPassword())
                    callinglogin();
                else
                    Toast.makeText(LoginScreen.this,"Password Should be greater than 6 characters",Toast.LENGTH_SHORT).show();

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

    private boolean validPassword() {
        if(password.length()>=6)
        {
            return true;
        }
        else
            return false;
    }

    private void callingGoogle() {
    }

    private void callingFb() {
    }

    private void callingSignup() {
        startActivity (new Intent(LoginScreen.this, SignUp.class));
    }

    private void callinglogin() {
        startActivity (new Intent(LoginScreen.this, MainActivity.class));

    }

    private void forgotpassword() {
        LayoutInflater li=LayoutInflater.from(LoginScreen.this);
        View v=li.inflate(R.layout.forgotpasswordprompt,null);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
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


}