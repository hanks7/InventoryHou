package com.easyway.mismclient.ui.adapter;

import android.view.View;
import android.widget.TextView;

import com.easyway.mismclient.R;
import com.easyway.mismclient.model.DetailListBean;
import com.easyway.mismclient.view.AmountView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 侯建军 47466436@qq.com
 * @class com.easyway.mismclient.ui.adapter.ViewHolder
 * @time 2018/9/17 15:12
 * @description 请填写描述
 */
public class ViewHolder {
    @BindView(R.id.item_list_inventory_tv_supplier)
    TextView mTvSupplier;//供应商
    @BindView(R.id.item_list_real_time_amount)
    TextView mTvRealTimeAmount;//账面数量
    @BindView(R.id.item_list_tv_amount_view)
    AmountView mTvAmountView;//实际数量
    @BindView(R.id.item_list_ll_realtime_amount)
    View mLlRealTimeAmount;//实际数量
    @BindView(R.id.item_list_tv_fact_amount_tag)
    TextView mTvFactAmountTag;//

    ViewHolder(View view) {
        ButterKnife.bind(this, view);
    }

    public void fillValue(final int position,  final List list, boolean isClick) {

        final DetailListBean bean = (DetailListBean) list.get(position);
        mTvSupplier.setText(bean.getSupplierName());
        mTvRealTimeAmount.setText(bean.getRealTimeAmount() + "");
        mTvAmountView.setText(bean.getFactAmount(), position);
        mTvAmountView.setClick(isClick);
        mTvAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                if (mTvAmountView.getPosition() == position) {
                    ((DetailListBean) list.get(position)).setFactAmount(amount);
                }

            }
        });
    }
}
