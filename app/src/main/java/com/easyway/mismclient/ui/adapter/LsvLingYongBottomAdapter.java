package com.easyway.mismclient.ui.adapter;

import android.content.Context;

import java.util.List;


/**
 * 盘点页下的-(领用)-(还库)的adapter
 */
public class LsvLingYongBottomAdapter extends LsvBusinessBaseAdapter {

    private String strNumName;

    public LsvLingYongBottomAdapter(Context mContext, List list, String strNumName) {
        super(mContext, list);
        this.strNumName = strNumName;
    }

    @Override
    public void fillValue(int position, ViewHolder viewHolder, List list, boolean isClick) {
        viewHolder.mTvFactAmountTag.setText(strNumName + ":");
    }


}

