package com.example.myredditapiapp.presentation.launcher.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.RequestManager;
import com.example.myredditapiapp.R;
import com.example.myredditapiapp.base.BaseFragment;
import com.example.myredditapiapp.presentation.launcher.LauncherViewModel;
import com.example.myredditapiapp.utils.UserMessageUtil;

import java.util.ArrayList;

import javax.inject.Inject;

public class CategoryFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView categoryRecycler;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;

    private String categoryName;
    private CategoryAdapter categoryAdapter;

    private LauncherViewModel launcherViewModel;
    private CategoryViewModel categoryViewModel;

    @Inject
    RequestManager requestManager;

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
        progressBar = view.findViewById(R.id.lyt_horizontal_progress);
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
        categoryRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        categoryAdapter = new CategoryAdapter(getActivity(), new ArrayList<>(), requestManager);
        categoryRecycler.setAdapter(categoryAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        launcherViewModel = new ViewModelProvider(requireActivity()).get(LauncherViewModel.class);
        categoryViewModel = new ViewModelProvider(this, getViewModelProviderFactory()).get(CategoryViewModel.class);

        if (categoryName != null) {
            categoryViewModel.loadCategoryData(categoryName);
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
                    progressBar.setIndeterminate(true);
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setIndeterminate(false);
                    progressBar.setVisibility(View.GONE);
                    if (swipeRefreshLayout.isRefreshing()) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
            }
        });
    }

    private void observeCategoryData() {
        categoryViewModel.getCategoryData().observe(getViewLifecycleOwner(), categoryModel -> {
            if (categoryModel != null) {
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
        categoryViewModel.loadCategoryData(categoryName);
    }
}