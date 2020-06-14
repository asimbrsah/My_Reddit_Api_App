package com.example.myredditapiapp.data.source.remote;

import com.example.myredditapiapp.data.model.response.CategoryResponseModel;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RedditApi {

    @GET("{category_name}/.json")
    Single<CategoryResponseModel> getCategoryData(@Path("category_name") String categoryName,
                                                  @Query("limit") String limit,
                                                  @Query("after") String afterKey,
                                                  @Query("count") String count);
}