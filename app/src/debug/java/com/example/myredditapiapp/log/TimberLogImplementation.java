package com.example.myredditapiapp.log;

import org.jetbrains.annotations.NotNull;

import timber.log.Timber;

public class TimberLogImplementation {

    public static void init() {

        Timber.plant(new Timber.DebugTree() {
            @NotNull
            @Override
            protected String createStackElementTag(@NotNull StackTraceElement element) {
                return String.format("C: %s: %s %s",
                        element.getLineNumber(),
                        element.getMethodName(),
                        super.createStackElementTag(element));
            }
        });
    }
}