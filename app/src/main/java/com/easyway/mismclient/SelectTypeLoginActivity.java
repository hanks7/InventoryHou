package com.easyway.mismclient;

import android.os.Bundle;
import android.view.View;

import com.easyway.mismclient.base.BaseActivity;
import com.easyway.mismclient.ui.offline.login.LoginOffActivity;
import com.easyway.mismclient.ui.login.LoginActivity;
import com.easyway.mismclient.utils.Uintent;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectTypeLoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.select_btn_offline_login, R.id.select_btn_online_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.select_btn_offline_login:
                Uintent.intentDIY(mActivity, LoginActivity.class);finish();
                break;
            case R.id.select_btn_online_login:
                Uintent.intentDIY(mActivity, LoginOffActivity.class);finish();
                break;
        }
    }
}
