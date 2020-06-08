package com.example.myredditapiapp.data.source.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myredditapiapp.data.model.response.CategoryResponseModel;
import com.example.myredditapiapp.data.source.local.db.dao.CategoryDataDao;

//@Database(entities = CategoryResponseModel.class, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CategoryDataDao categoryDataDao();
}