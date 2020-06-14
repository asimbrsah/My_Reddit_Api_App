package com.example.myredditapiapp;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.example.myredditapiapp.di.component.DaggerApplicationComponent;
import com.example.myredditapiapp.log.TimberLogImplementation;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class BaseApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().application(this).build();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Initializing the timber log based on build type
        TimberLogImplementation.init();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}