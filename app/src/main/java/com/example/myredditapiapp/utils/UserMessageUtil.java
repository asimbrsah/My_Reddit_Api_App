package com.example.myredditapiapp.utils;

import android.content.Context;
import android.widget.Toast;

public class UserMessageUtil {

    public static void showLongToastMessage(Context context, String message) {
        if (message != null) {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }

    public static void showShortToastMessage(Context context, String message) {
        if (message != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}