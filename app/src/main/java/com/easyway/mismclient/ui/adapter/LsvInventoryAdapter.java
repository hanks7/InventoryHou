package com.easyway.mismclient.ui.adapter;

import android.content.Context;
import android.view.View;

import java.util.List;

/**
 * 盘点页下的listview的adapter
 */
public class LsvInventoryAdapter extends LsvBusinessBaseAdapter {


    public LsvInventoryAdapter(Context mContext, List list) {
        super(mContext, list);
    }

    @Override
    public void fillValue(int position, ViewHolder viewHolder, List list, boolean isClick) {
        viewHolder.mLlRealTimeAmount.setVisibility(View.INVISIBLE);
    }
}
