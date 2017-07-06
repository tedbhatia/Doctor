package com.example.doctor.ui.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.support.service.ApiClient;
import com.example.doctor.support.service.RequestInterface;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.net.Authenticator;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    private TextView emailText;
    private EditText username;
    private TextView passwordText;
    private EditText password;
    private Button btnLogin;
    private Button btnSignup;
    private TextView forgotPassword;
    private LoginButton iconFb;
    private ImageButton iconGoogle;
    private TextView termsConditions;
    private TextView skip;
    private SharedPreferences sharedPreferences;
    public static final String MY_SHARED_PREFERENCES = "MyPrefs";
    public static boolean loggedIn = false;
    private ProgressDialog progressDialog;
    CallbackManager callbackManager;
    private GoogleApiClient mGoogleApiClient;
    private SignInButton googleSigninButton;
    private Authenticator pAuth;
    private FirebaseAuth mAuth;
    public int userid;

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

        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.Google_id)).requestEmail().build();
        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(LoginScreen.this, LoginScreen.this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();

        bindViews();


    }

    private void bindViews() {
        username = (EditText) findViewById(R.id.input_name);
        password = (EditText) findViewById(R.id.input_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        forgotPassword = (TextView) findViewById(R.id.forgot_password);
        iconFb = (LoginButton) findViewById(R.id.facebook_login);
        googleSigninButton = (SignInButton) findViewById(R.id.google_login);
        termsConditions = (TextView) findViewById(R.id.terms_conditions);
        skip = (TextView) findViewById(R.id.skipText);
        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        termsConditions.setOnClickListener(this);
        googleSigninButton.setOnClickListener(this);
        iconFb.setOnClickListener(this);
        skip.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
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
            case R.id.facebook_login:
                callingFb();
                break;
            case R.id.google_login:
                callingGoogle();
                break;
            case R.id.skipText:
                startActivity(new Intent(LoginScreen.this, MainActivity.class));

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
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(intent, 400);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 400) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            goMainScreen();

        } else {
            Toast.makeText(LoginScreen.this, "Login Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void goMainScreen() {
        //  Intent intent=new Intent(this,Screen2.class);
        //     intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        //startActivity(intent);
    }


    private void callingFb() {
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginScreen.this, "Success", Toast.LENGTH_SHORT);
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginScreen.this, "Login Canceled", Toast.LENGTH_SHORT);
            }

            @Override
            public void onError(FacebookException e) {

            }
        });
    }

    private void callingSignup() {
        startActivity(new Intent(LoginScreen.this, SignUp.class));
    }

    private void callinglogin() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username.getText().toString());
        editor.putString("password", password.getText().toString());
        editor.commit();
        String user = username.getText().toString();
        String pass = password.getText().toString();
        loadJSON(user,pass);
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitByBackKey();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    protected void exitByBackKey() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void initializeGPlusSettings() {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void loadJSON(String user, String pass) {
        final RequestInterface request = ApiClient.getClient().create(RequestInterface.class);
        Call<ResponseBody> call = request.login(user,pass);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String body = response.body().string();
                    if(body.equals("Wrong Username or Password")) {
                        Toast.makeText(LoginScreen.this,body,Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        userid=Integer.valueOf(body);
                        Toast.makeText(LoginScreen.this, "Logged in as " + body, Toast.LENGTH_SHORT).show();
                        loggedIn = true;
                        startActivity(new Intent(LoginScreen.this, MainActivity.class));
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginScreen.this,"Failure",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
