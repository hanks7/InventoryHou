package com.easyway.mismclient.ui.adapter;

import android.content.Context;

import com.easyway.mismclient.base.BaseMyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/4/17.
 */
public class LsvPopSelectAdapter extends BaseMyAdapter {
    public LsvPopSelectAdapter(Context mContext, List list) {
        super(mContext, list);
    }

  /*  public void updateList(List<Data> listBean) {
        if (listBean == null) return;
        notifyDataSetChanged();
    }*/

    @Override
    public void updateList(List listBean) {
        if (listBean != null) {
            this.list = listBean;
        } else {
            this.list = new ArrayList();
        }
        notifyDataSetChanged();
    }

    public int checkItemPosition = -1;

    public void setCheckItem(int position) {
        checkItemPosition = position;
        notifyDataSetChanged();
    }


}
