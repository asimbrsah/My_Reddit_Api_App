package com.example.myredditapiapp.data.source.local.pref;

import android.content.Context;

import com.example.myredditapiapp.Constants;

import java.util.Set;

import javax.inject.Inject;

public class PreferenceManager implements PreferenceProvider {

    private final Context context;

    @Inject
    public PreferenceManager(Context context) {
        this.context = context;
    }

    @Override
    public boolean getBoolean(String key) {
        return context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .getBoolean(key, false);
    }

    @Override
    public String getString(String key) {
        return context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .getString(key, null);
    }

    @Override
    public int getInteger(String key) {
        return context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .getInt(key, 0);
    }

    @Override
    public long getLong(String key) {
        return context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .getLong(key, 0);
    }

    @Override
    public float getFloat(String key) {
        return context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .getFloat(key, 0);
    }

    @Override
    public Set<String> getStringSet(String key) {
        return context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .getStringSet(key, null);
    }

    @Override
    public void setBoolean(String key, boolean value) {
        context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(key, value)
                .apply();
    }

    @Override
    public void setString(String key, String value) {
        context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(key, value)
                .apply();
    }

    @Override
    public void setInt(String key, int value) {
        context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .edit()
                .putInt(key, value)
                .apply();
    }

    @Override
    public void setLong(String key, long value) {
        context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .edit()
                .putLong(key, value)
                .apply();
    }

    @Override
    public void setFloat(String key, float value) {
        context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .edit()
                .putFloat(key, value)
                .apply();
    }

    @Override
    public void setStringSet(String key, Set<String> value) {
        context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .edit()
                .putStringSet(key, value)
                .apply();
    }

    @Override
    public void clearAll() {
        context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE)
                .edit()
                .clear()
                .apply();
    }
}