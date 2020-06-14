package com.example.myredditapiapp.di.modules;

import android.app.Application;

import androidx.room.Room;

import com.example.myredditapiapp.Constants;
import com.example.myredditapiapp.data.source.local.db.AppDatabase;
import com.example.myredditapiapp.data.source.local.db.dao.CategoryDataDao;
import com.example.myredditapiapp.data.source.local.pref.PreferenceManager;
import com.example.myredditapiapp.data.source.local.pref.PreferenceProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalDataProviderModule {

    // Room Database
    @Singleton
    @Provides
    AppDatabase provideAppDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, Constants.DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    // Shared Preference
    @Singleton
    @Provides
    PreferenceProvider providePreferenceProvider(Application application) {
        return new PreferenceManager(application);
    }

    @Singleton
    @Provides
    CategoryDataDao provideCategoryDataDao(AppDatabase appDatabase) {
        return appDatabase.categoryDataDao();
    }
}