package com.hearing.volometer.activity;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.hearing.volometer.R;
import com.hearing.volometer.fragment.BaseFragment;
import com.hearing.volometer.fragment.HistoryLocalFragment;
import com.hearing.volometer.fragment.HistoryNetFragment;
import com.hearing.volometer.util.NumUtils;
import com.hearing.volometer.view.DrawableRadioButton;
import com.hearing.volometer.view.ViewPagerFixed;

import java.util.ArrayList;
import java.util.List;

public class MeasureHistoryActivity extends AppCompatActivity {

    private RadioGroup rg_measure_history;
    private DrawableRadioButton rb_local_history;
    private DrawableRadioButton rb_net_history;
    private ViewPagerFixed vp_measure_history;
    private List<BaseFragment> mFragments;
    private RelativeLayout rl_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_history);
        initView();
        initData();
        initListener();
        setDrawableTop();
    }

    private void initView() {
        rg_measure_history = (RadioGroup)findViewById(R.id.rg_measure_history);
        rb_local_history = (DrawableRadioButton) findViewById(R.id.rb_local_history);
        rb_net_history = (DrawableRadioButton) findViewById(R.id.rb_net_history);
        vp_measure_history = (ViewPagerFixed)findViewById(R.id.vp_measure_history);
        rl_back = (RelativeLayout)findViewById(R.id.rl_back);
    }

    private void initListener(){
        rg_measure_history.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_local_history:
                        vp_measure_history.setCurrentItem(0, true);
                        break;
                    case R.id.rb_net_history:
                        vp_measure_history.setCurrentItem(1, true);
                        break;
                }
            }
        });
        vp_measure_history.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    rg_measure_history.check(R.id.rb_local_history);
                }else if (position == 1){
                    rg_measure_history.check(R.id.rb_net_history);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rl_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new HistoryLocalFragment());
        mFragments.add(new HistoryNetFragment());
        vp_measure_history.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

    }

    private void setDrawableTop() {
        int size;
        size = NumUtils.dip2px(this,30);
        DrawableRadioButton rb_history = (DrawableRadioButton)findViewById(R.id.rb_local_history);
        Drawable drawable = getResources().getDrawable(R.drawable.selector_local);
        drawable.setBounds(0,0,size,size);
        rb_history.setCompoundDrawables(drawable,null,null,null);
        rb_history.setDrawableWidth(size);
        rb_history.setDrawableHeight(size);
        rb_history.invalidate();

        rb_history = (DrawableRadioButton) findViewById(R.id.rb_net_history);
        drawable = getResources().getDrawable(R.drawable.selector_network);
        drawable.setBounds(0, 0, size, size);
        rb_history.setCompoundDrawables(drawable, null, null, null);
        rb_history.setDrawableWidth(size);
        rb_history.setDrawableHeight(size);
        rb_history.invalidate();

    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }
}
