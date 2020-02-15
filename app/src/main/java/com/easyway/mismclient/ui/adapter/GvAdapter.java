package com.easyway.mismclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseMyAdapter;
import com.easyway.mismclient.model.TestBean;

import java.util.List;

/**
 * @author 侯建军 47466436@qq.com
 * @class com.easyway.mismclient.ui.adapter.LsvBusinessBaseAdapter
 * @time 2018/9/17 15:57
 * @description 盘点封装的baseAdapter
 */
public class GvAdapter extends BaseMyAdapter {

    public GvAdapter(Context mContext, List list) {
        super(mContext, list);
    }

    @Override
    public void addAllData(List listBean) {
        if (listBean == null) return;
        if (list.size() >= 4) {
            list.remove(list.size() - 1);
        }
        list.addAll(listBean);
        list.add(new TestBean("", 2));//添加最有一个添加按钮,通过它是空的属性判断他是一个添加按钮
        notifyDataSetChanged();
    }

    public void addBean(String path, String text) {
        if (list.size() >= 4) {
            list.remove(list.size() - 1);
        }
        TestBean bean = new TestBean(path, text, 1);
        list.add(bean);

        list.add(new TestBean("", 2));//添加最有一个添加按钮,通过它是空的属性判断他是一个添加按钮
    }

    @Override
    public void updateList(List listBean) {
        if (listBean == null) return;
        list.clear();
        list.remove(list);

        list.addAll(listBean);
        list.add(new TestBean("", 2));//添加最有一个添加按钮,通过它是空的属性判断他是一个添加按钮
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GvViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (GvViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_gv, null);
            viewHolder = new GvViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.fillValue(mContext, position, list);
        return convertView;
    }


}
