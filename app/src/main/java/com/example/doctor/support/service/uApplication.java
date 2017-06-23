package com.example.doctor.support.service;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by Harsh Sheth on 6/23/2017.
 */

public class uApplication extends Application {
    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
