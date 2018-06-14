package com.hearing.volometer.view.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hearing.volometer.R;


/**
 * author: Aaron
 * date:  2017/4/3
 */

public class LoadMoreWrapAdapter extends RecyclerView.Adapter{
    private RecyclerView.Adapter mRealAdapter;

    private final int TYPE_FOOTER = 0X911;

    public static final int STATE_NORMAL = 0x1001;
    public static final int STATE_ERROR = 0x1002;
    public static final int STATE_LOADING = 0x1003;
    public static final int STATE_NO_MORE = 0x1004;
    private int currState = STATE_NORMAL;

    private FooterHolder mFooterHolder;

    public LoadMoreWrapAdapter(RecyclerView.Adapter adapter) {
        this.mRealAdapter = adapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == TYPE_FOOTER) {
            View view;
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_load_more_footer, null);

            mFooterHolder = new FooterHolder(view);
            return mFooterHolder;
        }else {
            return mRealAdapter.onCreateViewHolder(viewGroup, i);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (getItemViewType(i) != TYPE_FOOTER) {
            mRealAdapter.onBindViewHolder(viewHolder, i);
        }else {
            FooterHolder holder = (FooterHolder) viewHolder;
            holder.setData(currState);
        }
    }

    @Override
    public int getItemCount() {
        return mRealAdapter.getItemCount() == 0 ? 0 : mRealAdapter.getItemCount() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_FOOTER;
        }else {
            return mRealAdapter.getItemViewType(position);
        }
    }

    private class FooterHolder extends RecyclerView.ViewHolder {
        View ll_footer_loading;
        View ll_footer_error;
        View ll_footer_no_more;
        FooterHolder(View itemView) {
            super(itemView);
            ll_footer_loading = itemView.findViewById(R.id.ll_footer_loading);
            ll_footer_error = itemView.findViewById(R.id.ll_footer_error);
            ll_footer_no_more = itemView.findViewById(R.id.ll_footer_no_more);
        }

        public void setData(int status) {
            currState = status;
            switch (status) {
                case STATE_NORMAL:
                    setAllGone();
                    break;
                case STATE_LOADING:
                    setAllGone();
                    ll_footer_loading.setVisibility(View.VISIBLE);
                    break;
                case STATE_NO_MORE:
                    setAllGone();
                    ll_footer_no_more.setVisibility(View.VISIBLE);
                    break;
                case STATE_ERROR:
                    setAllGone();
                    ll_footer_error.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
        }

        void setAllGone() {
            if (ll_footer_loading != null) {
                ll_footer_loading.setVisibility(View.GONE);
            }
            if (ll_footer_no_more != null) {
                ll_footer_no_more.setVisibility(View.GONE);
            }
            if (ll_footer_error != null) {
                ll_footer_error.setVisibility(View.GONE);
            }
        }
    }

    public void setFooterState(int state) {
        currState = state;
        notifyDataSetChanged();
//        if (mFooterHolder != null) {
//            mFooterHolder.setData(state);
//        }
    }
}
