package com.example.myredditapiapp.base;

import com.example.myredditapiapp.factory.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment extends DaggerFragment {

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    protected ViewModelProviderFactory getViewModelProviderFactory() {
        return viewModelProviderFactory;
    }
}