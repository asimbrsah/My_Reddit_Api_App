package com.example.myredditapiapp;

import android.content.Context;
import android.content.Intent;

import com.example.myredditapiapp.presentation.main.MainActivity;
import com.example.myredditapiapp.presentation.main.category.detail.CategoryDetailActivity;

public class Navigator {

    public static void openLauncherScreen(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void openCategoryDetailScreen(Context context,
                                                String title,
                                                String url,
                                                boolean isVideo,
                                                boolean isImage) {
        Intent intent = new Intent(context, CategoryDetailActivity.class);
        intent.putExtra(Constants.EXTRA_CATEGORY_TITLE, title);
        intent.putExtra(Constants.EXTRA_CATEGORY_DETAIL_URL, url);
        intent.putExtra(Constants.EXTRA_CATEGORY_IS_VIDEO, isVideo);
        intent.putExtra(Constants.EXTRA_CATEGORY_IS_IMAGE, isImage);
        context.startActivity(intent);
    }
}