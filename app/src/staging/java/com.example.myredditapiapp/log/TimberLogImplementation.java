package com.example.myredditapiapp.log;

import android.util.Log;

import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

public class TimberLogImplementation {

    public static void init() {

        // A tree which logs important information for crash reporting.
        Timber.plant(new Timber.Tree() {
            @Override
            protected boolean isLoggable(@Nullable String tag, int priority) {
                // Don't log VERBOSE, DEBUG and INFO
                // Log only ERROR, WARN and WTF
                return priority != Log.VERBOSE && priority != Log.DEBUG && priority != Log.INFO;
            }

            @Override
            protected void log(int priority, @Nullable String tag, @NotNull String message, @Nullable Throwable t) {
                if (isLoggable(tag, priority)) {
                    // log your crash to your favourite
                    // Sending crash report to Firebase CrashAnalytics

                    // FirebaseCrash.report(message);
                    // FirebaseCrash.report(new Exception(message));
                }
            }
        });
    }
}