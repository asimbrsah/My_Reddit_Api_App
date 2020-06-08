package com.example.myredditapiapp.di.modules;

import com.example.myredditapiapp.presentation.splash.SplashActivity;
import com.example.myredditapiapp.presentation.launcher.LauncherActivityFragmentProvider;
import com.example.myredditapiapp.presentation.launcher.LauncherActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityProviderModule {

    @ContributesAndroidInjector
    public abstract SplashActivity provideSplashActivity();

    @ContributesAndroidInjector(modules = LauncherActivityFragmentProvider.class)
    public abstract LauncherActivity provideLauncherActivity();
}