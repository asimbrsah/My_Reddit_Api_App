package com.example.myredditapiapp.di.modules;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

import com.example.myredditapiapp.utils.NetworkUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilProviderModule {

    @Singleton
    @Provides
    ConnectivityManager provideConnectivityManager(Application application) {
        return (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Singleton
    @Provides
    NetworkUtil provideNetworkUtil(ConnectivityManager connectivityManager) {
        return new NetworkUtil(connectivityManager);
    }
}