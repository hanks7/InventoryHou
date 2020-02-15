package com.easyway.mismclient.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Created by admin on 2018/4/11.
 */

public class Base64Utils {
    // 加密
    public static String setBase64(String str) {
        String result = "";
        if( str != null) {
            try {
                result = new String(Base64.encode(str.getBytes("utf-8"), Base64.NO_WRAP),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        result = result.replace("+", "_").replace("/", "-");
        return result;
    }

    // 解密
    public static String getFromBase64(String str) {
        String result = "";
        if (str != null) {
            try {
                result = new String(Base64.decode(str, Base64.NO_WRAP), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
