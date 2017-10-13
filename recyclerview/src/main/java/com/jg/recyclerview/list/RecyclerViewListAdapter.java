package com.jg.recyclerview.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jg.recyclerview.R;
import com.jg.recyclerview.widget.RecyclerViewBaseAdapter;

/**
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/9/30$ 上午11:36$
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */

public class RecyclerViewListAdapter extends RecyclerViewBaseAdapter<String> {

    public RecyclerViewListAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onBaseCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.item_recyclerview_list, parent, false));
    }

    @Override
    public void onBaseBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
//        viewHolder.textView.setText(mList.get(position));
        Glide.with(mContent).load(mList.get(position)).into(((ViewHolder) holder).imageView);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.Item_List_TextView);
            imageView = view.findViewById(R.id.Item_List_ImageView);
        }
    }

}
