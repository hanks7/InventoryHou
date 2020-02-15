package com.easyway.mismclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyway.mismclient.R;
import com.easyway.mismclient.model.MDepartmentBean;

import java.util.List;

/**
 * 存放科室的adapter
 */
public class LsvPopCunFanKeshiAdapter extends LsvPopSelectAdapter {


    public LsvPopCunFanKeshiAdapter(Context mContext, List list) {
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
        viewHolder.fillValue(position, checkItemPosition, ((MDepartmentBean.DetailsBean) list.get(position)).getDepartmentName(), mContext);
        return convertView;
    }

}
