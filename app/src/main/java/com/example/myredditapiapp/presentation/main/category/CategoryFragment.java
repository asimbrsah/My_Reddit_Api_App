package com.example.myredditapiapp.presentation.main.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myredditapiapp.Constants;
import com.example.myredditapiapp.R;
import com.example.myredditapiapp.base.BaseFragment;
import com.example.myredditapiapp.databinding.FragmentCategoryBinding;
import com.example.myredditapiapp.di.modules.GlideApp;
import com.example.myredditapiapp.utils.UserMessageUtil;
import com.google.android.exoplayer2.SimpleExoPlayer;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import javax.inject.Inject;

public class CategoryFragment extends BaseFragment {

    private FragmentCategoryBinding fragmentcategoryBinding;

    private CategoryAdapter categoryAdapter;
    private CategoryViewModel categoryViewModel;

    private String categoryName = "";
    private String defaultCategoryKey = "";
    private boolean loading = false;
    private int visibleItemCount, pastVisibleItemCount, totalItemCount;

    @Inject
    SimpleExoPlayer simpleExoPlayer;

    private boolean isScrolled = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryName = requireArguments().getString("category_name");
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentcategoryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_category, container, false);
        return fragmentcategoryBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpRecyclerView(fragmentcategoryBinding.recyclerCategory);

        fragmentcategoryBinding.swipeLyt.setOnRefreshListener(() -> {
            isScrolled = false;
            fragmentcategoryBinding.swipeLyt.setRefreshing(false);
            if (categoryName != null && !categoryName.isEmpty()) {
                categoryViewModel.loadCategoryData(categoryName,
                        Constants.RECYCLER_VIEW_DEFAULT_ITEM_LIMIT,
                        Constants.DEFAULT_CATEGORY_KEY_VALUE,
                        Constants.RECYCLER_VIEW_DEFAULT_ITEM_COUNT);
            }
        });
    }

    private void setUpRecyclerView(RecyclerView categoryRecycler) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        categoryRecycler.setLayoutManager(linearLayoutManager);
        categoryAdapter = new CategoryAdapter(getActivity(), new ArrayList<>(), GlideApp.with(requireContext()), simpleExoPlayer);
        categoryRecycler.setAdapter(categoryAdapter);

        categoryRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    loading = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = linearLayoutManager.getChildCount();
                    pastVisibleItemCount = linearLayoutManager.findFirstVisibleItemPosition();
                    totalItemCount = linearLayoutManager.getItemCount();

                    if (loading && (visibleItemCount + pastVisibleItemCount) >= totalItemCount) {
                        loading = false;
                        isScrolled = true;
                        fragmentcategoryBinding.lytCircularProgressBar.circularProgressBar.setVisibility(View.VISIBLE);
                        if (categoryName != null && !categoryName.isEmpty()) {
                            categoryViewModel.loadCategoryData(categoryName,
                                    Constants.RECYCLER_VIEW_DEFAULT_ITEM_LIMIT,
                                    defaultCategoryKey,
                                    Constants.RECYCLER_VIEW_DEFAULT_ITEM_COUNT);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        categoryViewModel = new ViewModelProvider(this, getViewModelProviderFactory()).get(CategoryViewModel.class);

        if (categoryName != null && !categoryName.isEmpty()) {
            categoryViewModel.loadCategoryData(categoryName,
                    Constants.RECYCLER_VIEW_DEFAULT_ITEM_LIMIT,
                    Constants.DEFAULT_CATEGORY_KEY_VALUE,
                    Constants.RECYCLER_VIEW_DEFAULT_ITEM_COUNT);
        }

        observeInternetAvailable();
        observeLoading();
        observeCategoryData();
        observeRemoteError();
    }

    private void observeInternetAvailable() {
        categoryViewModel.getInternetAvailable().observe(getViewLifecycleOwner(), internetAvailable -> {
            if (internetAvailable != null) {
                UserMessageUtil.showLongToastMessage(getContext(), getString(R.string.internet_connection_unavailable));
            }
        });
    }

    private void observeLoading() {
        categoryViewModel.getLoading().observe(getViewLifecycleOwner(), loading -> {
            if (loading != null) {
                if (loading) {
                    fragmentcategoryBinding.lytHorizontalProgressBar.horizontalProgressBar.setIndeterminate(true);
                    fragmentcategoryBinding.lytHorizontalProgressBar.horizontalProgressBar.setVisibility(View.VISIBLE);
                } else {
                    fragmentcategoryBinding.lytHorizontalProgressBar.horizontalProgressBar.setIndeterminate(false);
                    fragmentcategoryBinding.lytHorizontalProgressBar.horizontalProgressBar.setVisibility(View.GONE);
                    if (fragmentcategoryBinding.swipeLyt.isRefreshing()) {
                        fragmentcategoryBinding.swipeLyt.setRefreshing(false);
                    }
                    fragmentcategoryBinding.lytCircularProgressBar.circularProgressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void observeCategoryData() {
        categoryViewModel.getCategoryData().observe(getViewLifecycleOwner(), categoryModel -> {
            if (categoryModel != null) {
                defaultCategoryKey = categoryModel.getData().getCategoryDefaultKey() != null ?
                        categoryModel.getData().getCategoryDefaultKey() : "";
                fragmentcategoryBinding.recyclerCategory.setVisibility(View.VISIBLE);
                if (isScrolled) {
                    categoryAdapter.updateCategoryData(categoryModel.getData().getChildren());
                } else {
                    categoryAdapter.setCategoryData(categoryModel.getData().getChildren());
                }
            }
        });
    }

    private void observeRemoteError() {
        categoryViewModel.getRemoteError().observe(getViewLifecycleOwner(), remoteError -> {
            if (remoteError != null) {
                UserMessageUtil.showLongToastMessage(getContext(), remoteError);
            }
        });
    }
}