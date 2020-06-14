package com.example.myredditapiapp.presentation.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myredditapiapp.base.BaseViewModel;

import javax.inject.Inject;

public class MainViewModel extends BaseViewModel {

    private MutableLiveData<String> transactionMutableLiveData = new MutableLiveData<>();

    @Inject
    MainViewModel() {

    }

    void setTransaction(String categoryName) {
        transactionMutableLiveData.setValue(categoryName);
    }

    LiveData<String> getTransactionMutableLiveData() {
        return transactionMutableLiveData;
    }
}