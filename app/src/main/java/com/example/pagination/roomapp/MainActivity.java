package com.example.pagination.roomapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.pagination.roomapp.db.ImageItem;
import com.example.pagination.roomapp.db.helper.DbHelper;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    protected RecyclerView recyclerView;

    @BindView(R.id.progress)
    protected ProgressBar progressBar;

    @BindView(R.id.addItemButton)
    protected Button add;

    private ImagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new ImagesAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition(); //get position which is swipe
                removeData(position);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        loadData();
        add.setOnClickListener(view -> addData(getRandImageItem()));


    }

    private ImageItem getRandImageItem() {
        Random generator = new Random();
        int i = generator.nextInt(20) + 1;
        return ImageItem.builder().name("item " + i).url("url").build();
    }

    private void removeData(int position) {
        DbHelper.getInstance().delete(adapter.getItemByPosition(position))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        aBoolean -> adapter.removeItem(position),
                        throwable -> showMessage(throwable.getMessage())
                );
    }

    private void addData(ImageItem item) {
        DbHelper.getInstance().insertAll()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        aBoolean -> adapter.addItem(item),
                        throwable -> showMessage(throwable.getMessage())
                );
    }

    private void loadData() {
        DbHelper.getInstance().getAllImages()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        imageItems -> adapter.addItems(imageItems),
                        throwable -> {
                            throwable.printStackTrace();
                            showMessage(throwable.getMessage());
                        });

    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
