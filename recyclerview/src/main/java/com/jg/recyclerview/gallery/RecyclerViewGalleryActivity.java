package com.jg.recyclerview.gallery;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jg.recyclerview.R;
import com.jg.recyclerview.widget.PullToRefresRecyclerView;
import com.jg.recyclerview.widget.PullToRefreshRecyclerViewListener;
import com.jg.recyclerview.widget.RecyclerViewBaseAdapter;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/10/13 10:16
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class RecyclerViewGalleryActivity extends AppCompatActivity implements PullToRefreshRecyclerViewListener, RecyclerViewBaseAdapter.OnRecyclerViewItemClickListener {

    private PullToRefresRecyclerView refresRecyclerView;
    private ImageView imageView;

    private RecyclerViewGalleryAdapter adapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x980:
                    adapter.clearList();
                    String[] images = getResources().getStringArray(R.array.imags_array);
                    adapter.addItems(images);
                    refresRecyclerView.onCloseDownRefresh();

                    Glide.with(RecyclerViewGalleryActivity.this)
                            .load(images[0])
                            .placeholder(R.mipmap.default_img)
                            .error(R.mipmap.default_img)
                            .into(imageView);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_gallery);
        refresRecyclerView = (PullToRefresRecyclerView) findViewById(R.id.recyclerView_Gallery);
        imageView = (ImageView) findViewById(R.id.recyclerView_Gallery_ImageView);

        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new RecyclerViewGalleryAdapter(this);

        refresRecyclerView.setRecyclerViewAdapter(adapter);
        refresRecyclerView.setPullToRefreshRecyclerViewListener(this);
        refresRecyclerView.setOnItemClickListener(this);
        refresRecyclerView.startDownRefresh();
    }

    @Override
    public void onDownRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0x980);
            }
        }, 3000);
    }

    @Override
    public void onPullRefresh() {

    }

    @Override
    public void onItemClick(View view, Object data, int position) {
//        Toast.makeText(this, data.toString(), Toast.LENGTH_LONG).show();
        Glide.with(this).load(data.toString()).into(imageView);
    }
}
