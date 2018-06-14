package com.hearing.volometer.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.hearing.volometer.R;

public class ConnectionModeActivity extends AppCompatActivity {
    RelativeLayout rl_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_mode);
        initView();
        initListener();
    }

    private void initView() {
        rl_back = findViewById(R.id.rl_back);
    }

    private void initListener() {
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
