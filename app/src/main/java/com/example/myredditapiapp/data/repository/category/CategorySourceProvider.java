package com.example.myredditapiapp.data.repository.category;

import com.example.myredditapiapp.data.model.response.CategoryResponseModel;

import io.reactivex.Single;

public interface CategorySourceProvider {
    Single<CategoryResponseModel> getCategoryData(String categoryName);
}