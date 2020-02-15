package com.easyway.mismclient.ui.business;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.easyway.mismclient.MainActivity;
import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseActivity;
import com.easyway.mismclient.base.BaseModel;
import com.easyway.mismclient.model.MProdEnterDetailBean;
import com.easyway.mismclient.ui.adapter.SlideViewAdapter;
import com.easyway.mismclient.utils.DialogInf;
import com.easyway.mismclient.utils.UToast;
import com.easyway.mismclient.utils.Ugson;
import com.easyway.mismclient.utils.Uintent;
import com.easyway.mismclient.utils.http.HttpAdapter;
import com.easyway.mismclient.utils.http.OnResponseListener;
import com.easyway.mismclient.view.ListSlideView;
import com.easyway.mismclient.view.MyToast;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.easyway.mismclient.base.BaseOFFLineApplication.APP;


/**
 * @author 侯建军 47466436@qq.com
 * @date 2018/5/28
 * @description 整单确认
 */
public class CommitAllTableActivity extends BaseActivity {

    @BindView(R.id.ac_commit_table_list_slide_view)
    ListSlideView mListSlideView;

    @BindView(R.id.ac_commit_all_table_btnComfirm)
    Button mBtnComfirm;

    private SlideViewAdapter adapter;//带侧滑的adapter
    MProdEnterDetailBean mBean;//万络返回的javabean
    int Reg_id;//上一页得到的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commit_all_table);
        ButterKnife.bind(this);

        Reg_id = getIntentData();//从上一个界面得到数据
        initView();//初始化控件
        getNetMProdEnterDetail();//网络请求

        adapter.setRemoveListener(new SlideViewAdapter.OnRemoveListener() {
            @Override
            public void onRemoveItem(int position) {
                mListSlideView.slideBack();
            }
        });

    }

    /**
     * 网络请求
     */
    private void getNetMProdEnterDetail() {
        HttpAdapter.getService().MProdEnterDetail(Reg_id)
                .enqueue(new OnResponseListener<MProdEnterDetailBean>(this) {
                    @Override
                    public void onSuccess(MProdEnterDetailBean bean) {
                        mBean = bean;
                        adapter.updateList(mBean.getDetails());
                    }


                });
    }

    /**
     * 初始化控件
     */
    private void initView() {
        adapter = new SlideViewAdapter(mActivity, null);
        mListSlideView.setAdapter(adapter);
    }

    /**
     * 从上一个界面得到数据
     */
    private int getIntentData() {
        return (int) getIntent().getExtras().get("mDepartmentCollarID");
    }

    /**
     * 点击确定的业务
     *
     * @param view
     */
    public void clickCommit(View view) {
        showDialog();
    }


    /**
     * 弹出设置权限dialog
     */
    public void showDialog() {

        UToast.showDialog(mActivity, "确定后无法撤销,是否继续", false, new DialogInf() {

            @Override
            public void commit() {
                doNetWork();//提交时的网络请求
            }
        });
    }

    /**
     * 提交时的网络请求
     */
    private void doNetWork() {
        mBean.setOperateName(APP.userModel.getEmployeeName());
        mBean.setOperateID(APP.userModel.getHRCode());
        HttpAdapter.getService().setMProdEnterDetail(
                Ugson.toJson(mBean))
                .enqueue(new OnResponseListener<BaseModel>(this) {
                    @Override
                    public void onSuccess(BaseModel bean) {

                        MyToast.makeText(mActivity, getString(R.string.commit_success), Toast.LENGTH_SHORT, new Runnable() {
                            @Override
                            public void run() {
                                Uintent.intentDIY(mActivity, MainActivity.class);
                                finish();
                            }
                        }).show();

                        mBtnComfirm.setEnabled(false);
                        mBtnComfirm.setBackgroundColor(mActivity.getResources().getColor(R.color.holo_green_dark));


                    }

                    @Override
                    public void onFailure(String strToast) {
                        UToast.showSnackBar(mBtnComfirm, strToast, R.color.red, R.color.white);
                    }
                });
    }


}
