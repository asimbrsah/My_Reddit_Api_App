package com.example.myredditapiapp.di.modules;

import com.example.myredditapiapp.presentation.main.MainActivity;
import com.example.myredditapiapp.presentation.launcher.LauncherActivity;
import com.example.myredditapiapp.presentation.main.MainActivityFragmentProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityProviderModule {

    @ContributesAndroidInjector
    public abstract LauncherActivity provideLauncherActivity();

    @ContributesAndroidInjector(modules = MainActivityFragmentProvider.class)
    public abstract MainActivity provideMainActivity();
}