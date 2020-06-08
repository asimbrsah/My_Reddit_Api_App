package com.example.myredditapiapp.di.modules;

import android.app.Application;

import com.example.myredditapiapp.BuildConfig;
import com.example.myredditapiapp.data.source.remote.RedditApi;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RestApiProviderModule {

    @Singleton
    @Provides
    Cache provideCache(Application application) {
        long cacheSize = 10 * 1024 * 1024;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            // apply interceptor logic on top of the network layer (ex: setting token, localization etc...)
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.level(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(logging);
        }
        okHttpClientBuilder.cache(cache);
        return okHttpClientBuilder.build();
    }

    @Singleton
    @Provides
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        //serialization the field name on the top of network layer while retrieving data
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);
        return gsonBuilder.create();
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    static RedditApi provideRestApi(Retrofit retrofit) {
        return retrofit.create(RedditApi.class);
    }
}