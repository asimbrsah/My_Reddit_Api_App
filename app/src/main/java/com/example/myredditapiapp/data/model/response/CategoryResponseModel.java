package com.example.myredditapiapp.data.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponseModel {

    @SerializedName("id")
    private int id;

    @SerializedName("data")
    private Data data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {

        @SerializedName("children")
        private List<Children> children = null;

        public List<Children> getChildren() {
            return children;
        }

        public void setChildren(List<Children> children) {
            this.children = children;
        }

        public static class Children {

            @SerializedName("data")
            private ChildrenData childrenData;

            public ChildrenData getChildrenData() {
                return childrenData;
            }

            public void setChildrenData(ChildrenData childrenData) {
                this.childrenData = childrenData;
            }

            public static class ChildrenData {

                @SerializedName("subreddit")
                private String subReddit;
                @SerializedName("title")
                private String title;
                @SerializedName("score")
                private int score;
                @SerializedName("domain")
                private String domain;
                @SerializedName("id")
                private String id;
                @SerializedName("num_comments")
                private int numComments;
                @SerializedName("url")
                private String url;

                public String getSubReddit() {
                    return subReddit;
                }

                public void setSubReddit(String subReddit) {
                    this.subReddit = subReddit;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getScore() {
                    return score;
                }

                public void setScore(int score) {
                    this.score = score;
                }

                public String getDomain() {
                    return domain;
                }

                public void setDomain(String domain) {
                    this.domain = domain;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public int getNumComments() {
                    return numComments;
                }

                public void setNumComments(int numComments) {
                    this.numComments = numComments;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}