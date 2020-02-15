package com.easyway.mismclient.ui.offline.login;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.easyway.mismclient.MainOfflineActivity;
import com.easyway.mismclient.R;
import com.easyway.mismclient.SelectTypeLoginActivity;
import com.easyway.mismclient.base.BaseActivity;
import com.easyway.mismclient.dao.DbHelper;
import com.easyway.mismclient.model.UserOFLBean;
import com.easyway.mismclient.utils.UToast;
import com.easyway.mismclient.utils.Uintent;
import com.easyway.mismclient.utils.UtilSystem;
import com.easyway.mismclient.utils.http.ImpDBSuccessFailListener;
import com.easyway.mismclient.utils.permission.PermissionReq;
import com.easyway.mismclient.utils.permission.PermissionResultTask;
import com.easyway.mismclient.view.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.easyway.mismclient.base.BaseConstants.IS_RELEASE;
import static com.easyway.mismclient.base.BaseOFFLineApplication.APP;
import static com.easyway.mismclient.utils.UProperTies.getPropString;


public class LoginOffActivity extends BaseActivity {


    @BindView(R.id.login_edt_username)
    EditText edtUsername;
    @BindView(R.id.login_edt_pwd)
    EditText edtPassword;
    @BindView(R.id.login_tv_version)
    TextView tvVersion;

    DbHelper mDbHelper;
    @BindView(R.id.topbar)
    TopBar topbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ologin);
        ButterKnife.bind(this);
        initView();
        mDbHelper = new DbHelper(this);//创建一个空的数据库数据库
//        addTestData(mDbHelper);
        initView();//初始化控件
        test();//上线后删除
        topbar.setTvLeft("登录类型", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uintent.intentDIY(mActivity, SelectTypeLoginActivity.class);finish();

            }
        });
    }

    @OnClick({R.id.userlogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.userlogin:

                PermissionReq.with(this)
                        .permissions(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .result(new PermissionResultTask(Manifest.permission.READ_EXTERNAL_STORAGE, this) {
                            @Override
                            public void onGranted() {
                                btnLoginClick();//登录的业务
                            }
                        })
                        .request();

                break;
        }
    }


    /**
     * 登陆的业务
     */
    private void btnLoginClick() {
        String hrCode = edtUsername.getText().toString().trim();
        if (hrCode.equals("")) {
            UToast.showText("账户名不能为空");
            return;
        }
        String password = edtPassword.getText().toString().trim();
        if (password.equals("")) {
            UToast.showText("密码不能为空");
            return;
        }


        mDbHelper.queryEmployee(hrCode, password,
                new ImpDBSuccessFailListener<UserOFLBean>() {
                    @Override
                    public void onSuccess(UserOFLBean bean) {
                        APP.mUserOFLBean=bean;
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
     * 初始化控件
     */
    private void initView() {
        String strVersion = String.format("版本号: %s  版本名称: %s", UtilSystem.getVersionCode(), UtilSystem.getVersionName());
        tvVersion.setText(strVersion);
    }


    /**
     * 上线后删除
     */
    public void test() {

        if (IS_RELEASE) return;

        edtUsername.setText( getPropString("OffUsername"));
        edtPassword.setText(getPropString("OffPassword"));
    }

}
