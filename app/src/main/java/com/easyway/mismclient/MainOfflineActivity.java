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
import com.easyway.mismclient.dao.DbHelper;
import com.easyway.mismclient.ui.business.CheckActivity;
import com.easyway.mismclient.ui.business.PickingActivity;
import com.easyway.mismclient.ui.business.ReturnActivity;
import com.easyway.mismclient.ui.login.LoginActivity;
import com.easyway.mismclient.ui.offline.InventoryOffActivity;
import com.easyway.mismclient.utils.DialogInf;
import com.easyway.mismclient.utils.UToast;
import com.easyway.mismclient.utils.Uintent;
import com.easyway.mismclient.view.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.easyway.mismclient.base.BaseConstants.MILLISECONDS;
import static com.easyway.mismclient.base.BaseOFFLineApplication.APP;
import static com.easyway.mismclient.utils.UTools.netUpdate;

public class MainOfflineActivity extends BaseActivity {

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

    @BindView(R.id.fg_one_1)
    View fgOne1;
    @BindView(R.id.fg_one_2)
    View fgOne2;
    @BindView(R.id.fg_one_3)
    View fgOne3;
    @BindView(R.id.fg_one_4)
    View fgOne4;
    @BindView(R.id.ac_check_ll_inventory)
    View mllInventory;//显示盘点时间和盘点单号
    @BindView(R.id.ac_check_ll_tv_inventory_number)
    TextView mTvInventoryNumber;//显示盘点时间和盘点单号
    @BindView(R.id.ac_check_ll_tv_inventory_start_date)
    TextView mTvInventoryStartDate;//显示盘点时间和盘点单号

    @BindView(R.id.main_drawer_layout)
    DrawerLayout mDrawerLayout;//侧滑布局

    private Vibrator vibrator;//震动
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        dbHelper = new DbHelper(this);
        initView();
        initData();
        test();
    }

    /**
     * 上线后删除
     */
    private void test() {

        if (BaseConstants.IS_RELEASE) return;

        UToast.showDialog2(this, "这是测试版本", false, null);
        topbar.setTitle(topbar.getTitle() + "(测试版)");
    }

    private void initData() {
        tvHrcode.setText("HRCode:  " + APP.mUserOFLBean.getHRCode());
        tvEmployeeName.setText("用户名:  " + APP.mUserOFLBean.getEmployeeName());
        tvValidCode.setText(dbHelper.queryDataUserDept(APP.mUserOFLBean.getDeptCode()) + "--" + APP.mUserOFLBean.getDutyName());
        tvHosID.setText("" + APP.mUserOFLBean.getHosptialName());
    }

    protected void initView() {

        fgOne1.setVisibility(View.VISIBLE);
        mllInventory.setVisibility(View.VISIBLE);

//        fgOne2.setVisibility(View.VISIBLE);
//        fgOne3.setVisibility(View.VISIBLE);
//        fgOne4.setVisibility(View.VISIBLE);

        topbar.setTitle("(离线盘点)-" + APP.mUserOFLBean.getEmployeeName());
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
            R.id.fg_one_4

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
                Uintent.intentDIY(this, InventoryOffActivity.class);
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
            case R.id.fg_mine_ll_detail:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        netUpdate(mActivity, false, false);
        showDialog();
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
        APP.quitLogin2();
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
     * 提示用户同步数据
     */
    public void showDialog() {
        mTvInventoryStartDate.setText(new StringBuilder().append(dbHelper.queryInventoryBeginDate()).toString());
        mTvInventoryNumber.setText(new StringBuilder().append(dbHelper.queryInventoryInventoryCode()).toString());
        if (!dbHelper.queryInventoryStatus()) {//判断TB_Inventory 中的 enddate不为空 就不弹出dialog
            return;
        }
        UToast.showDialog2(this, "请链接客户端,更新数据", true, new DialogInf() {
            @Override
            public void commit() {

            }
        });
    }


}
