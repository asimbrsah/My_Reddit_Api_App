package com.example.myredditapiapp.presentation.main.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.myredditapiapp.R;
import com.example.myredditapiapp.data.model.response.ChildrenResponseModel;
import com.example.myredditapiapp.databinding.ItemCategoryBinding;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private final Context context;
    private final RequestManager requestManager;
    private final List<ChildrenResponseModel> childrenResponseModelList;

    CategoryAdapter(Context context,
                    List<ChildrenResponseModel> childrenResponseModelList,
                    RequestManager requestManager) {
        this.context = context;
        this.requestManager = requestManager;
        this.childrenResponseModelList = childrenResponseModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCategoryBinding itemCategoryBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_category, parent, false);
        return new CategoryViewHolder(context, requestManager, itemCategoryBinding);
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
        if (this.childrenResponseModelList != null && this.childrenResponseModelList.size() > 0) {
            this.childrenResponseModelList.addAll(childrenResponseModelList);
            notifyDataSetChanged();
        }
    }

    void setCategoryData(List<ChildrenResponseModel> childrenResponseModelList) {
        if (this.childrenResponseModelList != null) {
            if (this.childrenResponseModelList.size() > 0) {
                this.childrenResponseModelList.clear();
            }
            this.childrenResponseModelList.addAll(childrenResponseModelList);
            notifyDataSetChanged();
        }
    }
}