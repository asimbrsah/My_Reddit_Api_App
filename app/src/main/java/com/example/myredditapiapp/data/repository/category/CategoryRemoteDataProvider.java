package com.example.myredditapiapp.data.repository.category;

import com.example.myredditapiapp.data.model.response.CategoryResponseModel;
import com.example.myredditapiapp.data.source.remote.RedditApi;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

public class CategoryRemoteDataProvider implements CategorySourceProvider {

    private RedditApi redditApi;

    @Inject
    public CategoryRemoteDataProvider(RedditApi redditApi) {
        this.redditApi = redditApi;
    }

    @Override
    public Single<CategoryResponseModel> getCategoryData(String categoryName,
                                                         String limit,
                                                         String afterKey,
                                                         String count) {
        return redditApi.getCategoryData(categoryName, limit, afterKey, count);
    }
}