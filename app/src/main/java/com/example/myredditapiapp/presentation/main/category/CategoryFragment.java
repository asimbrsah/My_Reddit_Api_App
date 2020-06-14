package com.example.myredditapiapp.presentation.main.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.myredditapiapp.BuildConfig;
import com.example.myredditapiapp.R;
import com.example.myredditapiapp.base.BaseFragment;
import com.example.myredditapiapp.di.modules.GlideApp;
import com.example.myredditapiapp.presentation.main.MainViewModel;
import com.example.myredditapiapp.utils.UserMessageUtil;
import com.google.android.exoplayer2.SimpleExoPlayer;

import java.util.ArrayList;
import java.util.Objects;

import javax.inject.Inject;

public class CategoryFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView categoryRecycler;
    private ProgressBar horizontalProgressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar circularProgressBar;

    private String categoryName;
    private CategoryAdapter categoryAdapter;

    private MainViewModel mainViewModel;
    private CategoryViewModel categoryViewModel;

    private String defaultCategoryKey = "";
    private boolean loading = false;
    private int visibleItemCount, pastVisibleItemCount, totalItemCount;

    @Inject
    SimpleExoPlayer simpleExoPlayer;

    public static CategoryFragment newInstance(String categoryName) {
        CategoryFragment categoryFragment = new CategoryFragment();
        Bundle args = new Bundle();
        args.putString("category_name", categoryName);
        categoryFragment.setArguments(args);
        return categoryFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            categoryName = getArguments().getString("category_name");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        categoryRecycler = view.findViewById(R.id.recycler_category);
        horizontalProgressBar = view.findViewById(R.id.include_horizontal_progress_bar);
        circularProgressBar = view.findViewById(R.id.include_circular_progress_bar);
        swipeRefreshLayout = view.findViewById(R.id.swipe_lyt);
        swipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecyclerView(categoryRecycler);
    }

    private void setUpRecyclerView(RecyclerView categoryRecycler) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        categoryRecycler.setLayoutManager(linearLayoutManager);
        categoryAdapter = new CategoryAdapter(getActivity(), new ArrayList<>(), GlideApp.with(Objects.requireNonNull(getContext())), simpleExoPlayer);
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
                        circularProgressBar.setVisibility(View.VISIBLE);
                        categoryViewModel.loadCategoryData(categoryName,
                                BuildConfig.RECYCLER_VIEW_DEFAULT_ITEM_LIMIT,
                                defaultCategoryKey,
                                BuildConfig.RECYCLER_VIEW_DEFAULT_ITEM_COUNT);
                    }
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        categoryViewModel = new ViewModelProvider(this, getViewModelProviderFactory()).get(CategoryViewModel.class);

        if (categoryName != null) {
            categoryViewModel.loadCategoryData(categoryName,
                    BuildConfig.RECYCLER_VIEW_DEFAULT_ITEM_LIMIT,
                    BuildConfig.DEFAULT_CATEGORY_KEY_VALUE,
                    BuildConfig.RECYCLER_VIEW_DEFAULT_ITEM_COUNT);
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
                    horizontalProgressBar.setIndeterminate(true);
                    horizontalProgressBar.setVisibility(View.VISIBLE);
                } else {
                    horizontalProgressBar.setIndeterminate(false);
                    horizontalProgressBar.setVisibility(View.GONE);
                    if (swipeRefreshLayout.isRefreshing()) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    circularProgressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void observeCategoryData() {
        categoryViewModel.getCategoryData().observe(getViewLifecycleOwner(), categoryModel -> {
            if (categoryModel != null) {
                defaultCategoryKey = categoryModel.getData().getCategoryDefaultKey() != null ?
                        categoryModel.getData().getCategoryDefaultKey() : "";
                categoryAdapter.setCategoryData(categoryModel.getData().getChildren());
                categoryRecycler.setVisibility(View.VISIBLE);
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

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        categoryViewModel.loadCategoryData(categoryName,
                BuildConfig.RECYCLER_VIEW_DEFAULT_ITEM_LIMIT,
                BuildConfig.DEFAULT_CATEGORY_KEY_VALUE,
                BuildConfig.RECYCLER_VIEW_DEFAULT_ITEM_COUNT);
    }
}