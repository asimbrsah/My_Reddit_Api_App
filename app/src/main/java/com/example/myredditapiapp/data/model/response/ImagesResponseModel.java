package com.example.myredditapiapp.data.model.response;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "preview_images_table")
public class ImagesResponseModel {

    @SerializedName("source")
    @Expose
    @Embedded
    private Source source;

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public static class Source {

        @SerializedName("url")
        @Expose
        @ColumnInfo(name = "image_url")
        private String imageUrl;
        @SerializedName("width")
        @Expose
        @ColumnInfo(name = "image_width")
        private Integer imageWidth;
        @SerializedName("height")
        @Expose
        @ColumnInfo(name = "image_height")
        private Integer imageHeight;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Integer getImageWidth() {
            return imageWidth;
        }

        public void setImageWidth(Integer imageWidth) {
            this.imageWidth = imageWidth;
        }

        public Integer getImageHeight() {
            return imageHeight;
        }

        public void setImageHeight(Integer imageHeight) {
            this.imageHeight = imageHeight;
        }
    }
}