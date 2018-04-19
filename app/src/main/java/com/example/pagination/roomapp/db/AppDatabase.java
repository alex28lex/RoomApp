package com.example.pagination.roomapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

import com.example.pagination.roomapp.App;

/**
 * Developed by Magora Team (magora-systems.com). 2017.
 *
 * @author mihaylov
 */
@Database(entities = {ImageItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase instance;

    public static AppDatabase getInstance() {
        if (instance == null) {
            instance = Room.databaseBuilder(App.getInstance(), AppDatabase.class, "image-database").build();
        }
        return instance;
    }

    public abstract ImageItemDao getImageItemDao();

}
