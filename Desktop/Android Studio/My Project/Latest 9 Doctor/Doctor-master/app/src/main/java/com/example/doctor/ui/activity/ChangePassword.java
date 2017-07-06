package com.example.doctor.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doctor.R;

public class ChangePassword extends AppCompatActivity implements OnClickListener{

    EditText current_password, new_password, confirm_new_password;
    Button update_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Change Password");
        bindViews();
    }

    private void bindViews() {
        current_password = (EditText) findViewById(R.id.current_password);
        new_password= (EditText) findViewById(R.id.new_password);
        confirm_new_password = (EditText) findViewById(R.id.confirm_new_password);
        update_password = (Button) findViewById(R.id.update_password);
        update_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.update_password){
            if(!currentPasswordMatches()){
                showToast("Current password is incorrect");
            }
            else if(!passwordsMatch()){
                showToast("New passwords do not match");
            }
            else if (!validPassword()){
                showToast("Length of new password must be atleast 6 characters long");
            }
            else {
                showToast("Password updated successfully");
                startActivity(new Intent(ChangePassword.this, EditProfile.class));
            }
        }
    }
    private void showToast(String string){
        Toast.makeText(this,string,Toast.LENGTH_SHORT).show();
    }

    private boolean currentPasswordMatches(){
        return current_password.getText().toString().equals("pankaj");
    }
    private boolean passwordsMatch(){
        return new_password.getText().toString().equals(confirm_new_password.getText().toString());
    }

    private boolean validPassword() {
        return new_password.getText().toString().length()>=6;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==android.R.id.home){

            this.finish();

        }
        return super.onOptionsItemSelected(item);

    }
}