package com.example.myredditapiapp.presentation.launcher;

import com.example.myredditapiapp.presentation.launcher.category.CategoryFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class LauncherActivityFragmentProvider {

    @ContributesAndroidInjector
    abstract CategoryFragment provideCategoryFragment();
}