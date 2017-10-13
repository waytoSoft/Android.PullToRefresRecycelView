package com.jg.recyclerview.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jg.recyclerview.R;

/**
 * author: hezhiWu <wuhezhi007@gmail.com>
 * version: V1.0
 * created at 2017/9/30$ 下午2:17$
 * <p>
 * Copyright (c) 2017 Shenzhen O&M Cloud Co., Ltd. All rights reserved.
 */

public class RecyclerViewListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_list_layout);

        findViewById(R.id.List_Down_Refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecyclerViewListActivity.this, RecyclerViewDownRefreshActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.List_Pull_Refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecyclerViewListActivity.this, RecyclerViewPullRefreshActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.List_Both_Refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecyclerViewListActivity.this, RecyclerViewBothRefreshActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.List_Disabled).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecyclerViewListActivity.this, RecyclerViewDisabledRefreshActivity.class);
                startActivity(intent);
            }
        });
    }
}
