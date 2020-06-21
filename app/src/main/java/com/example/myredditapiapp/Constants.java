package com.example.myredditapiapp;

public class Constants {

    private static final String APP_ID = BuildConfig.APPLICATION_ID;

    public static final String SHARED_PREF_NAME = APP_ID + BuildConfig.SHARED_PREF_NAME;
    public static final String DATABASE_NAME = APP_ID + BuildConfig.DATABASE_NAME;

    public static final boolean DEBUG = BuildConfig.DEBUG;

    // Api related
    public static final String BASE_URL = BuildConfig.BASE_URL;
    public static final String DEFAULT_CATEGORY_KEY_VALUE = BuildConfig.DEFAULT_CATEGORY_KEY_VALUE;
    public static final String RECYCLER_VIEW_DEFAULT_ITEM_COUNT = BuildConfig.RECYCLER_VIEW_DEFAULT_ITEM_COUNT;
    public static final String RECYCLER_VIEW_DEFAULT_ITEM_LIMIT = BuildConfig.RECYCLER_VIEW_DEFAULT_ITEM_LIMIT;

    // Extras
    public static final String EXTRA_CATEGORY_TITLE = APP_ID + ".extra_category_title";
    public static final String EXTRA_CATEGORY_DETAIL_URL = APP_ID + ".extra_category_detail_url";
    public static final String EXTRA_CATEGORY_IS_VIDEO = APP_ID + ".extra_category_category_is_video";
    public static final String EXTRA_CATEGORY_IS_IMAGE = APP_ID + ".extra_category_category_is_image";

}