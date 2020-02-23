package com.easyway.mismclient.ui.business;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseActivity;
import com.easyway.mismclient.model.WarehouseDetailList;
import com.easyway.mismclient.ui.adapter.GvNormalAdapter;
import com.easyway.mismclient.utils.ShowPhotoView;
import com.easyway.mismclient.view.GridViewForScrollView;
import com.easyway.mismclient.view.TopBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UploadImageActivity extends BaseActivity {
    @BindView(R.id.ac_upload_img_topbar)
    TopBar topbar;
    @BindView(R.id.ac_upload_img_tv_product_name)
    TextView mTvProductName;
    @BindView(R.id.ac_upload_img_tv_spec_model)
    TextView mTvSpecModel;
    @BindView(R.id.ac_upload_img_tv_pb_sn)
    TextView mTvPbSn;
    @BindView(R.id.ac_upload_img_tv_Amount)
    TextView mTvAmount;
    @BindView(R.id.ac_upload_img_tv_doc_name)
    TextView mTvDocName;

    @BindView(R.id.ac_upload_img_gv_photo)
    GridViewForScrollView mGvPhoto;

    private GvNormalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        ButterKnife.bind(this);
        initView(getIntentData());

        final ArrayList<String> list = getTestStrings();

        adapter = new GvNormalAdapter(this, list);
        mGvPhoto.setAdapter(adapter);
        mGvPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShowPhotoView.imageBrower(mActivity, position, list);
            }
        });
    }

    /**
     * 制造假数据，用于测试。
     *
     * @return
     */
    @NonNull
    private ArrayList<String> getTestStrings() {
        final ArrayList<String> list = new ArrayList<>();
        list.add("http://img.xker.com/xkerfiles/allimg/1507/092032F42-0.png");
        list.add("http://img.xker.com/xkerfiles/allimg/1507/095JQ054-0.jpg");
        list.add("http://img.xker.com/xkerfiles/allimg/1507/09214L559-0.jpg");
        list.add("http://img.xker.com/xkerfiles/allimg/1502/15052252B-0.png");
        return list;
    }

    /**
     * 初始化页面数据
     *
     * @param bean
     */
    private void initView(WarehouseDetailList bean) {
        if (bean == null) {
            return;
        }
        mTvAmount.setText(bean.getAmount() + "");
        mTvDocName.setText(bean.getProductName() + "");
        mTvProductName.setText(bean.getProductName() + "");
        String content =TextUtils.isEmpty(bean.getProductBatch())   &&TextUtils.isEmpty(bean.getSerialNumber())   ? "/" : "";
        mTvPbSn.setText(bean.getProductBatch() + content + bean.getSerialNumber());
        mTvSpecModel.setText(bean.getSpecName() + content + bean.getModelName());
    }

    /**
     * 从intent中获取bundle
     *
     * @return
     */
    private WarehouseDetailList getIntentData() {
        return (WarehouseDetailList) getIntent().getExtras().get("user");
    }


}
