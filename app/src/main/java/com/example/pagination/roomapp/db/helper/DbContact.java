package com.example.pagination.roomapp.db.helper;

import com.example.pagination.roomapp.db.ImageItem;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Developed by Magora Team (magora-systems.com). 2017.
 *
 * @author mihaylov
 */

public interface DbContact {
    Flowable<Boolean> insertAll(ImageItem... items);

    Flowable<Boolean> delete(ImageItem imageItem);

    Flowable<List<ImageItem>> getAllImages();

    Flowable<Integer> getCountImages();


}
