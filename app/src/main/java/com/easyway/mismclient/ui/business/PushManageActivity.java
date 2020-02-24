package com.easyway.mismclient.ui.business;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseActivity;
import com.easyway.mismclient.model.MWareHouseBean;
import com.easyway.mismclient.model.WarehouseDetailList;
import com.easyway.mismclient.ui.adapter.LsvPushManageBottomAdapter;
import com.easyway.mismclient.utils.Uintent;
import com.easyway.mismclient.utils.http.HttpAdapter;
import com.easyway.mismclient.utils.http.OnResponseListener;
import com.easyway.mismclient.view.ListViewForScrollView;
import com.easyway.mismclient.view.MyEditView;
import com.easyway.mismclient.view.MyTextView;
import com.easyway.mismclient.view.TopBar;
import com.easyway.mismclient.view.interf.BarCodeCallBackListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 入库管理
 */
public class PushManageActivity extends BaseActivity {

    @BindView(R.id.ac_push_manage_topbar)
    TopBar mTopbar;
    @BindView(R.id.ac_push_manage_edit_main)
    MyEditView mEditMain;

    @BindView(R.id.ac_push_manage_edit_zhi_dan_date)
    MyTextView mEditZhiDanDate;
    @BindView(R.id.ac_push_manage_edt_gong_ying_shang)
    MyTextView mEdtGongYingShang;
    @BindView(R.id.ac_push_manage_edt_ru_ku_ku_fang)
    MyTextView mEdtRuKuKuFang;

    @BindView(R.id.ac_push_manage_lsv)
    ListViewForScrollView mLsv;

    private LsvPushManageBottomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_manage);
        ButterKnife.bind(this);

        adapter = new LsvPushManageBottomAdapter(this, null);
        mLsv.setAdapter(adapter);

        mEditMain.setListener(new BarCodeCallBackListener() {
            @Override
            public void barCodeType(int barCodeType) {
                mEditMain.selectAll();
                setEmptyData();
                netWork(mEditMain.getText());
            }

            @Override
            public void doClose() {
                setEmptyData();
            }
        });

        mLsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("user",adapter.getItem(i));
                Uintent.intentDIY(mActivity, UploadImageActivity.class,bundle);
            }
        });
    }

    /**
     * 网络请求
     *
     * @param InstoreCode 测试用时 "QTRK201810230003"
     */
    private void netWork(String InstoreCode) {
        HttpAdapter.getService().MWareHouse(InstoreCode)
                .enqueue(new OnResponseListener<MWareHouseBean>(this) {
                    @Override
                    public void onSuccess(MWareHouseBean bean) {
                        initData(bean);

                    }

                });
    }

    private void initData(MWareHouseBean bean) {
        adapter.updateList(bean.getWarehouseDetailList());
        mEditZhiDanDate.setText(bean.getBLDate());
        mEdtGongYingShang.setText(bean.getSupplierName());
        mEdtRuKuKuFang.setText(bean.getDepartmentName());
    }

    private void setEmptyData() {
        adapter.updateList(new ArrayList<WarehouseDetailList>());
        mEditZhiDanDate.setText("");
        mEdtGongYingShang.setText("");
        mEdtRuKuKuFang.setText("");
    }


}
