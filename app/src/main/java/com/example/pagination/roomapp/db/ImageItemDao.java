package com.example.pagination.roomapp.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Developed by Magora Team (magora-systems.com). 2017.
 *
 * @author mihaylov
 */
@Dao
public interface ImageItemDao {
    @Insert
    void insertAll(ImageItem... items);

    @Delete
    void delete(ImageItem imageItem);

    @Query("SELECT * FROM image")
    List<ImageItem> getAllImages();

    @Query("SELECT COUNT(*) from image")
    int countImages();


}
