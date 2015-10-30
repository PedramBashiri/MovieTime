package com.example.pedram.movietime;

import android.app.Application;

import com.facebook.FacebookSdk;

/**
 * Created by Pedram on 4/28/2015.
 */
public class MyApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());

    }
}
