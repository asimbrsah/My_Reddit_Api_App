package com.example.myredditapiapp.presentation.launcher;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.example.myredditapiapp.Navigator;
import com.example.myredditapiapp.base.BaseActivity;
import com.example.myredditapiapp.databinding.ActivityLauncherBinding;

public class LauncherActivity extends BaseActivity {

    private LauncherViewModel launcherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLauncherBinding activityLauncherBinding = ActivityLauncherBinding.inflate(getLayoutInflater());
        setContentView(activityLauncherBinding.getRoot());

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