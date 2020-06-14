package com.example.myredditapiapp.base;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.example.myredditapiapp.factory.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    protected ViewModelProviderFactory getViewModelProviderFactory() {
        return viewModelProviderFactory;
    }

    protected void setUpToolBar(@NonNull Toolbar toolBar, @NonNull String title) {
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowCustomEnabled(false);
            actionBar.setTitle(title);
        }
    }
}