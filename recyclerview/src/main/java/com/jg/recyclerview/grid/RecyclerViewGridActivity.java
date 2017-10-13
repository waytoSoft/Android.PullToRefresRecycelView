package com.jg.recyclerview.grid;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jg.recyclerview.R;
import com.jg.recyclerview.list.RecyclerViewListAdapter;
import com.jg.recyclerview.widget.PullToRefresRecyclerView;
import com.jg.recyclerview.widget.PullToRefreshRecyclerViewListener;

/**
 * author: hezhiWu <hezhi.woo@gmail.com>
 * version: V1.0
 * created at 2017/10/13 9:25
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */
public class RecyclerViewGridActivity extends AppCompatActivity implements PullToRefreshRecyclerViewListener {

    private PullToRefresRecyclerView refresRecyclerView;

    private RecyclerViewListAdapter adapter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x880:
                    adapter.clearList();
                    String[] images = getResources().getStringArray(R.array.imags_array);
                    adapter.addItems(images);
                    refresRecyclerView.onCloseDownRefresh();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_grid);

        refresRecyclerView = (PullToRefresRecyclerView) findViewById(R.id.recyclerView_Grid);

        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new RecyclerViewListAdapter(this);

        refresRecyclerView.setRecyclerViewAdapter(adapter);
        refresRecyclerView.setPullToRefreshRecyclerViewListener(this);

        refresRecyclerView.startDownRefresh();
    }

    @Override
    public void onDownRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0x880);
            }
        }, 3000);
    }

    @Override
    public void onPullRefresh() {

    }
}
