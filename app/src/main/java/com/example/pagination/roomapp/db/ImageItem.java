package com.example.pagination.roomapp.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import lombok.Builder;
import lombok.Data;

/**
 * Developed by Magora Team (magora-systems.com). 2017.
 *
 * @author mihaylov
 */
@Data
@Builder
@Entity(tableName = "image")
public class ImageItem {
    @PrimaryKey(autoGenerate = true)
    Long id;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "url")
    String url;

    public ImageItem(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }
}
