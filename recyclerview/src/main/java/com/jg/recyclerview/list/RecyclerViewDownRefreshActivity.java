package com.jg.recyclerview.list;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jg.recyclerview.R;
import com.jg.recyclerview.widget.PullToRefresRecyclerView;
import com.jg.recyclerview.widget.PullToRefreshRecyclerViewListener;

/**
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/9/30$ 上午10:24$
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */

public class RecyclerViewDownRefreshActivity extends AppCompatActivity implements PullToRefreshRecyclerViewListener {

    PullToRefresRecyclerView recyclerView;

    private RecyclerViewListAdapter adapter;

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0x10:
                    adapter.clearList();
                    initData();
                    recyclerView.onCloseDownRefresh();
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

    }
}
