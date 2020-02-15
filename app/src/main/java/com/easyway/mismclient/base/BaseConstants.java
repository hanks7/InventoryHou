package com.easyway.mismclient.base;

import com.easyway.mismclient.model.IPPortBean;
import com.easyway.mismclient.utils.Ugson;
import com.easyway.mismclient.utils.Ulog;
import com.easyway.mismclient.utils.Usp;

/**
 * Created by admin on 2018/4/8.
 */
public class BaseConstants {
    /**
     * 是否测试 1-测试 0-上线  并不是在这里改,是在assets/appConfig中修改isTest
     */
    public static final boolean IS_RELEASE = false;
    /**
     * ip端口号
     * eg: http://172.16.1.81:7000
     * 116.247.74.76:8682  319271  外网
     */
    public static String FORMAL_URL = getString();//ip_端口号
    /**
     * 此应用存放文件的文件夹名称
     */
    public static String APP_INDEX = "easyway";
    /**
     * 震动的时间
     */
    public static final int MILLISECONDS = 100;

    /**
     * 处理從本地获取IP端口号
     * "http://172.16.1.81:7000"
     *
     * @return
     */
    private static String getString() {
        String json = (String) Usp.get(IPPortBean.class.getSimpleName(), Ugson.toJson(new IPPortBean()));
        IPPortBean ipPortBean = Ugson.toBean(json.replace(" ", ""), IPPortBean.class);
        Ulog.i("getIPPort", ipPortBean.getIpPort().replace(" ", ""));
        return ipPortBean.getIpPort().replace(" ", "");
    }
}
