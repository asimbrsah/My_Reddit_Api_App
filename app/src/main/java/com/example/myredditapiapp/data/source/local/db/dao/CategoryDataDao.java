package com.example.myredditapiapp.data.source.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myredditapiapp.data.model.response.CategoryResponseModel;

import io.reactivex.Single;

@Dao
public interface CategoryDataDao {
//
//    @Query("SELECT * from category_data_table ORDER BY id ASC")
//    Single<CategoryResponseModel> getCategoryData();
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void setCategoryData(CategoryResponseModel categoryResponseModel);
//
//    @Query("DELETE FROM category_data_table")
//    void deleteAllCategoryData();
//
//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    void updateCategoryData(CategoryResponseModel categoryResponseModel);
}