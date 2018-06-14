package com.hearing.volometer.fragment;


import android.support.v4.app.Fragment;

/**
 *
 * Created by Aaron on 2017/3/8.
 */

public class BaseFragment extends Fragment {
    protected boolean showToast;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        showToast = isVisibleToUser;
    }

    @Override
    public void onDestroy() {
        showToast = false;
        super.onDestroy();
    }

    public boolean isShowToast() {
        return showToast;
    }

    public void setShowToast(boolean showToast) {
        this.showToast = showToast;
    }
}
