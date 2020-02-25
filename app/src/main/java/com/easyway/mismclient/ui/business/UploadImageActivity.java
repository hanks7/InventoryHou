package com.easyway.mismclient.ui.business;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseActivity;
import com.easyway.mismclient.base.BaseModel;
import com.easyway.mismclient.model.UploadImageBean;
import com.easyway.mismclient.model.WarehouseDetailList;
import com.easyway.mismclient.ui.adapter.GvUploadImageAdapter;
import com.easyway.mismclient.ui.adapter.GvNormalAdapter;
import com.easyway.mismclient.utils.ShowPhotoView;
import com.easyway.mismclient.utils.UToast;
import com.easyway.mismclient.utils.Ulog;
import com.easyway.mismclient.utils.UploadHelper;
import com.easyway.mismclient.utils.http.HttpAdapter;
import com.easyway.mismclient.utils.http.OnResponseListener;
import com.easyway.mismclient.utils.permission.PermissionReq;
import com.easyway.mismclient.view.GridViewForScrollView;
import com.easyway.mismclient.view.TopBar;
import com.jaiky.imagespickers.ImageConfig;
import com.jaiky.imagespickers.ImageLoader;
import com.jaiky.imagespickers.ImageSelector;
import com.jaiky.imagespickers.ImageSelectorActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.RequestBody;

import static com.easyway.mismclient.base.BaseOFFLineApplication.APP;

/**
 * 图片上传界面
 */

/**
 * @ProjectName:
 * @Package:
 * @ClassName:
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate:
 * @UpdateUser: 更新者：
 * @UpdateDate:
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
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

    int position;
    GvUploadImageAdapter gvUploadImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        ButterKnife.bind(this);
        initView(getIntentData());


        init();
//        init2(mList);
    }

    public void btnClickUpload(View view) {
        if (gvUploadImageAdapter.getList() == null || gvUploadImageAdapter.getList().isEmpty()) {
            UToast.showText("请添加照片");
            return;
        }
        netWorkUpload2();
    }


    private void netWorkUpload2() {

        UploadHelper helper = UploadHelper.getInstance();
        for (UploadImageBean bean : gvUploadImageAdapter.getList()) {
            Ulog.i("netWorkUpload2", bean.getPath());
            File file = new File(bean.getPath());
            helper.addParameter("file", file);
        }

        Map<String, RequestBody> params = helper.addParameter("param", "{\"InfoId\":" + mBean.getProductInfoID() + ", CreateUser:\"" + APP.userModel.getEmployeeName() + "\"}").builder();


        HttpAdapter.getService().uploadFilesFeedback(params).enqueue(new OnResponseListener<BaseModel>(this) {
            @Override
            public void onSuccess(BaseModel baseModel) {
                UToast.showText("upLoadImg-onSuccess");
            }
        });
    }


    private void init2(final ArrayList<String> list) {
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
        String content = TextUtils.isEmpty(bean.getProductBatch()) && TextUtils.isEmpty(bean.getSerialNumber()) ? "/" : "";
        mTvPbSn.setText(bean.getProductBatch() + content + bean.getSerialNumber());
        mTvSpecModel.setText(bean.getSpecName() + content + bean.getModelName());
    }

    /**
     * 上一个页面传来的对象
     */
    WarehouseDetailList mBean;

    /**
     * 从intent中获取bundle
     *
     * @return
     */
    private WarehouseDetailList getIntentData() {
        mBean = (WarehouseDetailList) getIntent().getExtras().get("user");
        return mBean;
    }


    private void init() {
        gvUploadImageAdapter = new GvUploadImageAdapter(this, null);
        mGvPhoto.setAdapter(gvUploadImageAdapter);
        mGvPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                boolean isLast = gvUploadImageAdapter.getList().size() == i;
                if (isLast && gvUploadImageAdapter.getList().size() >= 9) {
                    UToast.showText("最多添加9张图片");
                    return;
                }
                if (isLast) {//判断是否点击最后一个“添加”按钮
                    position = i;
                    toPhoto(9 - gvUploadImageAdapter.getList().size());
                    return;
                } else {
                    ShowPhotoView.imageBrower(mActivity, gvUploadImageAdapter.getList().get(i).getPath());
                }
            }
        });
    }

    List<UploadImageBean> mList;

    /**
     * 处理选择图片之后返回主界面
     *
     * @param intent
     */
    private void dealReasultFromSelectPic(Intent intent) {
        List<String> pathList = intent.getStringArrayListExtra(ImageSelectorActivity.EXTRA_RESULT);
        mList = new ArrayList<>();
        for (final String path : pathList) {
            Ulog.i("path", path);
            mList.add(new UploadImageBean(path, ""));
        }

        gvUploadImageAdapter.addAllData(mList);
    }


    /**
     * 扫尾二维码回调放回
     *
     * @param requestCode
     * @param resultCode
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (requestCode == ImageSelector.IMAGE_REQUEST_CODE && resultCode == RESULT_OK && intent != null) {
            dealReasultFromSelectPic(intent);
            return;
        }
        if (resultCode == 103) {
//            dealReasultFromScanner(resultCode, intent);
            return;
        }

        UToast.showText("取消");
    }

    //********************************选择照片*************************************************
    private void toPhoto(int maxSize) {

        if (!PermissionReq.judgePermisson(this, Manifest.permission.CAMERA)) return;
        if (!PermissionReq.judgePermisson(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) return;

        ImageConfig imageConfig
                = new ImageConfig.Builder(new GlideLoader())
                .steepToolBarColor(getResources().getColor(R.color.colorPrimary))
                .titleBgColor(getResources().getColor(R.color.colorPrimary))
                .titleSubmitTextColor(getResources().getColor(R.color.white))
                .titleTextColor(getResources().getColor(R.color.white))
                .mutiSelectMaxSize(maxSize)
                // 开启单选   （默认为多选）
//                .singleSelect()
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


}
