package com.easyway.mismclient.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * adapter
 */
public class BaseMyAdapter<T> extends BaseAdapter {

    public Context mContext;
    public List<T> list;

    public BaseMyAdapter(Context mContext, List<T> list) {
        this.mContext = mContext;
        if (list != null) {
            this.list = list;
        } else {
            this.list = new ArrayList();
        }
    }
    public void addAllData(List<T> listBean) {
        if (listBean == null) return;
        list.addAll(listBean);
        notifyDataSetChanged();
    }

    /**
     *
     * @param listBean
     */
    public void updateList(List<T> listBean) {
        if (listBean == null) return;
        list.clear();
        list.remove(list);
        list.addAll(listBean);
        notifyDataSetChanged();
    }

    public List<T> getList() {
        return list;
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

}
