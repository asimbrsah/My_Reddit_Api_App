package com.example.myredditapiapp.data.repository.category;

import com.example.myredditapiapp.data.model.response.CategoryResponseModel;

import io.reactivex.Single;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CategorySourceProvider {
    Single<CategoryResponseModel> getCategoryData(String categoryName,
                                                  String limit,
                                                  String afterKey,
                                                  String count);
}