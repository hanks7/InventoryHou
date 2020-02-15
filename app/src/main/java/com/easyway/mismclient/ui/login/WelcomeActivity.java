package com.easyway.mismclient.ui.login;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyway.mismclient.MainActivity;
import com.easyway.mismclient.MainOfflineActivity;
import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseActivity;
import com.easyway.mismclient.dao.DbHelper;
import com.easyway.mismclient.utils.Uintent;
import com.easyway.mismclient.utils.permission.PermissionReq;
import com.easyway.mismclient.utils.permission.PermissionResultTask;
import com.easyway.mismclient.utils.permission.UPermissionsTool;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.easyway.mismclient.base.BaseOFFLineApplication.APP;


/**
 * @author 侯建军 47466436@qq.com
 * @date 2018/5/28
 * @description 欢迎界面
 */
public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.ac_welcome_iv_logo)
    ImageView mIvLogo;
    @BindView(R.id.ac_welcome_iv_company_name)
    TextView mTvCompanyName;

    int DURATION = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        getPermission();//获取权限

        PermissionReq.with(this)
                .permissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                .result(new PermissionResultTask(Manifest.permission.READ_EXTERNAL_STORAGE, this) {
                    @Override
                    public void onGranted() {
                        initDBhelper();//在Android设备的根目录下创建***.db文件
                    }
                })
                .request();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                intentActivity();
            }
        }, 1100);
        initAnimation();//初始化动画

    }

    /**
     * 初始化属性动画并执行
     */
    private void initAnimation() {
        float curTranslationY = mIvLogo.getTranslationY();
        ObjectAnimator OAtranslationY = ObjectAnimator.ofFloat(mIvLogo, "translationY", curTranslationY + 150f, curTranslationY);
        OAtranslationY.setDuration(DURATION);
        OAtranslationY.start();

        ObjectAnimator OAalpha = ObjectAnimator.ofFloat(mIvLogo, "alpha", 0.1f, 1f);
        OAalpha.setDuration(DURATION);
        OAalpha.start();

        float tVcurTranslationY2 = mTvCompanyName.getTranslationY();
        ObjectAnimator OAtVtranslationY = ObjectAnimator.ofFloat(mTvCompanyName, "translationY", tVcurTranslationY2 + 20, tVcurTranslationY2);
        OAtVtranslationY.setDuration(DURATION);
        OAtVtranslationY.start();

        ObjectAnimator OAtValpha = ObjectAnimator.ofFloat(mTvCompanyName, "alpha", 0.1f, 1f);
        OAtValpha.setDuration(DURATION);
        OAtValpha.start();

        ObjectAnimator OAtVscaleY = ObjectAnimator.ofFloat(mTvCompanyName, "scaleY", 0.1f, 1f);
        OAtVscaleY.setDuration(DURATION);
        OAtVscaleY.start();

        ObjectAnimator OAtVscaleX = ObjectAnimator.ofFloat(mTvCompanyName, "scaleX", 0.1f, 1f);
        OAtVscaleX.setDuration(DURATION);
        OAtVscaleX.start();

    }

    /**
     * 初始化数据库,在Android设备的根目录下创建***.db文件
     */
    private void initDBhelper() {
        DbHelper mDbHelper = new DbHelper(this);//创建一个空的数据库数据库
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        db.close();
    }
    /**
     * 获取权限
     */
    private void getPermission() {

        UPermissionsTool.
                getIntance(this).
                addPermission(Manifest.permission.READ_EXTERNAL_STORAGE).
                addPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE).
                addPermission(Manifest.permission.CAMERA).
                addPermission(Manifest.permission.READ_PHONE_STATE).
                initPermission();
    }

    /**
     * 跳转到下一个界面
     */
    private void intentActivity() {
        if (APP.userModel.isLogin()) {
            Uintent.intentDIYLeftToRight(this, MainActivity.class);
        } else if (APP.mUserOFLBean.isLogin()) {
            Uintent.intentDIYLeftToRight(this, MainOfflineActivity.class);
        } else {
            Uintent.intentDIYLeftToRight(this, LoginActivity.class);
        }
        finish();
    }

}

