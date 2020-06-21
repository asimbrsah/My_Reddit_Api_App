package com.example.myredditapiapp.presentation.main;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.myredditapiapp.R;
import com.example.myredditapiapp.base.BaseActivity;
import com.example.myredditapiapp.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        MainViewModel mainViewModel = new ViewModelProvider(this, getViewModelProviderFactory()).get(MainViewModel.class);

        navController = Navigation.findNavController(this, R.id.fragment_nav_host);
        NavigationUI.setupWithNavController(activityMainBinding.lytBottomNavigation, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp();
    }
}