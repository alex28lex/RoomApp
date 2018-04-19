package com.example.pagination.roomapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pagination.roomapp.db.ImageItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Developed by Magora Team (magora-systems.com). 2017.
 *
 * @author mihaylov
 */
public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.GalleryImageViewHolder> {

    private List<ImageItem> items;

    public ImagesAdapter() {
        this.items = new ArrayList<>();
    }

    public void setItems(List<ImageItem> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void addItems(List<ImageItem> items) {
        if (items.size() != 0) {
            this.items.addAll(items);
            notifyDataSetChanged();
        }
    }

    public ImageItem getItemByPosition(int pos) {
        return items.get(pos);
    }

    public void addItem(ImageItem item) {
        this.items.add(item);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public ImagesAdapter.GalleryImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ImagesAdapter.GalleryImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImagesAdapter.GalleryImageViewHolder holder, int position) {

        ImageItem imageItemDto = items.get(position);

        holder.categoryText.setText(imageItemDto.getName());
        Glide.with(holder.image.getContext())
                .load(imageItemDto.getUrl());
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    class GalleryImageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        protected ImageView image;

        @BindView(R.id.image_name_text)
        protected TextView categoryText;

        public GalleryImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}