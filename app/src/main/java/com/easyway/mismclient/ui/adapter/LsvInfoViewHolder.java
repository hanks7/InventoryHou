package com.easyway.mismclient.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.easyway.mismclient.R;
import com.easyway.mismclient.model.DetailListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 侯建军 47466436@qq.com
 * @class com.easyway.mismclient.ui.adapter.ViewHolder
 * @time 2018/9/17 15:12
 * @description 请填写描述
 */
public class LsvInfoViewHolder {

    @BindView(R.id.item_list_inventory_tv_supplier)
    TextView mTvSupplier;//供应商


    LsvInfoViewHolder(View view) {
        ButterKnife.bind(this, view);
    }

    public void fillValue(final int position, final List list) {

        final DetailListBean bean = (DetailListBean) list.get(position);
        mTvSupplier.setText(bean.getSupplierName());

    }
}
