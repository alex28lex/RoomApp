package com.example.pagination.roomapp.db.helper;

import com.example.pagination.roomapp.db.AppDatabase;
import com.example.pagination.roomapp.db.ImageItem;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

/**
 * Developed by Magora Team (magora-systems.com). 2017.
 *
 * @author mihaylov
 */
public class DbHelper implements DbContact {
    private static DbHelper instance;


    public static DbHelper getInstance() {
        return instance == null ? new DbHelper() : instance;
    }


    @Override
    public Flowable<Boolean> insertAll(final ImageItem... items) {
        return Flowable.create(new FlowableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(FlowableEmitter<Boolean> e) throws Exception {
                AppDatabase.getInstance().getImageItemDao().insertAll(items);
                e.onNext(true);
                e.onComplete();
            }
        }, BackpressureStrategy.DROP);

    }

    @Override
    public Flowable<Boolean> delete(final ImageItem imageItem) {
        return Flowable.create(new FlowableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(FlowableEmitter<Boolean> e) throws Exception {
                AppDatabase.getInstance().getImageItemDao().delete(imageItem);
                e.onNext(true);
                e.onComplete();
            }
        }, BackpressureStrategy.DROP);
    }

    @Override
    public Flowable<List<ImageItem>> getAllImages() {
        return Flowable.create(new FlowableOnSubscribe<List<ImageItem>>() {
            @Override
            public void subscribe(FlowableEmitter<List<ImageItem>> e) throws Exception {
                List<ImageItem> list = AppDatabase.getInstance().getImageItemDao().getAllImages();
                e.onNext(list);
                e.onComplete();
            }
        }, BackpressureStrategy.DROP);
    }

    @Override
    public Flowable<Integer> getCountImages() {
        return Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                int count = AppDatabase.getInstance().getImageItemDao().countImages();
                e.onNext(count);
                e.onComplete();
            }
        }, BackpressureStrategy.DROP);

    }
}
