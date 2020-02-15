package com.easyway.mismclient.base;

import android.app.Application;

import com.easyway.mismclient.model.UserBean;
import com.easyway.mismclient.utils.Ugson;
import com.easyway.mismclient.utils.Usp;

import static com.easyway.mismclient.utils.UmengUtils.initUmeng;

/**
 * 密钥库类型: JKS
 * 密钥库提供方: SUN
 *
 * 您的密钥库包含 1 个条目
 *
 * 别名: mismclient
 * 创建日期: 2018-4-13
 * 条目类型: PrivateKeyEntry
 * 证书链长度: 1
 * 证书[1]:
 * 所有者: CN=easyway, OU=easyway, O=easyway, L=sh, ST=sh, C=086
 * 发布者: CN=easyway, OU=easyway, O=easyway, L=sh, ST=sh, C=086
 * 序列号: 28a33257
 * 有效期开始日期: Fri Apr 13 14:28:16 CST 2018, 截止日期: Tue Apr 07 14:28:16 CST 2043
 * 证书指纹:
 *          MD5: 0C:93:04:5D:BF:97:45:85:DD:6A:EC:62:7B:0B:13:6B
 *          SHA1: 6F:27:F4:93:2D:5A:45:2A:3D:E3:AA:9E:35:CD:38:D6:81:9B:81:26
 *          SHA256: B4:69:22:1A:8A:A1:17:8A:9A:62:8C:1F:7E:A2:02:5E:ED:EB:99:9A:72:74:6A:0B:88:2E:F2:F9:DA:0B:12:68
 *          签名算法名称: SHA256withRSA
 *          版本: 3
 *
 * 扩展:
 *
 * #1: ObjectId: 2.5.29.14 Criticality=false
 * SubjectKeyIdentifier [
 * KeyIdentifier [
 * 0000: 86 2E 7A 53 14 C5 B6 0A   1E 8D 9C BF A4 69 BD FD  ..zS.........i..
 * 0010: 56 C3 24 23                                        V.$#
 * ]
 * ]
 *
 *
 *
 * *******************************************
 * *******************************************
 */
public class BaseApplication extends Application {


    public static final String USER_MODEL = "UserModel";
    public UserBean userModel;

    @Override
    public void onCreate() {
        super.onCreate();
        initUmeng();
        getUserModel();
    }

    /**
     * 将个人信息存到sharedperence和内存中
     *
     * @param userModel
     */
    public void setUserModel(UserBean userModel) {
        userModel.setLogin(true);
        this.userModel = userModel;
        Usp.put("UserModel", Ugson.toJson(userModel));
    }

    /**
     * 从sharedperence取出个人信息存到内存中
     */
    public UserBean getUserModel() {
        String json = (String) Usp.get(USER_MODEL, Ugson.toJson(new UserBean()));
        userModel = Ugson.toBean(json, UserBean.class);
        return userModel;
    }

    /**
     * 退出登录,清除个人信息
     */
    public void quitLogin() {
        Usp.remove(getApplicationContext(), "UserModel");
        getUserModel();
    }


}
