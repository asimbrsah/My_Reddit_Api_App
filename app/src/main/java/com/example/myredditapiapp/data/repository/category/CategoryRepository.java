package com.example.myredditapiapp.data.repository.category;

import com.example.myredditapiapp.data.model.response.CategoryResponseModel;
import com.example.myredditapiapp.di.scope.Local;
import com.example.myredditapiapp.di.scope.Remote;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class CategoryRepository implements CategorySourceProvider {

    private CategorySourceProvider categoryRemoteDataProvider;
    private CategorySourceProvider categoryLocalDataProvider;

    @Inject
    public CategoryRepository(@Remote CategorySourceProvider categoryRemoteDataProvider,
                              @Local CategorySourceProvider categoryLocalDataProvider) {
        this.categoryRemoteDataProvider = categoryRemoteDataProvider;
        this.categoryLocalDataProvider = categoryLocalDataProvider;
    }

    @Override
    public Single<CategoryResponseModel> getCategoryData(String categoryName,
                                                         String limit,
                                                         String afterKey,
                                                         String count) {
        return categoryRemoteDataProvider.getCategoryData(categoryName, limit, afterKey, count);
    }
}