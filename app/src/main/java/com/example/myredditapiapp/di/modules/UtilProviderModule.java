package com.example.myredditapiapp.di.modules;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.myredditapiapp.R;
import com.example.myredditapiapp.utils.NetworkUtil;
import com.google.android.exoplayer2.SimpleExoPlayer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UtilProviderModule {

    @Singleton
    @Provides
    static RequestOptions provideRequestOptions() {
        return RequestOptions
                .placeholderOf(R.drawable.ic_refresh_24dp)
                .error(R.drawable.ic_logo);
    }

    @Singleton
    @Provides
    static RequestManager provideRequestManager(Application application, RequestOptions requestOptions) {
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

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

    @Singleton
    @Provides
    SimpleExoPlayer provideSimpleExoPlayer(Application application) {
        return new SimpleExoPlayer.Builder(application).build();
    }
}