package com.easyway.mismclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyway.mismclient.R;

import java.util.List;

/**
 * @author 侯建军 47466436@qq.com
 * @class com.easyway.mismclient.ui.adapter.LsvBusinessBaseAdapter
 * @time 2018/9/17 15:57
 * @description 盘点封装的baseAdapter
 */
public class LsvDialogInventoryAdapter extends LsvPopSelectAdapter {

    public LsvDialogInventoryAdapter(Context mContext, List list) {
        super(mContext, list);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder2 viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder2) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_drop_down, null);
            viewHolder = new ViewHolder2(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.fillValue(position, checkItemPosition, (String) list.get(position), mContext);
        return convertView;
    }

}
