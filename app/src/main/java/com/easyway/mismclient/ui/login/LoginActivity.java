package com.easyway.mismclient.ui.login;

import android.Manifest;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.easyway.mismclient.MainActivity;
import com.easyway.mismclient.MainOfflineActivity;
import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseActivity;
import com.easyway.mismclient.base.BaseConstants;
import com.easyway.mismclient.dao.DBIpPortManager;
import com.easyway.mismclient.dao.DbHelper;
import com.easyway.mismclient.model.UserBean;
import com.easyway.mismclient.model.UserOFLBean;
import com.easyway.mismclient.utils.UToast;
import com.easyway.mismclient.utils.UTools;
import com.easyway.mismclient.utils.Uintent;
import com.easyway.mismclient.utils.Ulog;
import com.easyway.mismclient.utils.UtilSystem;
import com.easyway.mismclient.utils.http.HttpAdapter;
import com.easyway.mismclient.utils.http.ImpDBSuccessFailListener;
import com.easyway.mismclient.utils.http.OnResponseListener;
import com.easyway.mismclient.utils.permission.PermissionReq;
import com.easyway.mismclient.utils.permission.PermissionResultTask;
import com.easyway.mismclient.utils.permission.UPermissionsTool;
import com.easyway.mismclient.view.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.HttpUrl;

import static com.easyway.mismclient.base.BaseConstants.IS_RELEASE;
import static com.easyway.mismclient.base.BaseOFFLineApplication.APP;
import static com.easyway.mismclient.dao.DBIpPortManager.tb_login_account;
import static com.easyway.mismclient.utils.UProperTies.getPropString;
import static com.easyway.mismclient.utils.UTools.netUpdate;
import static com.easyway.mismclient.utils.UmengUtils.onLogin;

/**
 * @author 侯建军 47466436@qq.com
 * @date 2018/5/28
 * @description 登陆界面
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_edt_username)
    AutoCompleteTextView edtUsername;
    @BindView(R.id.login_edt_pwd)
    AutoCompleteTextView edtPassword;
    @BindView(R.id.login_check_box_username)
    CheckBox mCheckBox;

    @BindView(R.id.login_tv_version)
    TextView tvVersion;
    @BindView(R.id.topbar)
    TopBar topbar;
    boolean isOffline;

    private Dialog mLoginingDlg; // 显示正在登录的Dialog
    private DBIpPortManager db;
    DbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initOff();

        initLoginingDlg();//初始化正在登录对话框
        initView();//初始化控件
        getPermission();//6.0之上设置权限
        test();//上线后删除

        initAdapter();
    }

    private void initOff() {
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LoginActivity.this.isOffline = isChecked;
                test();
            }
        });
        mDbHelper = new DbHelper(this);//创建一个空的数据库数据库
    }

    @OnClick({R.id.userlogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.userlogin:
                showDilog();
                if (edtUsername.getText().toString().trim().equals("")) {
                    UToast.showText("账户名不能为空");
                    showDilog();
                    return;
                }
                if (edtPassword.getText().toString().trim().equals("")) {
                    UToast.showText("密码不能为空");
                    showDilog();
                    return;
                }

                if (isOffline) {//离线
                    PermissionReq.with(this)
                            .permissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .result(new PermissionResultTask(Manifest.permission.READ_EXTERNAL_STORAGE, this) {
                                @Override
                                public void onGranted() {
                                    netOfflineWork();//离线登录的业务
                                }
                            })
                            .request();
                } else {
                    netOnlineWork();//在线登陆的业务
                }


                break;
        }
    }

    /**
     * 离线登录的业务
     */
    private void netOfflineWork() {
        showDilog();
        mDbHelper.queryEmployee(edtUsername.getText().toString().trim(), edtPassword.getText().toString().trim(),
                new ImpDBSuccessFailListener<UserOFLBean>() {
                    @Override
                    public void onSuccess(UserOFLBean bean) {
                        APP.mUserOFLBean = bean;
                        db.insert(edtUsername.getText().toString().trim(), tb_login_account);
                        Uintent.intentDIY(mActivity, MainOfflineActivity.class);
                        finish();
                    }

                    @Override
                    public void onFail(String strToast) {
                        UToast.showSnackBar(topbar, strToast, R.color.red, R.color.white);
                    }
                });
    }

    /**
     * 在线登陆的业务
     */
    private void netOnlineWork() {
        try {
            HttpUrl baseURL = HttpUrl.parse(BaseConstants.FORMAL_URL);
            Ulog.i("ipPortBean.getIpPort()", BaseConstants.FORMAL_URL);
            //重建新的HttpUrl，需要重新设置的url部分
            baseURL.newBuilder()
                    .scheme(baseURL.scheme())//http协议如：http或者https
                    .host(baseURL.host())//主机地址
                    .port(baseURL.port())//端口
                    .build();
        } catch (Exception e) {
            UToast.showText("您输入的ip端口不符合规范(请不要输入中文\":\"符号");
            showDilog();
            return;
        }

        HttpAdapter.getService().login(
                edtUsername.getText().toString().trim(),
                edtPassword.getText().toString().trim(),
                UTools.getIPPortBean().getHosID(),
                UtilSystem.getVersionCode() + "")
                .enqueue(new OnResponseListener<UserBean>(null) {
                    @Override
                    public void onSuccess(UserBean bean) {
                        showDilog();
                        bean.setHospitalID(Integer.valueOf(UTools.getIPPortBean().getHosID()));
                        APP.setUserModel(bean);
                        db.insert(edtUsername.getText().toString().trim(), tb_login_account);
                        intentMain();

                    }

                    @Override
                    public void onFailure(String strToast) {
                        super.onFailure(strToast);
                        showDilog();
                    }

                    @Override
                    public void onFailure2(String strToast) {
                        super.onFailure2(strToast);
                        showDilog();
                        netUpdate(mActivity, false, false);//检查升级
                    }
                });
    }

    /**
     * 初始化控件
     */
    private void initView() {
        topbar.setIvRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uintent.intentDIYLeftToRight(LoginActivity.this, ChangeIPActivity.class);
            }
        });
//        topbar.setTvLeft("登录类型", new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Uintent.intentDIY(mActivity, SelectTypeLoginActivity.class);
//                finish();
//
//            }
//        });
        String strVersion = String.format("版本号: %s  版本名称: %s", UtilSystem.getVersionCode(), UtilSystem.getVersionName());
        tvVersion.setText(strVersion);
    }

    /**
     * 初始化正在登录对话框
     */
    private void initLoginingDlg() {

        mLoginingDlg = new Dialog(this, R.style.loginingDlg);
        mLoginingDlg.setContentView(R.layout.logining_dlg);

        Window window = mLoginingDlg.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        // 获取和mLoginingDlg关联的当前窗口的属性，从而设置它在屏幕中显示的位置

        // 获取屏幕的高宽
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int cxScreen = dm.widthPixels;
        int cyScreen = dm.heightPixels;

        int height = (int) getResources().getDimension(R.dimen.loginingdlg_height);// 高42dp
        int lrMargin = (int) getResources().getDimension(
                R.dimen.loginingdlg_lr_margin); // 左右边沿10dp
        int topMargin = (int) getResources().getDimension(
                R.dimen.loginingdlg_top_margin); // 上沿20dp

        params.y = (-(cyScreen - height) / 2) + topMargin; // -199
        /* 对话框默认位置在屏幕中心,所以x,y表示此控件到"屏幕中心"的偏移量 */

        params.width = cxScreen;
        params.height = height;
        // width,height表示mLoginingDlg的实际大小

        mLoginingDlg.setCanceledOnTouchOutside(true); // 设置点击Dialog外部任意区域关闭Dialog
    }

    /**
     * 跳转到主界面
     */
    private void intentMain() {
        onLogin(APP.getUserModel().getHRCode());
        Uintent.intentDIYLeftToRight(this, MainActivity.class);
        finish();
    }

    /**
     * 请求用户获取所有权限
     */
    private void getPermission() {
        UPermissionsTool.
                getIntance(this).
                addPermission(Manifest.permission.ACCESS_FINE_LOCATION).
                addPermission(Manifest.permission.ACCESS_COARSE_LOCATION).
                addPermission(Manifest.permission.READ_EXTERNAL_STORAGE).
                addPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE).
                addPermission(Manifest.permission.CAMERA).
                addPermission(Manifest.permission.CALL_PHONE).
                addPermission(Manifest.permission.READ_PHONE_STATE).
                initPermission();
    }

    /**
     * 显示或者关闭dialog
     */
    private void showDilog() {
        if (mLoginingDlg == null)
            return;
        if (mLoginingDlg.isShowing()) {
            mLoginingDlg.dismiss();
        } else {
            mLoginingDlg.show();
        }
    }

    /**
     * 新建适配器，适配器数据为搜索历史文件内容
     */
    private void initAdapter() {
        db = new DBIpPortManager(this);
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, db.queryAll(tb_login_account));
        edtUsername.setAdapter(adapter);
    }

    /**
     * 上线后删除
     */
    public void test() {
        if (IS_RELEASE) return;

        if (isOffline) {
            edtUsername.setText(getPropString("OffUsername"));
            edtPassword.setText(getPropString("OffPassword"));
        } else {
            edtUsername.setText(getPropString("Username"));
            edtPassword.setText(getPropString("Password"));
        }

    }


}
