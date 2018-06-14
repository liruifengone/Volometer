package com.hearing.volometer.activity;

import android.content.Intent;
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
import com.hearing.volometer.fragment.ConfigLocalFragment;
import com.hearing.volometer.fragment.ConfigNetFragment;
import com.hearing.volometer.util.NumUtils;
import com.hearing.volometer.view.DrawableRadioButton;
import com.hearing.volometer.view.ViewPagerFixed;

import java.util.ArrayList;
import java.util.List;

public class ConfigImportActivity extends SlidingActivity {

    private ViewPagerFixed vp_import_config;
    private RadioGroup rg_import_config;
    private RelativeLayout rl_back;
    private List<BaseFragment> mFragments;
    private DrawableRadioButton rb_local_config;
    private DrawableRadioButton rb_net_config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_import);
        initView();
        initData();
        initListener();
        setDrawableTop();
    }

    private void initView(){
        vp_import_config = (ViewPagerFixed) findViewById(R.id.vp_import_config);
        rg_import_config = (RadioGroup) findViewById(R.id.rg_import_config);
        rl_back = (RelativeLayout) findViewById(R.id.rl_back);
        rb_local_config = (DrawableRadioButton) findViewById(R.id.rb_local_config);
        rb_net_config = (DrawableRadioButton) findViewById(R.id.rb_net_config);
    }

    private void initData(){
        mFragments = new ArrayList<>();
        ConfigLocalFragment configLocalFragment = new ConfigLocalFragment();
        ConfigNetFragment configNetFragment = new ConfigNetFragment();
        mFragments.add(configLocalFragment);
        mFragments.add(configNetFragment);
        vp_import_config.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in, R.anim.anim_slide_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_slide_in, R.anim.anim_slide_out);

    }

    private void initListener(){
        rg_import_config.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId)
                {
                    case R.id.rb_local_config:
                        vp_import_config.setCurrentItem(0,true);
                        break;
                    case R.id.rb_net_config:
                        vp_import_config.setCurrentItem(1, true);
                        break;
                }

            }
        });

        vp_import_config.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    rg_import_config.check(R.id.rb_local_config);
                }else if (position == 1){
                    rg_import_config.check(R.id.rb_net_config);
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

    private void setDrawableTop() {
        int size;
        size = NumUtils.dip2px(this,30);
        DrawableRadioButton rb_history = (DrawableRadioButton)findViewById(R.id.rb_local_config);
        Drawable drawable = getResources().getDrawable(R.drawable.selector_local);
        drawable.setBounds(0,0,size,size);
        rb_history.setCompoundDrawables(drawable,null,null,null);
        rb_history.setDrawableWidth(size);
        rb_history.setDrawableHeight(size);
        rb_history.invalidate();

        rb_history = (DrawableRadioButton) findViewById(R.id.rb_net_config);
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
