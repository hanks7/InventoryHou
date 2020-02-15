package com.easyway.mismclient.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyway.mismclient.R;
import com.easyway.mismclient.model.TestBean;
import com.easyway.mismclient.utils.glide.UtilGlide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author 侯建军 47466436@qq.com
 * @class com.easyway.mismclient.ui.adapter.ViewHolder
 * @time 2018/9/17 15:12
 * @description 请填写描述
 */
public class GvViewHolder {

    @BindView(R.id.gv_iv)
    ImageView gvIv;
    @BindView(R.id.gv_tv)
    TextView gvTv;

    GvViewHolder(View view) {
        ButterKnife.bind(this, view);
    }

    public void fillValue(Context context, final int position, final List list) {

        final TestBean bean = (TestBean) list.get(position);

        if (list.size() - 1 == position) {
            gvIv.setImageDrawable(context.getResources().getDrawable(R.mipmap.icon_addpic_focused));
            gvTv.setText("添加");
        } else {
            if(TextUtils.isEmpty(bean.getPath())){
                gvIv.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_add_photo));
            }else{
                UtilGlide.loadImgNomal(context, bean.getPath(), gvIv);
            }
            gvTv.setText(bean.getText());
        }


    }
}
