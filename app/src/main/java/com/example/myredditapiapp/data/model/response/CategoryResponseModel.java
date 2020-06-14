package com.example.myredditapiapp.data.model.response;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "category_data_table")
public class CategoryResponseModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("data")
    @Expose
    @Embedded
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

        @SerializedName("dist")
        @Expose
        @ColumnInfo(name = "dist")
        private Integer dataListCount;

        @SerializedName("children")
        @Expose
        @Ignore
        private List<ChildrenResponseModel> children;

        @SerializedName("after")
        @Expose
        @ColumnInfo(name = "category_default_key")
        private String categoryDefaultKey;

        public Integer getDataListCount() {
            return dataListCount;
        }

        public void setDataListCount(Integer dataListCount) {
            this.dataListCount = dataListCount;
        }

        public List<ChildrenResponseModel> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenResponseModel> children) {
            this.children = children;
        }

        public String getCategoryDefaultKey() {
            return categoryDefaultKey;
        }

        public void setCategoryDefaultKey(String categoryDefaultKey) {
            this.categoryDefaultKey = categoryDefaultKey;
        }
    }
}