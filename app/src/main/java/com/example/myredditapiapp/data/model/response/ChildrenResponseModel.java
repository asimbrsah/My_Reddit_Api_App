package com.example.myredditapiapp.data.model.response;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "children_data_table")
public class ChildrenResponseModel {

    @SerializedName("data")
    @Expose
    @Embedded
    private ChildrenData childrenData;

    public ChildrenData getChildrenData() {
        return childrenData;
    }

    public void setChildrenData(ChildrenData childrenData) {
        this.childrenData = childrenData;
    }

    public static class ChildrenData {

        @SerializedName("subreddit")
        @Expose
        @ColumnInfo(name = "header")
        private String header;
        @SerializedName("domain")
        @Expose
        @ColumnInfo(name = "domain")
        private String domain;
        @SerializedName("title")
        @Expose
        @ColumnInfo(name = "title")
        private String title;
        @SerializedName("score")
        @Expose
        @ColumnInfo(name = "score")
        private Long score;
        @SerializedName("num_comments")
        @Expose
        @ColumnInfo(name = "num_comments")
        private Long numComments;

        @SerializedName("preview")
        @Expose
        @Embedded
        private Preview preview;

        @SerializedName("is_video")
        @Expose
        @ColumnInfo(name = "is_video")
        private Boolean isVideo;

        @SerializedName("media")
        @Expose
        @Embedded
        private Media media;

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Long getScore() {
            return score;
        }

        public void setScore(Long score) {
            this.score = score;
        }

        public Long getNumComments() {
            return numComments;
        }

        public void setNumComments(Long numComments) {
            this.numComments = numComments;
        }

        public Preview getPreview() {
            return preview;
        }

        public void setPreview(Preview preview) {
            this.preview = preview;
        }

        public Boolean getVideo() {
            return isVideo;
        }

        public void setVideo(Boolean video) {
            isVideo = video;
        }

        public Media getMedia() {
            return media;
        }

        public void setMedia(Media media) {
            this.media = media;
        }

        public static class Preview {

            @SerializedName("images")
            @Expose
            @Ignore
            private List<ImagesResponseModel> images;

            public List<ImagesResponseModel> getImages() {
                return images;
            }

            public void setImages(List<ImagesResponseModel> images) {
                this.images = images;
            }
        }

        public static class Media {

            @SerializedName("reddit_video")
            @Expose
            @Embedded
            private RedditVideo redditVideo;

            public RedditVideo getRedditVideo() {
                return redditVideo;
            }

            public void setRedditVideo(RedditVideo redditVideo) {
                this.redditVideo = redditVideo;
            }

            public static class RedditVideo {

                @SerializedName("fallback_url")
                @Expose
                @ColumnInfo(name = "video_url")
                private String videoUrl;
                @SerializedName("height")
                @Expose
                @ColumnInfo(name = "video_height")
                private Integer videoHeight;
                @SerializedName("width")
                @Expose
                @ColumnInfo(name = "video_width")
                private Integer videoWidth;
                @SerializedName("duration")
                @Expose
                @ColumnInfo(name = "video_duration")
                private Integer videoDuration;
                @SerializedName("is_gif")
                @Expose
                @ColumnInfo(name = "is_gif")
                private Boolean isGif;

                public String getVideoUrl() {
                    return videoUrl;
                }

                public void setVideoUrl(String videoUrl) {
                    this.videoUrl = videoUrl;
                }

                public Integer getVideoHeight() {
                    return videoHeight;
                }

                public void setVideoHeight(Integer videoHeight) {
                    this.videoHeight = videoHeight;
                }

                public Integer getVideoWidth() {
                    return videoWidth;
                }

                public void setVideoWidth(Integer videoWidth) {
                    this.videoWidth = videoWidth;
                }

                public Integer getVideoDuration() {
                    return videoDuration;
                }

                public void setVideoDuration(Integer videoDuration) {
                    this.videoDuration = videoDuration;
                }

                public Boolean getGif() {
                    return isGif;
                }

                public void setGif(Boolean gif) {
                    isGif = gif;
                }
            }
        }
    }
}