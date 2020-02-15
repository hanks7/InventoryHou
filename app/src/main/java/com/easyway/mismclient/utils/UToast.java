package com.easyway.mismclient.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.easyway.mismclient.R;

import static com.easyway.mismclient.base.BaseOFFLineApplication.APP;


/**
 * @author 侯建军
 * @data on 2018/1/4 11:03
 * @org www.hopshine.com
 * @function 请填写
 * @email 474664736@qq.com
 */
public class UToast {


    /**
     * 构造方法私有化 不允许new对象
     */
    private UToast() {
    }

    /**
     * Toast对象
     */
    private static Toast toast = null;

    /**
     * 显示Toast
     */
    public static void showText(Object strToast) {
        Ulog.i("toast", strToast);
        if (toast == null) {
            toast = Toast.makeText(APP, "", Toast.LENGTH_SHORT);
        }
        toast.setText(strToast + "");
        toast.show();
    }

    /**
     * 显示snackBar
     */
    public static void showSnackBar(View view, String message, int backgroundColor, int textColor) {
        Ulog.i("showSnackBar", message);
        Snackbar sb = Snackbar.make(
                view,
                message,
                Snackbar.LENGTH_SHORT
        );
        View snackBarView = sb.getView();
        ViewGroup.LayoutParams lp;
        lp = snackBarView.getLayoutParams();
        lp.height = UtilPixelTransfrom.dip2px(view.getContext(), 70);
        snackBarView.setLayoutParams(lp);
        sb.getView().setBackgroundColor(APP.getResources().getColor(backgroundColor));
        ((TextView) (sb.getView()).findViewById(R.id.snackbar_text)).setTextColor(APP.getResources().getColor(textColor));
        sb.show();
    }

    /**
     *  有确定和取消按钮的dialog
     * @param mContext
     * @param content
     * 需要提示的內容
     * @param isCancelable
     * 点击返回是否可以取消
     * @param inf
     * 確定取消的接口,確定的抽象方法必須重寫
     */
    public static void showDialog3(Context mContext, CharSequence content,String strOk, boolean isCancelable, final DialogInf inf) {
        LayoutInflater inflaterDl = LayoutInflater.from(mContext);
        View view = inflaterDl.inflate(R.layout.item_dialog_permission_notice, null);
        final AlertDialog dialog = new AlertDialog.Builder(mContext, R.style.dialog_custom_style).create();
        dialog.setCancelable(false);
        dialog.show();
        dialog.getWindow().setContentView(view);

        View btnCancel = view.findViewById(R.id.btn_cancel);
        View btnCommit = view.findViewById(R.id.btn_ok);
        TextView tvOk = (TextView) view.findViewById(R.id.tv_ok);
        TextView tvMessage = (TextView) view.findViewById(R.id.tv_message);
        tvMessage.setText(content+"");
        tvOk.setText(strOk+"");
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                inf.cancel();
            }
        });
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                inf.commit();
            }
        });
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.setCancelable(isCancelable);
    }
    /**
     * 弹出设置dialog
     * @param mContext
     * @param content
     * 需要提示的內容
     * @param isCancelable
     * 点击返回是否可以取消
     * @param inf
     * 確定取消的接口,確定的抽象方法必須重寫
     */
    public static void showDialog(Context mContext, CharSequence content, boolean isCancelable, final DialogInf inf) {
      showDialog3(mContext,content,mContext.getString(R.string.confirm),isCancelable,inf);
    }

    /**
     * 弹出设置dialog 有确定,没取消按钮,但可以取消的
     * @param mContext
     * @param content
     * 需要提示的內容
     * @param isCancelable
     * 点击返回是否可以取消
     * @param inf
     * 確定取消的接口,確定的抽象方法必須重寫
     */
    public static void showDialog2(Context mContext, CharSequence content, boolean isCancelable, final DialogInf inf) {
        LayoutInflater inflaterDl = LayoutInflater.from(mContext);
        View view = inflaterDl.inflate(R.layout.item_dialog_note, null);
        final AlertDialog mNotedialog = new AlertDialog.Builder(mContext, R.style.dialog_custom_style).create();
        mNotedialog.setCancelable(false);
        mNotedialog.show();
        mNotedialog.getWindow().setContentView(view);

        View btnCommit = view.findViewById(R.id.btn_ok);
        TextView tvMessage = (TextView) view.findViewById(R.id.tv_message);

        tvMessage.setText(content);

        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNotedialog.dismiss();
                if (inf != null) {
                    inf.commit();
                }

            }
        });
        mNotedialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        mNotedialog.setCancelable(isCancelable);
    }


}
