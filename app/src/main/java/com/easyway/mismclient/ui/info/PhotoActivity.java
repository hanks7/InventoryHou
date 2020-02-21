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
import com.easyway.mismclient.base.BaseModel;
import com.easyway.mismclient.model.TestBean;
import com.easyway.mismclient.ui.adapter.GvAdapter;
import com.easyway.mismclient.utils.DialogInf;
import com.easyway.mismclient.utils.UToast;
import com.easyway.mismclient.utils.http.HttpAdapter;
import com.easyway.mismclient.utils.http.OnResponseListener;
import com.easyway.mismclient.utils.permission.PermissionReq;
import com.easyway.mismclient.view.GridViewForScrollView;
import com.easyway.mismclient.view.MyDialog;
import com.jaiky.imagespickers.ImageConfig;
import com.jaiky.imagespickers.ImageLoader;
import com.jaiky.imagespickers.ImageSelector;
import com.jaiky.imagespickers.ImageSelectorActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.easyway.mismclient.model.TestBean.setDataList;
import static com.easyway.mismclient.utils.http.HttpAdapter.getRequestBody;

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

    public void btnClickUpload(View view) {
        upLoadImg();
    }

    /**
     * 上传图片的网络请求
     */
    public void upLoadImg() {
        if (mPathList == null) {
            return;
        }


        test1();


    }

    private void test1() {
        HttpAdapter.getService().upload(
                getRequestBody("{\"InfoId\":2141, CreateUser:\"000000demo\"}"),
                filesToMultipartBodyParts()
        ).enqueue(new OnResponseListener<BaseModel>(this) {
            @Override
            public void onSuccess(BaseModel baseModel) {
                UToast.showText("upLoadImg-onSuccess");
            }
        });
    }
    private void test2() {
        HttpAdapter.getService().upload(
                getRequestBody("{\"InfoId\":2141, CreateUser:\"000000demo\"}"),
                filesToMultipartBodyParts().get(0)
        ).enqueue(new OnResponseListener<BaseModel>(this) {
            @Override
            public void onSuccess(BaseModel baseModel) {
                UToast.showText("upLoadImg-onSuccess");
            }
        });
    }

    public List<MultipartBody.Part> filesToMultipartBodyParts() {
        List<MultipartBody.Part> parts = new ArrayList<>();
        for (File file : mFileList) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
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

    List<String> mPathList;
    List<File> mFileList;

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
            mPathList = data.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
            mFileList = new ArrayList<>();
            for (final String path : mPathList) {
                File file = new File(path);
                mFileList.add(file);
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
