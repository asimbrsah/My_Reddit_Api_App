package com.example.myredditapiapp.presentation.launcher.category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myredditapiapp.base.BaseViewModel;
import com.example.myredditapiapp.data.model.response.CategoryResponseModel;
import com.example.myredditapiapp.data.repository.category.CategoryRepository;
import com.example.myredditapiapp.utils.NetworkUtil;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CategoryViewModel extends BaseViewModel {

    private final CategoryRepository categoryRepository;
    private final NetworkUtil networkUtil;

    private MutableLiveData<CategoryResponseModel> categoryData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private MutableLiveData<Object> internetAvailable = new MutableLiveData<>();
    private MutableLiveData<String> remoteError = new MutableLiveData<>();

    @Inject
    CategoryViewModel(CategoryRepository categoryRepository,
                      NetworkUtil networkUtil) {
        this.categoryRepository = categoryRepository;
        this.networkUtil = networkUtil;
    }

    void loadCategoryData(String categoryName) {
        if (networkUtil.isInternetAvailable()) {
            setCompositeDisposable(loadCategoryDataDisposable(categoryName));
        } else {
            internetAvailable.setValue(new Object());
        }
    }

    private Disposable loadCategoryDataDisposable(String categoryName) {
        return categoryRepository.getCategoryData(categoryName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> loading.setValue(true))
                .doAfterTerminate(() -> loading.setValue(false))
                .subscribe(categoryModel -> categoryData.setValue(categoryModel),
                        throwable -> remoteError.setValue("Something is broken, please try after some time !"));
    }

    LiveData<Object> getInternetAvailable() {
        return internetAvailable;
    }

    LiveData<Boolean> getLoading() {
        return loading;
    }

    LiveData<CategoryResponseModel> getCategoryData() {
        return categoryData;
    }

    LiveData<String> getRemoteError() {
        return remoteError;
    }
}