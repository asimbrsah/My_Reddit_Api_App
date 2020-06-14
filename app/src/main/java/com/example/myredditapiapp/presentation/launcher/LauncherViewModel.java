package com.example.myredditapiapp.presentation.launcher;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myredditapiapp.base.BaseViewModel;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LauncherViewModel extends BaseViewModel {

    private MutableLiveData<Boolean> launchApplication = new MutableLiveData<>();

    @Inject
    LauncherViewModel() {

    }

    void launchApplication() {
        setCompositeDisposable(Observable.timer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> launchApplication.setValue(true)));
    }

    LiveData<Boolean> getLaunchApplication() {
        return launchApplication;
    }
}