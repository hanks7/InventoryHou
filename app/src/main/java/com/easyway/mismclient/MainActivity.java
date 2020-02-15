package com.easyway.mismclient;

import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.easyway.mismclient.base.BaseActivity;
import com.easyway.mismclient.base.BaseConstants;
import com.easyway.mismclient.model.UserModuleBean;
import com.easyway.mismclient.ui.business.CheckActivity;
import com.easyway.mismclient.ui.business.InventoryActivity;
import com.easyway.mismclient.ui.business.OperationRegisterActivity;
import com.easyway.mismclient.ui.business.PickingActivity;
import com.easyway.mismclient.ui.business.ReturnActivity;
import com.easyway.mismclient.ui.info.InfoActivity;
import com.easyway.mismclient.ui.login.LoginActivity;
import com.easyway.mismclient.utils.DialogInf;
import com.easyway.mismclient.utils.UToast;
import com.easyway.mismclient.utils.Uintent;
import com.easyway.mismclient.utils.Ulog;
import com.easyway.mismclient.view.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.easyway.mismclient.base.BaseConstants.MILLISECONDS;
import static com.easyway.mismclient.base.BaseOFFLineApplication.APP;
import static com.easyway.mismclient.utils.UTools.netUpdate;
import static com.easyway.mismclient.utils.UmengUtils.onLogout;

/**
 * @author 侯建军 47466436@qq.com
 * @date 2018/5/28
 * @description 首页界面
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.fg_mine_hrcode)
    TextView tvHrcode;
    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.fg_mine_EmployeeName)
    TextView tvEmployeeName;
    @BindView(R.id.fg_mine_ValidCode)
    TextView tvValidCode;
    @BindView(R.id.fg_mine_HosID)
    TextView tvHosID;
    @BindView(R.id.fg_mine_ll_detail)
    View llDetail;
    @BindView(R.id.main_tv_empty_note)
    View tvEmptyNote;

    @BindView(R.id.fg_one_1)
    View fgOne1;
    @BindView(R.id.fg_one_2)
    View fgOne2;
    @BindView(R.id.fg_one_3)
    View fgOne3;
    @BindView(R.id.fg_one_4)
    View fgOne4;
    @BindView(R.id.fg_one_5)
    View fgOne5;
    @BindView(R.id.fg_one_6)
    View fgOne6;

    @BindView(R.id.main_drawer_layout)
    DrawerLayout mDrawerLayout;//侧滑布局

    private Vibrator vibrator;//震动

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initView();
        initData();
        test();

    }

    private void initData() {
        tvHrcode.setText("HRCode:  " + APP.userModel.getHRCode());
        tvEmployeeName.setText("用户名:  " + APP.userModel.getEmployeeName());
        tvValidCode.setText(APP.userModel.GetValidCode());
        tvHosID.setText("HospitalID:  " + APP.userModel.getHospitalID());
    }

    protected void initView() {
        for (UserModuleBean userModuleBean : APP.getUserModel().GetModule()) {
            Ulog.i("getPrivilegeCode", userModuleBean.getPrivilegeCode());
            switch (userModuleBean.getPrivilegeCode()) {
                case "pandian":
                    fgOne1.setVisibility(View.VISIBLE);
                    break;
                case "yanshou":
                    fgOne2.setVisibility(View.VISIBLE);
                    break;
                case "lingyong"://
                    fgOne3.setVisibility(View.VISIBLE);
                    break;
                case "huanku"://
                    fgOne4.setVisibility(View.VISIBLE);
                case "caiji"://
                    fgOne5.setVisibility(View.VISIBLE);
                    break;
            }

        }
        if (!BaseConstants.IS_RELEASE) {
            fgOne6.setVisibility(View.VISIBLE);
        }
        Ulog.i("权限个数", APP.getUserModel().GetModule().size());
        tvEmptyNote.setVisibility(APP.getUserModel().GetModule().size() == 0 ? View.VISIBLE : View.GONE);
        topbar.setTitle(APP.getUserModel().getEmployeeName());
        topbar.setIvBackButton(R.mipmap.menu, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                judgeDrawerLayoutIsOpen();
            }
        });
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(30);

    }


    @OnClick({
            R.id.fg_mine_update,
            R.id.fg_mine_quit,
            R.id.fg_mine_ll_detail,
            R.id.fg_one_1,
            R.id.fg_one_2,
            R.id.fg_one_3,
            R.id.fg_one_4,
            R.id.fg_one_5,
            R.id.fg_one_6

    })
    public void onViewClicked(View view) {
        vibrator.vibrate(MILLISECONDS);
        switch (view.getId()) {
            case R.id.fg_mine_update:
                netUpdate(mActivity, true, true);
                break;
            case R.id.fg_mine_quit:
                showQuitDialog();//退出登录
                break;
            case R.id.fg_one_1:
                Uintent.intentDIY(this, InventoryActivity.class);
                break;
            case R.id.fg_one_2:
                Uintent.intentDIY(this, CheckActivity.class);
                break;
            case R.id.fg_one_3:
                Uintent.intentDIY(this, PickingActivity.class);
                break;
            case R.id.fg_one_4:
                Uintent.intentDIY(this, ReturnActivity.class);
                break;
            case R.id.fg_one_5:
                Uintent.intentDIY(this, InfoActivity.class);
                break;
            case R.id.fg_one_6:
                Uintent.intentDIY(this, OperationRegisterActivity.class);
                break;
            case R.id.fg_mine_ll_detail:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        netUpdate(mActivity, false, false);
    }


    /**
     * 判断DrawerLayout是否 打开
     * 如果侧滑打开就关闭,关闭就打开
     */
    private void judgeDrawerLayoutIsOpen() {
        if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {//如果侧滑打开就关闭,关闭就打开
            mDrawerLayout.closeDrawers();
        } else {
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    /**
     * 退出登录dialog
     */
    public void showQuitDialog() {
        UToast.showDialog(this, "退出登录将清除个人信息,是否继续", true, new DialogInf() {
            @Override
            public void commit() {
                quitLogin();
            }
        });
    }

    /**
     * 退出登录
     */
    private void quitLogin() {
        APP.quitLogin();
        onLogout();
        Uintent.intentDIY(this, LoginActivity.class);
        finish();
    }

    private long mkeyTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mkeyTime) > 2000) {
                mkeyTime = System.currentTimeMillis();
                UToast.showSnackBar(topbar, "再按一次退出" + getResources().getString(R.string.app_name), R.color.colorPrimary, R.color.white);
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 上线后删除
     */
    private void test() {

        if (BaseConstants.IS_RELEASE) return;

        UToast.showDialog2(this, "这是测试版本", false, null);
        topbar.setTitle(topbar.getTitle() + "(测试版)");
    }
}
