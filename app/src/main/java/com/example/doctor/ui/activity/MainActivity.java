package com.example.doctor.ui.activity;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.internal.NavigationMenu;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.doctor.R;
import com.example.doctor.ui.fragment.EmergencyContactsFragment;
import com.example.doctor.ui.fragment.FindDoctorsFragment;
import com.example.doctor.ui.fragment.Health_Acc_Fragment;
import com.example.doctor.ui.fragment.MedicineFragment;
import com.example.doctor.ui.fragment.MyProfileFragment;
import com.example.doctor.ui.fragment.NotificationsFragment;
import com.example.doctor.ui.fragment.ProcedureFragment;
import com.example.doctor.ui.fragment.Symptoms_Fragment;

import static com.example.doctor.ui.activity.LoginScreen.MY_SHARED_PREFERENCES;
import static com.example.doctor.ui.activity.LoginScreen.loggedIn;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
    DrawerLayout drawer;
    MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindView();
        setTitle("My Health Account");

    }

    private void bindView() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        if (!loggedIn) {
            navigationView.getMenu().getItem(7).setVisible(false);
        }

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new Health_Acc_Fragment()).commit();

        View view = navigationView.getHeaderView(0);
        view.findViewById(R.id.imageView).setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (loggedIn == true) {
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
        } else {
            startActivity(new Intent(this, LoginScreen.class));
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.health_acc) {
            setTitle("My Health Account");
            fm.beginTransaction().replace(R.id.content_frame, new Health_Acc_Fragment()).commit();

        } else if (id == R.id.symptoms) {
            setTitle("Symptoms");
            fm.beginTransaction().replace(R.id.content_frame, new Symptoms_Fragment()).commit();

        } else if (id == R.id.find_docs) {
            setTitle("Find Doctors");
            fm.beginTransaction().replace(R.id.content_frame, new FindDoctorsFragment()).commit();

        } else if (id == R.id.emergency) {
            setTitle("Emergency Contacts");
            fm.beginTransaction().replace(R.id.content_frame, new EmergencyContactsFragment()).commit();

        } else if (id == R.id.procedure) {
            setTitle("Procedures");
            fm.beginTransaction().replace(R.id.content_frame, new ProcedureFragment()).commit();

        } else if (id == R.id.notifications) {
            setTitle("Notifications");
            fm.beginTransaction().replace(R.id.content_frame, new NotificationsFragment()).commit();

        } else if (id == R.id.meds) {
            setTitle("Medicines");
            fm.beginTransaction().replace(R.id.content_frame, new MedicineFragment()).commit();

        } else if (id == R.id.logout) {
            if (loggedIn == true) {
                loggedIn = false;
                Toast.makeText(MainActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = getSharedPreferences(MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("password");
                editor.commit();
                Intent intent = new Intent(MainActivity.this, LoginScreen.class);
                startActivity(intent);
            } else {
                //Toast.makeText(MainActivity.this, "Log In First!", Toast.LENGTH_SHORT).show();
            }
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        if (loggedIn) {
            setTitle("My Profile");
            fm.beginTransaction().replace(R.id.content_frame, new MyProfileFragment()).commit();
            drawer.closeDrawers();
        } else {
            Toast.makeText(this, "Please Register or Login to use this feature!", Toast.LENGTH_SHORT).show();
        }
    }


}
