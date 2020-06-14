package com.example.myredditapiapp.presentation.main;

import com.example.myredditapiapp.presentation.main.category.CategoryFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainActivityFragmentProvider {

    @ContributesAndroidInjector
    abstract CategoryFragment provideCategoryFragment();
}