package com.example.myredditapiapp.presentation.main.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.myredditapiapp.R;
import com.example.myredditapiapp.data.model.response.ChildrenResponseModel;
import com.google.android.exoplayer2.SimpleExoPlayer;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private List<ChildrenResponseModel> childrenResponseModelList;
    private final RequestManager requestManager;
    private SimpleExoPlayer simpleExoPlayer;

    CategoryAdapter(Context context,
                    List<ChildrenResponseModel> childrenResponseModelList,
                    RequestManager requestManager,
                    SimpleExoPlayer simpleExoPlayer) {
        this.childrenResponseModelList = childrenResponseModelList;
        this.requestManager = requestManager;
        this.simpleExoPlayer = simpleExoPlayer;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(v, requestManager, simpleExoPlayer);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bind(childrenResponseModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return childrenResponseModelList.size();
    }

    void updateCategoryData(List<ChildrenResponseModel> childrenResponseModelList) {
        this.childrenResponseModelList.addAll(childrenResponseModelList);
        notifyDataSetChanged();
    }

    void setCategoryData(List<ChildrenResponseModel> childrenResponseModelList) {
        if (this.childrenResponseModelList.size() > 0) {
            this.childrenResponseModelList.clear();
        }
        this.childrenResponseModelList.addAll(childrenResponseModelList);
        notifyDataSetChanged();
    }
}