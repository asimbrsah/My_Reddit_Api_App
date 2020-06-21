package com.example.myredditapiapp.presentation.main.category;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.myredditapiapp.Navigator;
import com.example.myredditapiapp.data.model.response.ChildrenResponseModel;
import com.example.myredditapiapp.databinding.ItemCategoryBinding;
import com.example.myredditapiapp.utils.GlideUtil;
import com.example.myredditapiapp.utils.NumberCountUtil;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private final Context context;
    private final RequestManager requestManager;
    private final ItemCategoryBinding itemCategoryBinding;

    CategoryViewHolder(Context context,
                       RequestManager requestManager,
                       ItemCategoryBinding itemCategoryBinding) {

        super(itemCategoryBinding.getRoot());
        this.context = context;
        this.requestManager = requestManager;
        this.itemCategoryBinding = itemCategoryBinding;
    }

    void bind(ChildrenResponseModel childrenResponseModel) {

        String title = childrenResponseModel != null &&
                childrenResponseModel.getChildrenData() != null &&
                childrenResponseModel.getChildrenData().getTitle() != null &&
                !childrenResponseModel.getChildrenData().getTitle().isEmpty() ?
                childrenResponseModel.getChildrenData().getTitle() : "";

        itemCategoryBinding.tvHeader.setText(childrenResponseModel != null &&
                childrenResponseModel.getChildrenData() != null &&
                childrenResponseModel.getChildrenData().getHeader() != null &&
                !childrenResponseModel.getChildrenData().getHeader().isEmpty() ?
                childrenResponseModel.getChildrenData().getHeader() : "");

        itemCategoryBinding.tvDomain.setText(childrenResponseModel != null &&
                childrenResponseModel.getChildrenData() != null &&
                childrenResponseModel.getChildrenData().getDomain() != null &&
                !childrenResponseModel.getChildrenData().getDomain().isEmpty() ?
                childrenResponseModel.getChildrenData().getDomain() : "");

        itemCategoryBinding.tvTitle.setText(title);

        itemCategoryBinding.tvScore.setText(childrenResponseModel != null &&
                childrenResponseModel.getChildrenData() != null &&
                childrenResponseModel.getChildrenData().getScore() > 0 ?
                String.format("%s likes", NumberCountUtil.format(childrenResponseModel.getChildrenData().getScore())) : "");

        itemCategoryBinding.tvComment.setText(childrenResponseModel != null &&
                childrenResponseModel.getChildrenData() != null &&
                childrenResponseModel.getChildrenData().getNumComments() > 0 ?
                String.format("%s comments", NumberCountUtil.format(childrenResponseModel.getChildrenData().getNumComments())) : "");

        String detailUrl = childrenResponseModel != null &&
                childrenResponseModel.getChildrenData() != null &&
                childrenResponseModel.getChildrenData().getUrl() != null &&
                !childrenResponseModel.getChildrenData().getUrl().isEmpty() ?
                childrenResponseModel.getChildrenData().getUrl() : "";

        String imageUrl = childrenResponseModel != null &&
                childrenResponseModel.getChildrenData() != null &&
                childrenResponseModel.getChildrenData().getPreview() != null &&
                childrenResponseModel.getChildrenData().getPreview().getImages() != null &&
                childrenResponseModel.getChildrenData().getPreview().getImages().size() > 0 &&
                childrenResponseModel.getChildrenData().getPreview().getImages().get(0) != null &&
                childrenResponseModel.getChildrenData().getPreview().getImages().get(0).getSource() != null &&
                childrenResponseModel.getChildrenData().getPreview().getImages().get(0).getSource().getImageUrl() != null &&
                !childrenResponseModel.getChildrenData().getPreview().getImages().get(0).getSource().getImageUrl().isEmpty() ?
                childrenResponseModel.getChildrenData().getPreview().getImages().get(0).getSource().getImageUrl() : "";

        int imageHeight = childrenResponseModel != null &&
                childrenResponseModel.getChildrenData() != null &&
                childrenResponseModel.getChildrenData().getPreview() != null &&
                childrenResponseModel.getChildrenData().getPreview().getImages() != null &&
                childrenResponseModel.getChildrenData().getPreview().getImages().size() > 0 &&
                childrenResponseModel.getChildrenData().getPreview().getImages().get(0) != null &&
                childrenResponseModel.getChildrenData().getPreview().getImages().get(0).getSource() != null &&
                childrenResponseModel.getChildrenData().getPreview().getImages().get(0).getSource().getImageHeight() != null &&
                childrenResponseModel.getChildrenData().getPreview().getImages().get(0).getSource().getImageHeight() > 0 ?
                childrenResponseModel.getChildrenData().getPreview().getImages().get(0).getSource().getImageHeight() : 0;

        int imageWidth = childrenResponseModel != null &&
                childrenResponseModel.getChildrenData() != null &&
                childrenResponseModel.getChildrenData().getPreview() != null &&
                childrenResponseModel.getChildrenData().getPreview().getImages() != null &&
                childrenResponseModel.getChildrenData().getPreview().getImages().size() > 0 &&
                childrenResponseModel.getChildrenData().getPreview().getImages().get(0) != null &&
                childrenResponseModel.getChildrenData().getPreview().getImages().get(0).getSource() != null &&
                childrenResponseModel.getChildrenData().getPreview().getImages().get(0).getSource().getImageWidth() != null &&
                childrenResponseModel.getChildrenData().getPreview().getImages().get(0).getSource().getImageWidth() > 0 ?
                childrenResponseModel.getChildrenData().getPreview().getImages().get(0).getSource().getImageWidth() : 0;

        if (imageUrl != null && !imageUrl.isEmpty() && imageWidth > 0 && imageHeight > 0) {
            if (imageUrl.contains("amp;")) {
                imageUrl = imageUrl.replace("amp;", "");
            }
            GlideUtil.setUpImageWithPlaceHolder(requestManager, imageUrl, itemCategoryBinding.imgThumbnail);
            itemCategoryBinding.imgThumbnail.setVisibility(View.VISIBLE);
        } else {
            itemCategoryBinding.imgThumbnail.setVisibility(View.GONE);
        }

        String videoUrl = childrenResponseModel != null &&
                childrenResponseModel.getChildrenData() != null &&
                childrenResponseModel.getChildrenData().getMedia() != null &&
                childrenResponseModel.getChildrenData().getMedia().getRedditVideo() != null &&
                childrenResponseModel.getChildrenData().getMedia().getRedditVideo().getVideoUrl() != null &&
                !childrenResponseModel.getChildrenData().getMedia().getRedditVideo().getVideoUrl().isEmpty() ?
                childrenResponseModel.getChildrenData().getMedia().getRedditVideo().getVideoUrl() : "";

        String finalImageUrl = imageUrl;

        itemCategoryBinding.getRoot().setOnClickListener(v -> {
            if (detailUrl != null && !detailUrl.isEmpty()) {
                Navigator.openCategoryDetailScreen(context, title, detailUrl, false, false);
            }
        });

        itemCategoryBinding.imgThumbnail.setOnClickListener(v -> {
            if (videoUrl != null && !videoUrl.isEmpty()) {
                Navigator.openCategoryDetailScreen(context, title, videoUrl, true, false);
            } else if (finalImageUrl != null && !finalImageUrl.isEmpty()) {
                Navigator.openCategoryDetailScreen(context, title, finalImageUrl, false, true);
            } else {
                Navigator.openCategoryDetailScreen(context, title, detailUrl, false, false);
            }
        });
    }
}