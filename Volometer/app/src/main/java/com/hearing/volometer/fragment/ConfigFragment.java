package com.hearing.volometer.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.hearing.volometer.R;
import com.hearing.volometer.activity.ConfigImportActivity;
import com.hearing.volometer.activity.LauncherActivity;
import com.hearing.volometer.adapter.FragmentAdapter;
import com.hearing.volometer.adapter.TwoFragmentAdapter;
import com.hearing.volometer.view.TwoViewPagerFixed;
import com.hearing.volometer.view.ViewPagerFixed;

import java.util.ArrayList;
import java.util.List;


public class ConfigFragment extends BaseFragment {

    private EditText et_test_point_name;
    private EditText et_wiring_mode_config;
    private EditText et_PT_change_ratio;
    private EditText et_CT_change_ratio;
    private EditText et_safety_warning_threshold;

    private TextView import_config;
    private TextView confirm;

    private String test_point_name;
    private String wiring_mode_config;
    private String PT_change_ratio;
    private String CT_change_ratio;
    private String safety_warning_threshold;


    final private String TAG = "ConfigFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_config, null);

        initView(view);
        initListener();

        return view;
    }

    private void initListener() {
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LauncherActivity.class);
                startActivity(intent);

            }
        });

        import_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ConfigImportActivity.class);
                startActivity(intent);

            }
        });
    }

    private void initView(View view) {
        et_CT_change_ratio=view.findViewById(R.id.et_test_point_name);
        et_wiring_mode_config=view.findViewById(R.id.et_wiring_mode_config);
        et_CT_change_ratio=view.findViewById(R.id.et_CT_change_ratio);
        et_PT_change_ratio=view.findViewById(R.id.et_PT_change_ratio);
        et_safety_warning_threshold=view.findViewById(R.id.et_safety_warning_threshold);
        confirm=view.findViewById(R.id.confirm);
        import_config=view.findViewById(R.id.import_config);
    }
}
