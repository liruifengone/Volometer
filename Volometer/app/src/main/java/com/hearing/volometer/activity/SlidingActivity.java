package com.hearing.volometer.activity;

import android.os.Bundle;

import com.hearing.volometer.view.SlidingLayout;


/**
 * Created by Emrys on 2017/9/12.
 */

public class SlidingActivity extends BaseActivity {
    protected boolean showToast;  // 指示当前activity是否能显示toast
    SlidingLayout rootView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (enableSliding()) {
            rootView = new SlidingLayout(this);
            rootView.bindActivity(this);
        }
    }

    protected boolean enableSliding() {
        return true;
    }

    @Override
    public void onBackPressed() {
        rootView.scrollClose();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showToast = true;
    }

    @Override
    protected void onStop() {
        showToast = false;
        super.onStop();
    }
}
