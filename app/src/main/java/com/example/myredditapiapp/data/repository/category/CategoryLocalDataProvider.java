package com.example.myredditapiapp.data.repository.category;

import com.example.myredditapiapp.data.model.response.CategoryResponseModel;
import com.example.myredditapiapp.data.source.local.db.dao.CategoryDataDao;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

public class CategoryLocalDataProvider implements CategorySourceProvider {

    private CategoryDataDao categoryDataDao;

    @Inject
    public CategoryLocalDataProvider(CategoryDataDao categoryDataDao) {
        this.categoryDataDao = categoryDataDao;
    }

    @Override
    public Single<CategoryResponseModel> getCategoryData(String categoryName,
                                                         String limit,
                                                         String afterKey,
                                                         String count) {
        return null;
    }
}