package com.easyway.mismclient.base;

import com.easyway.mismclient.dao.DbHelper;
import com.easyway.mismclient.model.UserOFLBean;

/**
 * 离线的application
 */
public class BaseOFFLineApplication extends BaseApplication {
    public UserOFLBean mUserOFLBean;
    public static BaseOFFLineApplication APP;


    @Override
    public void onCreate() {
        APP = this;
        super.onCreate();

        getUserModel2();
    }


    /**
     * 从sharedperence取出个人信息存到内存中
     */
    public UserOFLBean getUserModel2() {

        DbHelper mDbHelper = null;//创建一个空的数据库数据库
        try {
            mDbHelper = new DbHelper(this);
            mUserOFLBean = mDbHelper.queryEmployee();
        } catch (Exception e) {
            e.printStackTrace();
            mUserOFLBean = new UserOFLBean();
        }

        return mUserOFLBean;
    }

    /**
     * 退出登录,清除个人信息
     */
    public void quitLogin2() {
        if (mUserOFLBean != null) {
            DbHelper mDbHelper = new DbHelper(this);//创建一个空的数据库数据库
            mUserOFLBean.setLogin(false);
            mDbHelper.updateEmployee(mUserOFLBean);
            getUserModel2();
        }

    }


}
