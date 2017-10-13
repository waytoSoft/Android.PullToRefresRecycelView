package com.jg.recyclerview.list;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jg.recyclerview.R;
import com.jg.recyclerview.widget.PullToRefresRecyclerView;
import com.jg.recyclerview.widget.PullToRefreshRecyclerViewListener;

import java.util.ArrayList;
import java.util.List;

/**
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/9/30$ 上午10:24$
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */

public class RecyclerViewBothRefreshActivity extends AppCompatActivity implements PullToRefreshRecyclerViewListener {

    PullToRefresRecyclerView recyclerView;

    private RecyclerViewListAdapter adapter;

    private int index = 0;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x10:
                    index = 0;
                    adapter.clearList();
                    initData();
                    recyclerView.onCloseDownRefresh();
                    break;
                case 0x30:
                    String[] images=getResources().getStringArray(R.array.imageV2_array);
                    adapter.addItems(images);

                    index++;

                    if (index == 3) {
                        recyclerView.onCloseLoadMore();
                    } else
                        recyclerView.onLoadMoreComplete();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_list);
        recyclerView = (PullToRefresRecyclerView) findViewById(R.id.RecyclerView_List);

        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new RecyclerViewListAdapter(this);
        recyclerView.setRecyclerViewAdapter(adapter);
        recyclerView.setPullToRefreshRecyclerViewListener(this);
        recyclerView.setMode(PullToRefresRecyclerView.Mode.BOTH);

        recyclerView.startDownRefresh();
    }

    private void initData() {
        String[] images=getResources().getStringArray(R.array.imags_array);
        adapter.addItems(images);
    }

    @Override
    public void onDownRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0x10);
            }
        }, 2000);
    }

    @Override
    public void onPullRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0x30);
            }
        }, 3000);
    }
}
