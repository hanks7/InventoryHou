package com.easyway.mismclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseMyAdapter;

import java.util.List;

/**
 * @author 侯建军 47466436@qq.com
 * @class com.easyway.mismclient.ui.adapter.LsvBusinessBaseAdapter
 * @time 2018/9/17 15:57
 * @description 盘点封装的baseAdapter
 */
public class LsvInfoAdapter extends BaseMyAdapter {

    public LsvInfoAdapter(Context mContext, List list) {
        super(mContext, list);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LsvInfoViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (LsvInfoViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_info, null);
            viewHolder = new LsvInfoViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.fillValue(position, list);
        return convertView;
    }


}
