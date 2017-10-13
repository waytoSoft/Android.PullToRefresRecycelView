package com.jg.recyclerview.gallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jg.recyclerview.R;
import com.jg.recyclerview.widget.RecyclerViewBaseAdapter;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/10/13 10:24
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class RecyclerViewGalleryAdapter extends RecyclerViewBaseAdapter<String> {
    public RecyclerViewGalleryAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onBaseCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_recyclerview_gallery, parent, false));
    }

    @Override
    public void onBaseBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Glide.with(mContent)
                .load(mList.get(position))
                .placeholder(R.mipmap.default_img)
                .error(R.mipmap.default_img)
                .into(viewHolder.imageView);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.Item_Gallery_ImageView);
        }
    }
}
