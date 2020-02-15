package com.easyway.mismclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyway.mismclient.R;
import com.easyway.mismclient.model.LicenceListBean;

import java.util.List;

/**
 * Created by admin on 2018/4/17.
 * 注册证
 */
public class LsvPopRegisterNumAdapter extends LsvPopSelectAdapter {


    public LsvPopRegisterNumAdapter(Context mContext, List list) {
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
        viewHolder.fillValue(position, checkItemPosition, ((LicenceListBean) list.get(position)).getRegistrationCard(), mContext);
        return convertView;
    }

}
