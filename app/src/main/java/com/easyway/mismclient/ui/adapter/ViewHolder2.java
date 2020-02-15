package com.easyway.mismclient.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.easyway.mismclient.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 侯建军 47466436@qq.com
 * @class com.easyway.mismclient.ui.adapter.ViewHolder
 * @time 2018/9/17 15:12
 * @description 请填写描述
 */
public class ViewHolder2 {

    @BindView(R.id.text)
    TextView mText;

    ViewHolder2(View view) {
        ButterKnife.bind(this, view);
    }


    public void fillValue(int position, int checkItemPosition, String content, Context context) {
        mText.setText(content);
        if (checkItemPosition == position) {
            mText.setTextColor(context.getResources().getColor(R.color.input_color));
            mText.setCompoundDrawablesWithIntrinsicBounds(null, null, context.getResources().getDrawable(R.drawable.ic_aaab), null);
        } else {
            mText.setTextColor(context.getResources().getColor(R.color.gray));
            mText.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }
}
