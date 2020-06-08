package com.example.myredditapiapp.presentation.launcher.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.myredditapiapp.R;
import com.example.myredditapiapp.data.model.response.CategoryResponseModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context context;
    private List<CategoryResponseModel.Data.Children> childrenList;
    private final RequestManager requestManager;

    CategoryAdapter(Context context, List<CategoryResponseModel.Data.Children> childrenList, RequestManager requestManager) {
        this.context = context;
        this.childrenList = childrenList;
        this.requestManager = requestManager;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bind(childrenList.get(position));
    }

    @Override
    public int getItemCount() {
        return childrenList.size();
    }

    void setCategoryData(List<CategoryResponseModel.Data.Children> childrenList) {
        this.childrenList = childrenList;
        notifyDataSetChanged();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout mainLayout;
        private ImageView imgBrand;
        private TextView tvTitle;
        private TextView tvSubReddit;
        private TextView tvDomain;
        private TextView tvScore;
        private TextView tvComment;
        private ImageView imgThumbnail;

        private CategoryViewHolder(View itemView) {
            super(itemView);
            mainLayout = itemView.findViewById(R.id.cat_lyt);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvSubReddit = itemView.findViewById(R.id.tv_sub_reddit);
            tvDomain = itemView.findViewById(R.id.tv_domain);
            tvScore = itemView.findViewById(R.id.tv_score);
            tvComment = itemView.findViewById(R.id.tv_comment);
            imgThumbnail = itemView.findViewById(R.id.img_thumbnail);
        }

        void bind(CategoryResponseModel.Data.Children children) {
            if (children.getChildrenData().getTitle() != null) {
                tvTitle.setText(children.getChildrenData().getTitle());
            }
            if (children.getChildrenData().getSubReddit() != null) {
                tvSubReddit.setText(children.getChildrenData().getSubReddit());
            }
            if (children.getChildrenData().getDomain() != null) {
                tvDomain.setText(children.getChildrenData().getDomain());
            }
            if (children.getChildrenData().getScore() > 0) {
                tvScore.setText(String.valueOf(children.getChildrenData().getScore()));
            }
            if (children.getChildrenData().getNumComments() > 0) {
                tvComment.setText(String.format("%s comments", children.getChildrenData().getNumComments()));
            }
            if (children.getChildrenData().getUrl() != null && children.getChildrenData().getUrl().contains("http")) {
                requestManager.load(children.getChildrenData().getUrl()).into(imgThumbnail);
                imgThumbnail.setVisibility(View.VISIBLE);
            } else {
                imgThumbnail.setVisibility(View.GONE);
            }
            if (children.getChildrenData().getUrl() != null && children.getChildrenData().getUrl().contains("http")) {

            }
        }
    }
}