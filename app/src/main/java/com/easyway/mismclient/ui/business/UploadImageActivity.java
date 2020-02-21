package com.easyway.mismclient.ui.business;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseActivity;
import com.easyway.mismclient.ui.adapter.GvNormalAdapter;
import com.easyway.mismclient.utils.ShowPhotoView;
import com.easyway.mismclient.view.GridViewForScrollView;
import com.easyway.mismclient.view.TopBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UploadImageActivity extends BaseActivity {
    @BindView(R.id.topbar)
    TopBar topbar;
    @BindView(R.id.ac_detail_repair_tv_phone)
    TextView mTvPhone;
    @BindView(R.id.ac_detail_repair_gv_photo)
    GridViewForScrollView mGvPhoto;
    private GvNormalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        ButterKnife.bind(this);

        final ArrayList<String> list = new ArrayList<>();
        list.add("http://img.xker.com/xkerfiles/allimg/1507/092032F42-0.png");
        list.add("http://img.xker.com/xkerfiles/allimg/1507/095JQ054-0.jpg");
        list.add("http://img.xker.com/xkerfiles/allimg/1507/09214L559-0.jpg");
        list.add("http://img.xker.com/xkerfiles/allimg/1502/15052252B-0.png");

        adapter = new GvNormalAdapter(this, list);
        mGvPhoto.setAdapter(adapter);
        mGvPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShowPhotoView.imageBrower(mActivity, position, list);
            }
        });
    }


}
