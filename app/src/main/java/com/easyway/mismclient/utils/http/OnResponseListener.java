package com.easyway.mismclient.utils.http;

import android.text.TextUtils;

import com.easyway.mismclient.base.BaseModel;
import com.easyway.mismclient.base.DialogInterface;
import com.easyway.mismclient.utils.UToast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @date 2018/6/4 
 * @author 侯建军 47466436@qq.com
 * @description 请填写描述
 */
public abstract class OnResponseListener<T extends BaseModel> implements Callback<T> {


    DialogInterface infa;

    /**
     * @param infa
     */
    public OnResponseListener(DialogInterface infa) {
        this.infa = infa;
        show();
    }
    /**
     * dialog显示
     */
    public void show() {
        if (infa != null) {
            infa.showDialog();
        }
    }
    /**
     * dialog隐藏
     */
    public void dismiss() {
        if (infa != null) {
            infa.dismissDialog();
        }
    }




    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        dismiss();
        if (response.code() == 200) {
            switch (response.body().getCode()) {
                case 0:
                    onSuccess(response.body());
                    break;
                case 2:
                    onFailure2(response.body().getMessage() + "");
                    break;
                default:
                    onFailure(response.body().getMessage() + "");
            }
        } else {
            String strError = "";
            try {
                strError += response.errorBody().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            strError += response.code();
            onFailure(strError);
        }

    }


    @Override
    public void onFailure(Call<T> call, Throwable t) {
        dismiss();
        String tag = "网络异常";
        String msg = t.getMessage();
        if (!TextUtils.isEmpty(msg)) {
            tag = tag + ":" + msg;
        }
        onFailure(tag);
    }

    /**
     * 请求成功时返回，必须重写此方法
     *
     * @param t
     */
    public abstract void onSuccess(T t);

    /**
     * 请求失败时返回，需要时重写此方法(默认是弹出吐司)
     */
    public void onFailure(String strToast) {
        UToast.showText(strToast);
    }

    /**
     * code==2时返回的提示
     */
    public void onFailure2(String strToast) {
        UToast.showText(strToast);
    }

}