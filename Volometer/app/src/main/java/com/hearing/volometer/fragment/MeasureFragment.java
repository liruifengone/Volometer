package com.hearing.volometer.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.*;
import com.hearing.volometer.R;


public class MeasureFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_measure, null);
        return view;
    }
}
