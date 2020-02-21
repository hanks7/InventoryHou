package com.easyway.mismclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseMyAdapter;
import com.easyway.mismclient.model.WarehouseDetailList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 盘点页下的-(领用)-(还库)的adapter
 */
public class LsvPushManageBottomAdapter extends BaseMyAdapter<WarehouseDetailList> {

    public LsvPushManageBottomAdapter(Context mContext, List list) {
        super(mContext, list);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_push_manage_detail, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.fillValue(position, list.get(position));
        return convertView;
    }


    public static class ViewHolder {

        @BindView(R.id.item_lsv_push_manage_tv1)
        TextView vhTv1;
        @BindView(R.id.item_lsv_push_manage_tv2)
        TextView vhTv2;
        @BindView(R.id.item_lsv_push_manage_tv3)
        TextView vhTv3;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void fillValue(final int position, WarehouseDetailList bean ) {
            vhTv1.setText(bean.getProductName()+"");
            vhTv2.setText(bean.getSupplierName()+"");
            vhTv3.setText(bean.getAmount()+"");


        }
    }


}

