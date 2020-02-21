package com.easyway.mismclient.utils.photoview;

/**
 * Created by hanks7 on 2016/10/27.
 * 防止控件被重复点击
 *
 */
public class UtilCommon {
    private static long lastClickTime;

 /*
        if (UtilCommon.isFastDoubleClick()) { return; }
    */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
}
