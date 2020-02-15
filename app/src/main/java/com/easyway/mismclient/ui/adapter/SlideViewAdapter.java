package com.easyway.mismclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseMyAdapter;
import com.easyway.mismclient.model.MProdEnterDetailBean;
import com.easyway.mismclient.view.AmountView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author blank
 * @version V1.0
 * @time 下午4:01:07
 */
public class SlideViewAdapter extends BaseMyAdapter {


    private OnRemoveListener mRemoveListener;

    @Override
    public void updateList(List listBean) {
        if (listBean != null) {
            this.list = listBean;
        } else {
            this.list = new ArrayList();
        }
        notifyDataSetChanged();
    }

    public SlideViewAdapter(Context mContext, List list) {
        super(mContext, list);
    }


    public void setRemoveListener(OnRemoveListener removeListener) {
        this.mRemoveListener = removeListener;
    }

    final int TYPE_0 = 0;
    final int TYPE_1 = 1;


    /**
     * 根据数据列表的position返回需要展示的layout的对应的type
     * type的值必须从0开始
     */
    @Override
    public int getItemViewType(int position) {
        int isVoid = Integer.valueOf(((MProdEnterDetailBean.DetailsBean) list.get(position)).getIsVoid());
        //这里我们修改的是对应头item和底部item
        switch (isVoid) {
            case 0:
                return TYPE_0;
            case 1:
                return TYPE_1;
            default:
                return TYPE_0;

        }
    }

    /**
     * 该方法返回多少个不同的布局
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder  viewHolder=null;
        int type = getItemViewType(position);
        if (convertView == null) {
            // 按当前所需的样式，确定new的布局
            switch (type) {
                case TYPE_0:
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lsv_commit_all, null);
                    viewHolder = new ViewHolder(convertView);

                    convertView.setTag(viewHolder);
                    break;
                case TYPE_1:
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item_product_listview_null, null);
                    break;
                default:
                    break;
            }
        } else {
            switch (type) {
                case TYPE_0:
                    viewHolder = (ViewHolder) convertView.getTag();
                    break;
                case TYPE_1:
                    break;
            }
        }
        // 设置资源
        switch (type) {
            case TYPE_0:
                fillValue(position,viewHolder);
                break;
            case TYPE_1:
                break;
        }
        return convertView;
    }




    public interface OnRemoveListener {
        void onRemoveItem(int position);
    }

    static class ViewHolder {
        @BindView(R.id.tvDelete)
        TextView mDelete;

        @BindView(R.id.item_list_inventory_tv_supplier)
        TextView mTvSupplier;//供应商
        @BindView(R.id.item_list_real_time_amount)
        TextView mTvFactAmount;//
        @BindView(R.id.item_list_tv_amount_view)
        AmountView mTvAmountView;//

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 填充数据
     *
     * @param position
     * @param viewHolder
     */
    private void fillValue(final int position, final ViewHolder viewHolder) {
        final MProdEnterDetailBean.DetailsBean bean = (MProdEnterDetailBean.DetailsBean) list.get(position);
        viewHolder.mTvSupplier.setText(bean.getProductInfoName());
        viewHolder.mTvFactAmount.setText(bean.getSpecification());

        viewHolder.mTvAmountView.setText((int) bean.getFactAmount(), position);
        viewHolder.mTvAmountView.setClick(true);

        viewHolder.mTvAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                if (viewHolder.mTvAmountView.getPosition() == position) {
                    ((MProdEnterDetailBean.DetailsBean) list.get(position)).setFactAmount(amount);
                }
            }
        });

        viewHolder.mDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRemoveListener != null) {
                    mRemoveListener.onRemoveItem(position);
                }
                ((MProdEnterDetailBean.DetailsBean) list.get(position)).setIsVoid("1");
                notifyDataSetChanged();
            }
        });


    }

}
