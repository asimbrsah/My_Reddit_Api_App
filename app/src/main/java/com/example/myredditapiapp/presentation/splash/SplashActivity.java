package com.example.myredditapiapp.presentation.splash;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.example.myredditapiapp.Navigator;
import com.example.myredditapiapp.R;
import com.example.myredditapiapp.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    private SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splashViewModel = new ViewModelProvider(this, getViewModelProviderFactory()).get(SplashViewModel.class);

        splashViewModel.launchApplication();

        observeLaunchApplication();
    }

    private void observeLaunchApplication() {
        splashViewModel.getLaunchApplication().observe(this, launchApplication -> {
            if (launchApplication != null && launchApplication) {
                Navigator.openLauncherScreen(this);
            }
        });
    }
}