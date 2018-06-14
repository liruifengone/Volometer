package com.hearing.volometer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hearing.volometer.R;
import com.hearing.volometer.activity.ConnectionModeActivity;
import com.hearing.volometer.activity.MeasureHistoryActivity;
import com.hearing.volometer.activity.MineSyncActivity;

/**
 * Create by hearing on 18-1-18
 */
public class MineFragment extends BaseFragment {

    private LinearLayout ll_mine_sync;
    private LinearLayout ll_mine_history;
    private LinearLayout ll_mine_connect;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        initView(view);
        initListener();
        return view;
    }

    private void initListener() {
        ll_mine_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MeasureHistoryActivity.class);
                startActivity(intent);
            }
        });

        ll_mine_sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MineSyncActivity.class);
                startActivity(intent);
            }
        });

        ll_mine_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ConnectionModeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView(View view) {
        ll_mine_connect=view.findViewById(R.id.ll_mine_connect);
        ll_mine_history=view.findViewById(R.id.ll_mine_history);
        ll_mine_sync=view.findViewById(R.id.ll_mine_sync);
    }

}
