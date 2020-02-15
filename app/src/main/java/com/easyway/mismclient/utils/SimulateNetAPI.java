package com.easyway.mismclient.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * @author 侯建军 47466436@qq.com
 * @class com.easyway.mdsmclient.utils.SimulateNetAPI
 * @time 2019/4/19 17:11
 * @description 请填写描述
 */
public class SimulateNetAPI {
    /**
     * 获取去最原始的数据信息
     *
     * @return json data
     */
    public static String getOriginalFundData(Context context, String jsonName) {
        InputStream input = null;
        try {
            //taipingyang.json文件名称
            input = context.getAssets().open(jsonName);
            String json = convertStreamToString(input);
            Ulog.i("getOriginalFundData", json);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * input 流转换为字符串
     *
     * @param is
     * @return
     */
    private static String convertStreamToString(InputStream is) {
        String s = null;
        try {
            //格式转换
            Scanner scanner = new Scanner(is, "UTF-8").useDelimiter("\\A");
            if (scanner.hasNext()) {
                s = scanner.next();
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
