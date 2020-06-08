package com.example.myredditapiapp.data.source.local.pref;

import java.util.Set;

public interface PreferenceProvider {
    boolean getBoolean(String key);

    String getString(String key);

    int getInteger(String key);

    long getLong(String key);

    float getFloat(String key);

    Set<String> getStringSet(String key);

    void setBoolean(String key, boolean value);

    void setString(String key, String value);

    void setInt(String key, int value);

    void setLong(String key, long value);

    void setFloat(String key, float value);

    void setStringSet(String key, Set<String> value);

    void clearAll();
}