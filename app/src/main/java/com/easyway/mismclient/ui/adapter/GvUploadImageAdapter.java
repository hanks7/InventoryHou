package com.easyway.mismclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseMyAdapter;
import com.easyway.mismclient.model.UploadImageBean;
import com.easyway.mismclient.utils.glide.UtilGlide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 侯建军 QQ:474664736
 * @time 2019/11/28 10:37
 * @class describe
 */
public class GvUploadImageAdapter extends BaseMyAdapter<UploadImageBean> {

    public GvUploadImageAdapter(Context mContext, List list) {
        super(mContext, list);
    }

    @Override
    public void addAllData(List listBean) {
        list.addAll(listBean);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list.size() + 1;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        GvViewHolder viewHolder = null;

        if (position == getCount() - 1) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_gv2, null);
            if (getList().size()==9) convertView.setVisibility(View.GONE);
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_gv, null);
            viewHolder = new GvViewHolder(convertView);
        }


        if (position < getCount() - 1 && getList().size() != 0) {
            final UploadImageBean bean = list.get(position);
            viewHolder.gvIvClear.setVisibility(View.VISIBLE);
            UtilGlide.loadImgNomal(mContext, bean.getPath(), viewHolder.gvIv);

            viewHolder.gvIvClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });
        }



        return convertView;
    }

    static class GvViewHolder {

        @BindView(R.id.gv_iv)
        ImageView gvIv;
        @BindView(R.id.gv_iv_clear)
        ImageView gvIvClear;

        GvViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
