package com.example.myredditapiapp.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myredditapiapp.di.scope.ViewModelKey;
import com.example.myredditapiapp.factory.ViewModelProviderFactory;
import com.example.myredditapiapp.presentation.launcher.LauncherViewModel;
import com.example.myredditapiapp.presentation.launcher.category.CategoryViewModel;
import com.example.myredditapiapp.presentation.splash.SplashViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelProviderModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelProviderFactory(ViewModelProviderFactory viewModelFactory);

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel.class)
    public abstract ViewModel bindSplashViewModel(SplashViewModel splashViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LauncherViewModel.class)
    public abstract ViewModel bindLauncherViewModel(LauncherViewModel launcherViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel.class)
    public abstract ViewModel bindCategoryViewModel(CategoryViewModel categoryViewModel);
}