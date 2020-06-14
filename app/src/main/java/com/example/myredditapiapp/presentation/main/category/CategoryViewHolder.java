package com.example.myredditapiapp.presentation.main.category;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.myredditapiapp.R;
import com.example.myredditapiapp.data.model.response.ChildrenResponseModel;
import com.example.myredditapiapp.utils.GlideUtil;
import com.example.myredditapiapp.utils.NumberCountUtil;
import com.example.myredditapiapp.widgets.ResizableImageView;
import com.google.android.exoplayer2.SimpleExoPlayer;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private TextView tvHeader;
    private TextView tvDomain;
    private TextView tvTitle;
    private TextView tvScore;
    private TextView tvComment;
    private FrameLayout mediaContainer;
    private ResizableImageView imgThumbnail;
    private ImageView volumeControl;
    private View videoProgressView;

    private RequestManager requestManager;
    private SimpleExoPlayer simpleExoPlayer;

    CategoryViewHolder(View itemView,
                       RequestManager requestManager,
                       SimpleExoPlayer simpleExoPlayer) {

        super(itemView);
        this.requestManager = requestManager;

        tvHeader = itemView.findViewById(R.id.tv_header);
        tvDomain = itemView.findViewById(R.id.tv_domain);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvScore = itemView.findViewById(R.id.tv_score);
        tvComment = itemView.findViewById(R.id.tv_comment);
        mediaContainer = itemView.findViewById(R.id.media_container);
        imgThumbnail = itemView.findViewById(R.id.img_thumbnail);
        volumeControl = itemView.findViewById(R.id.volume_control);
        videoProgressView = itemView.findViewById(R.id.include_circular_progress_bar);
        this.simpleExoPlayer = simpleExoPlayer;
    }

    void bind(ChildrenResponseModel childrenResponseModel) {

        tvHeader.setText(childrenResponseModel != null &&
                childrenResponseModel.getChildrenData() != null &&
                childrenResponseModel.getChildrenData().getHeader() != null &&
                !childrenResponseModel.getChildrenData().getHeader().isEmpty() ?
                childrenResponseModel.getChildrenData().getHeader() : "");

        tvDomain.setText(childrenResponseModel != null &&
                childrenResponseModel.getChildrenData() != null &&
                childrenResponseModel.getChildrenData().getDomain() != null &&
                !childrenResponseModel.getChildrenData().getDomain().isEmpty() ?
                childrenResponseModel.getChildrenData().getDomain() : "");

        tvTitle.setText(childrenResponseModel != null &&
                childrenResponseModel.getChildrenData() != null &&
                childrenResponseModel.getChildrenData().getTitle() != null &&
                !childrenResponseModel.getChildrenData().getTitle().isEmpty() ?
                childrenResponseModel.getChildrenData().getTitle() : "");

        tvScore.setText(childrenResponseModel != null &&
                childrenResponseModel.getChildrenData() != null &&
                childrenResponseModel.getChildrenData().getScore() > 0 ?
                NumberCountUtil.format(childrenResponseModel.getChildrenData().getScore()) : "");

        tvComment.setText(childrenResponseModel != null &&
                childrenResponseModel.getChildrenData() != null &&
                childrenResponseModel.getChildrenData().getNumComments() > 0 ?
                String.format("%s comments", NumberCountUtil.format(childrenResponseModel.getChildrenData().getNumComments())) : "");

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
            GlideUtil.setUpImageWithRequestOptions(requestManager, imageUrl, imgThumbnail);
            imgThumbnail.setVisibility(View.VISIBLE);
        } else {
            imgThumbnail.setVisibility(View.GONE);
        }

        boolean isVideoLinkAvailable = childrenResponseModel != null &&
                childrenResponseModel.getChildrenData() != null &&
                childrenResponseModel.getChildrenData().getVideo() != null ?
                childrenResponseModel.getChildrenData().getVideo() : false;

        if (isVideoLinkAvailable) {

            String videoUrl = childrenResponseModel.getChildrenData() != null &&
                    childrenResponseModel.getChildrenData().getMedia() != null &&
                    childrenResponseModel.getChildrenData().getMedia().getRedditVideo().getVideoUrl() != null &&
                    !childrenResponseModel.getChildrenData().getMedia().getRedditVideo().getVideoUrl().isEmpty() ?
                    childrenResponseModel.getChildrenData().getMedia().getRedditVideo().getVideoUrl() : "";

            boolean isGif = childrenResponseModel.getChildrenData() != null &&
                    childrenResponseModel.getChildrenData().getMedia() != null &&
                    childrenResponseModel.getChildrenData().getMedia().getRedditVideo().getGif() != null ?
                    childrenResponseModel.getChildrenData().getMedia().getRedditVideo().getGif() : false;

            if (videoUrl != null && !videoUrl.isEmpty()) {

            }
        }
    }
}