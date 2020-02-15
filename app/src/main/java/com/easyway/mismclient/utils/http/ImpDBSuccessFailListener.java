package com.easyway.mismclient.utils.http;

import com.easyway.mismclient.utils.UToast;

/**
 * @author 侯建军 47466436@qq.com
 * @class com.easyway.mismclient.utils.http.OnDBSuccessFailListener
 * @time 2018/7/24 13:45
 * @description 请填写描述
 */
public abstract class ImpDBSuccessFailListener<T> implements OnDBSuccessFailListener {


    public abstract void onSuccess(T t);

    @Override
    public void onFail(String strToast) {
        UToast.showText(strToast);
    }

}
