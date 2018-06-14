package com.hearing.volometer.view.recycler;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * author: Aaron
 * date:  2017/4/3
 */

public class LoadMoreRecyclerView extends RecyclerView {
    private OnLoadMoreListener mLoadMoreListener;

    public LoadMoreRecyclerView(Context context) {
        this(context, null);
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setOnScrollListener(new LoadMoreScrollListener() {
            @Override
            void onLoadMore(RecyclerView recyclerView) {
                if (mLoadMoreListener != null) {
                    mLoadMoreListener.onLoadMore(recyclerView);
                }
            }
        });
    }

    @Override
    public void setAdapter(Adapter adapter) {
        LoadMoreWrapAdapter wrapAdapter = new LoadMoreWrapAdapter(adapter);
        wrapAdapter.setFooterState(LoadMoreWrapAdapter.STATE_NORMAL);
        super.setAdapter(wrapAdapter);
    }

    @Override
    public LoadMoreWrapAdapter getAdapter() {
        return (LoadMoreWrapAdapter)super.getAdapter();
    }

    private abstract class LoadMoreScrollListener extends OnScrollListener {

        private int lastVisibleItemPosition;
        private int currentScrollState = 0;
        private float distanceY = 0;//判断滑动方向

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            distanceY = dy;
            lastVisibleItemPosition  = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            currentScrollState = newState;
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            if (distanceY > 0 && (visibleItemCount > 0
                    && currentScrollState == RecyclerView.SCROLL_STATE_IDLE
                    && (lastVisibleItemPosition) >= totalItemCount - 1)) {
                distanceY = 0;
                onLoadMore(LoadMoreRecyclerView.this);
            }
        }

        abstract void onLoadMore(RecyclerView recyclerView);
    }



    public interface OnLoadMoreListener {
        void onLoadMore(RecyclerView recyclerView);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        mLoadMoreListener = listener;
    }
}
