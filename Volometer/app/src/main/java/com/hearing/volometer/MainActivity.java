package com.hearing.volometer;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import com.hearing.volometer.adapter.FragmentAdapter;
import com.hearing.volometer.fragment.ConfigFragment;
import com.hearing.volometer.fragment.MeasureFragment;
import com.hearing.volometer.fragment.MineFragment;
import com.hearing.volometer.view.ViewPagerFixed;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentAdapter fragmentAdapter;
    private ViewPagerFixed viewPager;
    private TabLayout tabLayout;

    private List<Fragment> fragments;
    private List<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main);

        fragments = new ArrayList<>();
        fragments.add(new MeasureFragment());
        fragments.add(new ConfigFragment());
        fragments.add(new MineFragment());

        titles = new ArrayList<>();
        titles.add("测量");
        titles.add("配置");
        titles.add("我的");

        viewPager = (ViewPagerFixed) findViewById(R.id.tab_view_pager);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);

        viewPager.setAdapter(fragmentAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.selector_main_measure);
        tabLayout.getTabAt(1).setIcon(R.drawable.selector_main_config);
        tabLayout.getTabAt(2).setIcon(R.drawable.selector_main_mine);
    }
}
