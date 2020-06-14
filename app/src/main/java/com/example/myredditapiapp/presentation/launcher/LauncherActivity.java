package com.example.myredditapiapp.presentation.launcher;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.example.myredditapiapp.Navigator;
import com.example.myredditapiapp.R;
import com.example.myredditapiapp.base.BaseActivity;

public class LauncherActivity extends BaseActivity {

    private LauncherViewModel launcherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        launcherViewModel = new ViewModelProvider(this, getViewModelProviderFactory()).get(LauncherViewModel.class);

        launcherViewModel.launchApplication();

        observeLaunchApplication();
    }

    private void observeLaunchApplication() {
        launcherViewModel.getLaunchApplication().observe(this, launchApplication -> {
            if (launchApplication != null && launchApplication) {
                Navigator.openLauncherScreen(this);
            }
        });
    }
}