package com.example.myredditapiapp;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myredditapiapp.presentation.launcher.LauncherActivity;

public class Navigator {

    public static void openLauncherScreen(Context context) {
        Intent intent = new Intent(context, LauncherActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void commitNowReplaceFragment(@NonNull FragmentManager fragmentManager,
                                                @NonNull Fragment fragment, int id) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id, fragment);
        fragmentTransaction.commitNow();
    }

    public static void commitAddFragmentWithBackStack(@NonNull FragmentManager fragmentManager,
                                                      @NonNull Fragment fragment, int id, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id, fragment);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }
}