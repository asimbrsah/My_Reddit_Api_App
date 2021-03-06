package com.example.myredditapiapp.utils;

import com.bumptech.glide.RequestManager;
import com.example.myredditapiapp.R;
import com.example.myredditapiapp.widgets.ResizableImageView;

public class GlideUtil {

    public static void setUpImageWithPlaceHolder(RequestManager glide,
                                                 String url,
                                                 ResizableImageView resizableImageView) {
        glide
                .load(url)
                .placeholder(R.drawable.ic_white_background_100dp)
                .error(R.drawable.ic_logo)
                .into(resizableImageView);
    }

    public static void setUpGIFWithPlaceHolder(RequestManager glide,
                                               String url,
                                               ResizableImageView resizableImageView) {
        glide
                .asGif()
                .load(url)
                .placeholder(R.drawable.ic_white_background_100dp)
                .error(R.drawable.ic_logo)
                .into(resizableImageView);
    }
}