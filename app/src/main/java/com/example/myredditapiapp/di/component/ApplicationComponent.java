package com.example.myredditapiapp.di.component;

import android.app.Application;

import com.example.myredditapiapp.BaseApplication;
import com.example.myredditapiapp.di.modules.ActivityProviderModule;
import com.example.myredditapiapp.di.modules.LocalDataProviderModule;
import com.example.myredditapiapp.di.modules.RepositoryProviderModule;
import com.example.myredditapiapp.di.modules.RestApiProviderModule;
import com.example.myredditapiapp.di.modules.UtilProviderModule;
import com.example.myredditapiapp.di.modules.ViewModelProviderModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                ActivityProviderModule.class,
                ViewModelProviderModule.class,
                RestApiProviderModule.class,
                LocalDataProviderModule.class,
                RepositoryProviderModule.class,
                UtilProviderModule.class}
)
public interface ApplicationComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }
}