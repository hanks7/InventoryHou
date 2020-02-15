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
public class LsvBusinessBaseAdapter extends BaseMyAdapter {
    private boolean isClick = true;

    public LsvBusinessBaseAdapter(Context mContext, List list) {
        super(mContext, list);
    }

    public void setIsClick(List listBean, boolean isClick) {
        this.isClick = isClick;
        list.clear();
        list.remove(list);
        if (listBean != null) {
            list.addAll(listBean);
        }
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_off_inventory, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.fillValue(position, list, isClick);
        fillValue(position, viewHolder, list, isClick);
        return convertView;
    }

    /**
     * 填充数据
     * @param position
     * @param viewHolder
     * @param list
     * @param isClick
     */
    public void fillValue(int position, final ViewHolder viewHolder, List list, boolean isClick) {
    }

}
