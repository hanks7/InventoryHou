package com.easyway.mismclient.ui.info;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.easyway.BarcodeObject;
import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseActivity;
import com.easyway.mismclient.model.MProdEnterBean;
import com.easyway.mismclient.ui.adapter.LsvInfoAdapter;
import com.easyway.mismclient.ui.adapter.LsvPopRegisterNumAdapter;
import com.easyway.mismclient.utils.SimulateNetAPI;
import com.easyway.mismclient.utils.UToast;
import com.easyway.mismclient.utils.UTools;
import com.easyway.mismclient.utils.Ugson;
import com.easyway.mismclient.utils.Uintent;
import com.easyway.mismclient.utils.Ulog;
import com.easyway.mismclient.utils.permission.PermissionReq;
import com.easyway.mismclient.view.MyTextView;
import com.easyway.mismclient.view.PopMenu;
import com.easyway.mismclient.view.PopMenu2;
import com.easyway.mismclient.view.TopBar;
import com.google.zxing.activity.CaptureActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InfoActivity extends BaseActivity {
    @BindView(R.id.ac_check_edit_ping_ci)
    MyTextView mEditProductInfoName;
    @BindView(R.id.ac_check_edt_guige)
    MyTextView mEdtSpecification;
    @BindView(R.id.ac_check_edt_factory_of_product)
    MyTextView mEdtEnterpriseName;


    @BindView(R.id.ac_check_register_tv_num)
    TextView mRegisterTvNum;
    @BindView(R.id.ac_check_register_iv_close)
    ImageView mRegisterIvClose;
    @BindView(R.id.ac_check_register_rl_select)
    RelativeLayout mRegisterRlSelect;


    @BindView(R.id.ac_inventory_lsv)
    ListView mLsv;
    @BindView(R.id.ac_check_topbar)
    TopBar mTopBar;
    @BindView(R.id.ac_check_register_tv_tag)
    TextView acCheckRegisterTvTag;
    @BindView(R.id.ac_check_scroll_view)
    ScrollView acCheckScrollView;
    @BindView(R.id.ac_barcode_scan_rl_search)
    RelativeLayout mRlSearch;
    @BindView(R.id.ac_barcode_scan_btnOK2)
    Button btn;

    private LsvInfoAdapter adapter;
    private LsvPopRegisterNumAdapter popRegisterNumAdapter;
    private PopMenu popZhuCeCode;//注册证号
    private PopMenu2 popZhuCeCode2;//搜索结果
    MProdEnterBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);

        mBean = Ugson.toBean(SimulateNetAPI.getOriginalFundData(this, "test_json_6.json"), MProdEnterBean.class);

        popRegisterNumAdapter = new LsvPopRegisterNumAdapter(mActivity, null);
        popZhuCeCode = new PopMenu(mActivity, mRegisterIvClose, mRegisterRlSelect, popRegisterNumAdapter);
        popZhuCeCode2 = new PopMenu2(mActivity, mRlSearch, btn, popRegisterNumAdapter);

        mEditProductInfoName.setText(mBean.getProductInfoName());
        mEdtSpecification.setText(mBean.getSpecification());
        mEdtEnterpriseName.setText(mBean.getEnterpriseName());

        mRegisterTvNum.setText(mBean.getRegisterNum());

        adapter = new LsvInfoAdapter(this, null);
        mLsv.setAdapter(adapter);

        mLsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Uintent.intentDIY(mActivity, PhotoActivity.class);
            }
        });


        adapter.addAllData(mBean.getDetailList());//sn为空时可以添加数字,否则就不可以

        intRegisterPop();
        intRegisterPop2();

        mTopBar.setIvRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UToast.showText("View");
            }
        });
    }

    private void intRegisterPop2() {
        popZhuCeCode2.setOnItemClickListener(mBean.getLicenceList(), new PopMenu2.ItemClickListener() {
            @Override
            public void itemClickListener(int position) {

                String strRegisterNum = mBean.getLicenceList().get(position).getRegistrationCard();
                UToast.showText(strRegisterNum);

            }
        });
    }

    /**
     * 给注册证的popWindow赋值
     */
    public void intRegisterPop() {

        popZhuCeCode.setOnItemClickListener(mBean.getLicenceList(), new PopMenu.ItemClickListener() {
            @Override
            public void itemClickListener(int position) {

                String strRegisterNum = mBean.getLicenceList().get(position).getRegistrationCard();
                mRegisterTvNum.setText(strRegisterNum);
                mBean.setRegisterNum(strRegisterNum);

            }
        });

    }

    private static final int REQUEST_CODE = 100;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == REQUEST_CODE && intent != null) {

            String result = intent.getStringExtra(CaptureActivity.SCAN_QRCODE_RESULT);

            BarcodeObject barcodeObject = UTools.getBarcodeObject(result);

            if (barcodeObject.getBarcodeType() == null) {
                UToast.showText("条码格式不正确");
            }
            switch (barcodeObject.getBarcodeType()) {
                case Primary:
                    Ulog.i("Primary", barcodeObject.getMainCode());
                    break;
                case Secondary:
                    Ulog.i("Secondary", barcodeObject.getMainCode());
                    break;
                case Concatenated:
                    Ulog.i("Concatenated", barcodeObject.getMainCode());
                    break;
            }

        }


    }

    @OnClick({
            R.id.ac_barcode_scan_btnOK1,
            R.id.ac_check_register_tv_tag
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_barcode_scan_btnOK1:
                intentCameraActivity();
                break;
            case R.id.ac_check_register_tv_tag:
                break;
        }
    }

    /**
     * 跳转到扫描二维码界面
     */
    private void intentCameraActivity() {
        if (!PermissionReq.judgePermisson(this, Manifest.permission.CAMERA)) return;//没有相机权限
        Intent intent = new Intent();
        intent.setClass(this, CaptureActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }
}
