package com.example.myredditapiapp.presentation.launcher;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myredditapiapp.base.BaseViewModel;

import javax.inject.Inject;

public class LauncherViewModel extends BaseViewModel {

    private MutableLiveData<String> transactionMutableLiveData = new MutableLiveData<>();

    @Inject
    LauncherViewModel() {

    }

    void setTransaction(String categoryName) {
        transactionMutableLiveData.setValue(categoryName);
    }

    LiveData<String> getTransactionMutableLiveData() {
        return transactionMutableLiveData;
    }
}