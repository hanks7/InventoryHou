package com.easyway.mismclient.utils.http;

import android.content.Context;
import android.os.AsyncTask;

import com.easyway.mismclient.base.BaseModel;
import com.easyway.mismclient.view.LoadingDialog;

/**
 * @author 侯建军 47466436@qq.com
 * @class com.easyway.mismclient.utils.http.MyAsyncTask
 * @time 2018/10/22 14:17
 * @description 请填写描述
 */
public abstract class MyAsyncTask<T extends BaseModel> extends AsyncTask<Void, Void, T> {
    /**
     * 网络请求等待的dialog
     */
    LoadingDialog dialog;
    ImpDBSuccessFailListener listener;

    /**
     * 传入为null就不会显示dialog
     *
     * @param context
     */
    public MyAsyncTask(Context context,ImpDBSuccessFailListener listener) {
        if (context != null) {
            dialog = new LoadingDialog(context);
            this.listener=listener;
        }
    }


    /**
     * dialog隐藏
     */
    public void dismiss() {
        if (dialog == null) return;
        try {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * dialog.show()
     */
    public void show() {
        if (dialog == null) return;
        try {
            if (!dialog.isShowing()) {
                dialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        show();
    }


    @Override
    protected void onPostExecute(T bean) {
        super.onPostExecute(bean);
        dismiss();
        if (bean.getCode() == 0) {
            listener.onSuccess(bean);
        }else{
            listener.onFail(bean.getMessage());
        }
    }

}
