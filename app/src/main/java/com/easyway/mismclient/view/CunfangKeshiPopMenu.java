package com.easyway.mismclient.view;

/**
 * Created by Administrator on 2017/3/17.
 */

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.easyway.mismclient.R;
import com.easyway.mismclient.dao.DBManager;
import com.easyway.mismclient.model.MDepartmentBean;
import com.easyway.mismclient.ui.adapter.LsvPopSelectAdapter;

import java.util.List;

import static com.easyway.mismclient.utils.UTools.showAsDropDown2;


/**
 * 项目名称：translate
 * 实现功能：  翻译详情界面，分享弹出窗口
 * 类名称：PopWinMenu
 * 类描述：(该类的主要功能)
 * 创建人：徐纪伟
 * E-mail: xujiwei558@126.com
 * 创建时间：2014年10月18日 下午4:37:25
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class CunfangKeshiPopMenu<T> extends PopupWindow {
    private final EditText tvSelectKeshi;
    private ListView lsv;
    private LsvPopSelectAdapter adapter;
    private View contentView;
    private View onclickView;
    private View popView;
      List<MDepartmentBean.DetailsBean> list;

    /**
     * @param activity
     */
    public CunfangKeshiPopMenu(Activity activity,
                               final View viewArror,
                               View onclickView,
                               View popView,
                               LsvPopSelectAdapter adapter) {
        this.onclickView = onclickView;
        this.popView = popView;
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(activity.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.popwin_cun_fang_keshi, null);
        // 设置SelectPicPopupWindow的View
        this.setContentView(contentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationPreview2);
        lsv = ((ListView) contentView.findViewById(R.id.pop_lsv));
        tvSelectKeshi = ((EditText) contentView.findViewById(R.id.pop_tv_select_keshi));
        onclickView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewArror.animate().setDuration(600).rotation(-90).start();
                showPopupWindow();
            }
        });
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                viewArror.animate().setDuration(600).rotation(0).start();
            }
        });

        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupWindow();
            }
        });

        this.adapter = adapter;
        lsv.setAdapter(adapter);
    }


    public void setOnItemClickListener(final DBManager dbManager, final ItemClickListener itemClickListener) {

        tvSelectKeshi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (TextUtils.isEmpty(tvSelectKeshi.getText())) {
                    list=dbManager.getAllCities();
                    adapter.updateList(list);
                } else {
                    list=dbManager.searchCity(tvSelectKeshi.getText().toString().trim());
                    adapter.updateList(list);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        list=dbManager.getAllCities();
        adapter.updateList(list);

        lsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,  int position, long id) {
                adapter.setCheckItem(position);
                showPopupWindow();
                itemClickListener.itemClickListener(list.get(position));
            }
        });


    }


    /**
     * 显示popupWindow
     */
    public void showPopupWindow() {
        if (!this.isShowing()) {

            int x = 0;
            int y = 0;
            showAsDropDown2(this,popView, x, y);// 以下拉方式显示popupwindow
        } else {
            this.dismiss();
        }
    }

    public interface ItemClickListener {
        /**
         * 点击帅选选项时的业务
         *
         */
        void itemClickListener(MDepartmentBean.DetailsBean bean);
    }


}