package com.easyway.mismclient.ui.adapter;

import android.content.Context;
import android.view.View;

import com.easyway.mismclient.model.DetailListBean;

import java.util.List;

/**
 * 盘点页下的listview的adapter
 */
public class LsvGuiHuanAdapter extends LsvBusinessBaseAdapter {


    public LsvGuiHuanAdapter(Context mContext, List list) {
        super(mContext, list);
    }

    @Override
    public void fillValue(int position, ViewHolder viewHolder, List list, boolean isClick) {
        if(getCount()==1){
            ((DetailListBean) list.get(position)).setFactAmount(1);
        }

        viewHolder.mLlRealTimeAmount.setVisibility(View.INVISIBLE);
    }

}
