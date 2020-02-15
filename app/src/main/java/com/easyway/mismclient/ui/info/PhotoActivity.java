package com.easyway.mismclient.ui.info;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseActivity;
import com.easyway.mismclient.model.TestBean;
import com.easyway.mismclient.ui.adapter.GvAdapter;
import com.easyway.mismclient.utils.DialogInf;
import com.easyway.mismclient.utils.permission.PermissionReq;
import com.easyway.mismclient.view.GridViewForScrollView;
import com.easyway.mismclient.view.MyDialog;
import com.jaiky.imagespickers.ImageConfig;
import com.jaiky.imagespickers.ImageLoader;
import com.jaiky.imagespickers.ImageSelector;
import com.jaiky.imagespickers.ImageSelectorActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.easyway.mismclient.model.TestBean.setDataList;

public class PhotoActivity extends BaseActivity {
    @BindView(R.id.gv_photo)
    GridViewForScrollView gv;

    int position;
    List<TestBean> list;
    GvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
        adapter = new GvAdapter(this, null);
        list = setDataList();
        gv.setAdapter(adapter);
        adapter.addAllData(list);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                if (adapter.getList().size() - 1 == i) {
                    MyDialog.showSelectPhotoOptions(mActivity, adapter.getList(), new DialogInf() {
                        @Override
                        public void commit() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                } else {
                    toPhoto();
                }
            }
        });
    }


    //********************************选择照片*************************************************
    private void toPhoto() {

        if (!PermissionReq.judgePermisson(this, Manifest.permission.CAMERA)) return;
        if (!PermissionReq.judgePermisson(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) return;

        ImageConfig imageConfig
                = new ImageConfig.Builder(new GlideLoader())
                .steepToolBarColor(getResources().getColor(R.color.colorPrimary))
                .titleBgColor(getResources().getColor(R.color.colorPrimary))
                .titleSubmitTextColor(getResources().getColor(R.color.white))
                .titleTextColor(getResources().getColor(R.color.white))
                // 开启单选   （默认为多选）
                .singleSelect()
                // 开启拍照功能 （默认关闭）
                .showCamera()
                // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                .filePath("/ImageSelector/Pictures")
                .build();


        ImageSelector.open(this, imageConfig);   // 开启图片选择器
    }

    class GlideLoader implements ImageLoader {

        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            Glide.with(context)
                    .load(path)
                    .placeholder(com.jaiky.imagespickers.R.mipmap.imageselector_photo)
                    .centerCrop()
                    .into(imageView);
        }

    }

    /**
     * 得到照片返回的结果
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImageSelector.IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);

            for (final String path : pathList) {

                if (adapter.getList().size() - 1 == position) {
                    adapter.addBean(path, "");
                } else {
                    ((TestBean) adapter.getList().get(position)).setPath(path);
                }
                adapter.notifyDataSetChanged();
            }

        }


    }

}
