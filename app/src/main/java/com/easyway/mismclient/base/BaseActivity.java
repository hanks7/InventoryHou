package com.easyway.mismclient.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;

import com.easyway.mismclient.utils.UStatusBar;
import com.easyway.mismclient.utils.Ulog;
import com.easyway.mismclient.utils.permission.PermissionReq;

import static com.easyway.mismclient.utils.UmengUtils.onPauseToActivity;
import static com.easyway.mismclient.utils.UmengUtils.onResumeToActivity;

public class BaseActivity extends FragmentActivity implements DialogInterface {
    public BaseActivity mActivity;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionReq.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setOnTranslucent();
        super.onCreate(savedInstanceState);
        mActivity = this;
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initDialog();
    }

    /**
     * 启用 透明状态栏(重写此方法可以取消透明)
     */
    protected void setOnTranslucent() {
        UStatusBar.enableTranslucentStatusbar(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onResumeToActivity(this);
        Ulog.i("activity", "(" + getClass().getSimpleName() + ".java:0)");

    }

    private ProgressDialog dialog;//显示等待的dialog

    private void initDialog() {
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//转盘
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("正在加载，请稍后……");

    }

    @Override
    public void showDialog() {
        if (dialog == null) return;
        dialog.show();
    }

    @Override
    public void dismissDialog() {
        if (dialog == null) return;
        dialog.dismiss();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //        BaseApplication.APP.refWatcher.watch(this);
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPauseToActivity(this);
    }

    @Override
    public void onBackPressed() {
        dealOnBackPressed();
        super.onBackPressed();
    }

    public void dealOnBackPressed() {
    }
}
