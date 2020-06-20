package com.example.myredditapiapp;

import android.content.Context;
import android.content.Intent;

import com.example.myredditapiapp.presentation.main.MainActivity;

public class Navigator {

    public static void openLauncherScreen(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }
}