package com.google.zxing.common;

import android.util.Log;


/**
 * @author 侯建军
 * @data on 2018/1/4 10:40
 * @org www.hopshine.com
 * @function 请填写
 * @email 474664736@qq.com
 */
public class Ulog {
    public static final String TAG = "mc-";

    public static void i(Object mess) {

        Log.i(TAG + "", mess + "");
    }

    public static void i(Object tag, Object mess) {

        Log.i(TAG + tag, mess + "");
    }



}
