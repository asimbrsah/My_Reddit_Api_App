package com.example.myredditapiapp.data.repository.category;

import com.example.myredditapiapp.data.model.response.CategoryResponseModel;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class CategoryLocalDataProvider implements CategorySourceProvider {

    @Inject
    public CategoryLocalDataProvider() {
    }

    @Override
    public Single<CategoryResponseModel> getCategoryData(String categoryName) {
        return null;
    }
}