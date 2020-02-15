package com.easyway.mismclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseMyAdapter;
import com.easyway.mismclient.model.TestBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 侯建军 47466436@qq.com
 * @class com.easyway.mismclient.ui.adapter.LsvBusinessBaseAdapter
 * @time 2018/9/17 15:57
 * @description 盘点封装的baseAdapter
 */
public class DialogGvAdapter extends BaseMyAdapter {

    public DialogGvAdapter(Context mContext, List list) {
        super(mContext, list);
    }

    @Override
    public int getCount() {
        return list.size() - 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_dialog_gv, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.fillValue(position, list);
        return convertView;
    }

    class ViewHolder {

        @BindView(R.id.item_dialog_gv_check_box)
        CheckBox checkBox;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void fillValue(final int position, final List list) {
            TestBean bean = (TestBean) list.get(position);
            checkBox.setText(bean.getText());
            checkBox.setChecked(bean.getIsSelected() == 1);
        }

    }
}
