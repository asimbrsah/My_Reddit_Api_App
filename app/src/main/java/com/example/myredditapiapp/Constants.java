package com.example.myredditapiapp;

public class Constants {

    private static final String APP_ID = BuildConfig.APPLICATION_ID;

    public static final String SHARED_PREF_NAME = APP_ID + BuildConfig.SHARED_PREF_NAME;
    public static final String DATABASE_NAME = APP_ID + BuildConfig.DATABASE_NAME;

    public static final boolean DEBUG = BuildConfig.DEBUG;

    public static final String DEFAULT_CATEGORY_KEY_VALUE = BuildConfig.DEFAULT_CATEGORY_KEY_VALUE;
    public static final String RECYCLER_VIEW_DEFAULT_ITEM_COUNT = BuildConfig.RECYCLER_VIEW_DEFAULT_ITEM_COUNT;
    public static final String RECYCLER_VIEW_DEFAULT_ITEM_LIMIT = BuildConfig.RECYCLER_VIEW_DEFAULT_ITEM_LIMIT;

    public static final String BASE_URL = BuildConfig.BASE_URL;
    public static final String ALL_CATEGORY_END_POINT_URL = BuildConfig.ALL_CATEGORY_END_POINT_URL;
    public static final String POPULAR_CATEGORY_END_POINT_URL = BuildConfig.POPULAR_CATEGORY_END_POINT_URL;
    public static final String SCIENCE_CATEGORY_END_POINT_URL = BuildConfig.SCIENCE_CATEGORY_END_POINT_URL;
    public static final String ART_CATEGORY_END_POINT_URL = BuildConfig.ART_CATEGORY_END_POINT_URL;
}