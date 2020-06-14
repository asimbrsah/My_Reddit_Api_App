package com.example.myredditapiapp.utils;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

public class NetworkUtil {
    private ConnectivityManager connectivityManager;

    @Inject
    public NetworkUtil(ConnectivityManager connectivityManager) {
        this.connectivityManager = connectivityManager;
    }

    public boolean isInternetAvailable() {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}