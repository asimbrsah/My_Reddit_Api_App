package com.example.myredditapiapp.presentation.main;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import com.example.myredditapiapp.BuildConfig;
import com.example.myredditapiapp.Navigator;
import com.example.myredditapiapp.R;
import com.example.myredditapiapp.base.BaseActivity;
import com.example.myredditapiapp.presentation.main.category.CategoryFragment;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends BaseActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private ActionBarDrawerToggle actionBarDrawerToggle;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.lyt_drawer);
        navigationView = findViewById(R.id.navigation_view);

        setUpToolBar(toolbar, getString(R.string.all_text));
        initDrawer();

        navigationView.setNavigationItemSelectedListener(menuItem -> {
            setDrawerClick(menuItem.getItemId());
            menuItem.setChecked(true);
            drawerLayout.closeDrawers();
            return true;
        });

        mainViewModel = new ViewModelProvider(this, getViewModelProviderFactory()).get(MainViewModel.class);

        setUpInitialFragment();

        observeTransaction();
    }

    private void initDrawer() {
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NotNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    private void setDrawerClick(int itemId) {
        switch (itemId) {
            case R.id.item_all:
                setUpToolBar(toolbar, getString(R.string.all_text));
                mainViewModel.setTransaction(BuildConfig.ALL_CATEGORY_END_POINT_URL);
                break;
            case R.id.item_popular:
                setUpToolBar(toolbar, getString(R.string.popular_text));
                mainViewModel.setTransaction(BuildConfig.POPULAR_CATEGORY_END_POINT_URL);
                break;
        }
    }

    private void setUpInitialFragment() {
        Navigator.commitNowReplaceFragment(getSupportFragmentManager(),
                CategoryFragment.newInstance(BuildConfig.ALL_CATEGORY_END_POINT_URL), R.id.lyt_container);
    }

    private void observeTransaction() {
        mainViewModel.getTransactionMutableLiveData().observe(this, categoryName -> {
            if (categoryName != null) {
                setUpCategoryFragment(categoryName);
            }
        });
    }

    private void setUpCategoryFragment(String categoryName) {
        Navigator.commitAddFragmentWithBackStack(getSupportFragmentManager(),
                CategoryFragment.newInstance(categoryName), R.id.lyt_container, CategoryFragment.class.getName());
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        super.onBackPressed();
    }
}