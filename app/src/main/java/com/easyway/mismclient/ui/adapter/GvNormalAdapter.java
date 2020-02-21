package com.easyway.mismclient.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseMyAdapter;
import com.easyway.mismclient.utils.glide.UtilGlide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 侯建军 QQ:474664736
 * @time 2019/11/28 10:37
 * @class describe
 */
public class GvNormalAdapter extends BaseMyAdapter<String> {

    public GvNormalAdapter(Context mContext, List list) {
        super(mContext, list);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        GvViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_gv, null);
            viewHolder = new GvViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GvViewHolder) convertView.getTag();
        }


        final String picUrl = list.get(position);
        viewHolder.gvIvClear.setVisibility(View.GONE);
        UtilGlide.loadImgNomal(mContext, picUrl, viewHolder.gvIv);

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
