package com.easyway.mismclient.view;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseMyAdapter;
import com.easyway.mismclient.model.TestBean;
import com.easyway.mismclient.ui.adapter.DialogGvAdapter;
import com.easyway.mismclient.ui.adapter.LsvDialogInventoryAdapter;
import com.easyway.mismclient.utils.DialogInf;
import com.easyway.mismclient.utils.UToast;

import java.util.List;

/**
 * @author 侯建军 47466436@qq.com
 * @class com.easyway.mismclient.view.MyDialog
 * @time 2019/1/14 10:37
 * @description 请填写描述
 */
public class MyDialog {

    /**
     * @param mContext
     * @param isCancelable
     * @param list
     */
    public static void show(Context mContext, boolean isCancelable, final List<String> list) {
        LayoutInflater inflaterDl = LayoutInflater.from(mContext);
        View view = inflaterDl.inflate(R.layout.item_inventory_list_view, null);
        final AlertDialog dialog = new AlertDialog.Builder(mContext, R.style.dialog_custom_style).create();
        dialog.setCancelable(false);
        dialog.show();
        dialog.getWindow().setContentView(view);

        ListView lsv = (ListView) view.findViewById(R.id.item_inventory_dialog_lsv);
        BaseMyAdapter adapter = new LsvDialogInventoryAdapter(mContext, null);
        adapter.updateList(list);
        lsv.setAdapter(adapter);
        setListViewHeight(lsv, 5);
        lsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UToast.showText(list.get(position));
                dialog.dismiss();
            }
        });
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.setCancelable(isCancelable);
    }


    /**
     * @param mContext
     * @param list
     */
    public static void showSelectPhotoOptions(Context mContext, final List<TestBean> list, final DialogInf inf) {
        LayoutInflater inflaterDl = LayoutInflater.from(mContext);
        View view = inflaterDl.inflate(R.layout.item_dialog_photo_options, null);
        final AlertDialog dialog = new AlertDialog.Builder(mContext, R.style.dialog_custom_style).create();
        dialog.setCancelable(false);
        dialog.show();
        dialog.getWindow().setContentView(view);

        GridView gv = view.findViewById(R.id.item_dialog_photo_options_gv);
        View btnCancel = view.findViewById(R.id.btn_cancel);
        View btnCommit = view.findViewById(R.id.btn_ok);

        DialogGvAdapter adapter = new DialogGvAdapter(mContext, list);

        gv.setAdapter(adapter);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inf.commit();
                dialog.dismiss();
            }
        });
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
    }

    /**
     * 限制listView 的最大高度
     *
     * @param listView
     */
    private static void setListViewHeight(ListView listView, int maxItemCount) {
        ListAdapter listAdapter = listView.getAdapter(); //得到ListView 添加的适配器
        if (listAdapter == null) {
            return;
        }

        View itemView = listAdapter.getView(0, null, listView); //获取其中的一项
        //进行这一项的测量，为什么加这一步，具体分析可以参考 https://www.jianshu.com/p/dbd6afb2c890这篇文章
        itemView.measure(0, 0);
        int itemHeight = itemView.getMeasuredHeight(); //一项的高度
        int itemCount = listAdapter.getCount();//得到总的项数
        LinearLayout.LayoutParams layoutParams = null; //进行布局参数的设置
        if (itemCount <= maxItemCount) {
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight * itemCount);
        } else if (itemCount > maxItemCount) {
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight * maxItemCount);
        }
        listView.setLayoutParams(layoutParams);
    }


}
