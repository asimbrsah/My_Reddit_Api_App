package com.example.myredditapiapp.di.modules;

import com.example.myredditapiapp.data.repository.category.CategoryLocalDataProvider;
import com.example.myredditapiapp.data.repository.category.CategoryRemoteDataProvider;
import com.example.myredditapiapp.data.source.local.db.dao.CategoryDataDao;
import com.example.myredditapiapp.data.source.remote.RedditApi;
import com.example.myredditapiapp.di.scope.Local;
import com.example.myredditapiapp.di.scope.Remote;
import com.example.myredditapiapp.data.repository.category.CategorySourceProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryProviderModule {

    @Singleton
    @Provides
    @Remote
    CategorySourceProvider provideCategoryRemoteDataProvider(RedditApi redditApi) {
        return new CategoryRemoteDataProvider(redditApi);
    }

    @Singleton
    @Provides
    @Local
    CategorySourceProvider provideCategoryLocalDataProvider() {
        return new CategoryLocalDataProvider();
    }
}