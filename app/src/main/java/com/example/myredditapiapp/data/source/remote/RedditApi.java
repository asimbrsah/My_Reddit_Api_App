package com.example.myredditapiapp.data.source.remote;

import com.example.myredditapiapp.data.model.response.CategoryResponseModel;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RedditApi {

    @GET("{category_name}/.json?limit=10")
    Single<CategoryResponseModel> getCategoryData(@Path("category_name") String categoryName);
}