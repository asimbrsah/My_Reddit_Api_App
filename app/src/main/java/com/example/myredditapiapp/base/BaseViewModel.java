package com.example.myredditapiapp.base;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseViewModel extends ViewModel {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
        super.onCleared();
    }

    protected CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    protected void setCompositeDisposable(Disposable disposable) {
        this.compositeDisposable.add(disposable);
    }
}