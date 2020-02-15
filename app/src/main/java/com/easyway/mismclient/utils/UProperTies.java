package com.easyway.mismclient.utils;

import android.content.Context;

import java.io.InputStream;
import java.util.Properties;

import static com.easyway.mismclient.base.BaseOFFLineApplication.APP;

/**
 * @author 侯建军 47466436@qq.com
 * @class com.easyway.mismclient.utils.UProperTies
 * @time 2018/9/28 14:54
 * @description 配置文件和读配置文件
 */
public class UProperTies {
    /**
     * @param context
     * @return
     */
    private static Properties getProperties(Context context) {
        Properties props = new Properties();
        try {
            InputStream in = context.getAssets().open("appConfig");
            props.load(in);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return props;
    }

    /**
     * 根据key值得到配置文件内容(全是String类型)
     * @param strKey
     * @return
     */
    public static String getPropString(String strKey) {
        String content=getProperties(APP).getProperty(strKey);
        return content==null?"":content;
    }


}
