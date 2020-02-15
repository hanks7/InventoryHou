package com.easyway.mismclient.ui.business;

import android.os.Bundle;
import android.os.Vibrator;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseActivity;
import com.easyway.mismclient.dao.DBManager;
import com.easyway.mismclient.model.LicenceListBean;
import com.easyway.mismclient.model.MDepartmentBean;
import com.easyway.mismclient.model.MProdEnterBean;
import com.easyway.mismclient.model.MProdEnterResultBean;
import com.easyway.mismclient.ui.adapter.LsvBusinessBaseAdapter;
import com.easyway.mismclient.ui.adapter.LsvPopCunFanKeshiAdapter;
import com.easyway.mismclient.ui.adapter.LsvPopRegisterNumAdapter;
import com.easyway.mismclient.utils.Base64Utils;
import com.easyway.mismclient.utils.DialogInf;
import com.easyway.mismclient.utils.UToast;
import com.easyway.mismclient.utils.Ugson;
import com.easyway.mismclient.utils.Uintent;
import com.easyway.mismclient.utils.UtilDate;
import com.easyway.mismclient.utils.http.HttpAdapter;
import com.easyway.mismclient.utils.http.OnResponseListener;
import com.easyway.mismclient.view.interf.BarCodeCallBackListener;
import com.easyway.mismclient.view.BarCodeEditView;
import com.easyway.mismclient.view.CunfangKeshiPopMenu;
import com.easyway.mismclient.view.MyTextView;
import com.easyway.mismclient.view.PopMenu;
import com.easyway.mismclient.view.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.easyway.mismclient.base.BaseConstants.MILLISECONDS;
import static com.easyway.mismclient.base.BaseOFFLineApplication.APP;

/**
 * @author 侯建军 47466436@qq.com
 * @date 2018/5/28
 * @description 验收界面
 */
public class CheckActivity extends BaseActivity {

    @BindView(R.id.ac_check_edit_main)
    BarCodeEditView mEditMain;

    @BindView(R.id.ac_check_rl_select_keshi)
    RelativeLayout mRlSelectKeshi;
    @BindView(R.id.ac_check_tv_select_keshi)
    TextView mTvSelectKeshi;
    @BindView(R.id.ac_check_iv_close)
    ImageView mIvClose;

    @BindView(R.id.ac_check_edit_ping_ci)
    MyTextView mEditProductInfoName;
    @BindView(R.id.ac_check_edt_tong_yong_ming)
    MyTextView mEdtGeneralName;
    @BindView(R.id.ac_check_edt_guige)
    MyTextView mEdtSpecification;
    @BindView(R.id.ac_check_edt_xinhao)
    MyTextView mEdtModel;
    @BindView(R.id.ac_check_edt_factory_of_product)
    MyTextView mEdtEnterpriseName;

    @BindView(R.id.ac_check_edt_date_of_product)
    MyTextView mEdtProductionDate;
    @BindView(R.id.ac_check_edt_date_of_youxiao)
    MyTextView mEdtEffectiveDate;
    @BindView(R.id.ac_check_edt_num_of_pi)
    MyTextView mEdtLot;
    @BindView(R.id.ac_check_edt_num_of_xulie)
    MyTextView mEdtSN;

    @BindView(R.id.ac_check_register_tv_num)
    TextView mRegisterTvNum;
    @BindView(R.id.ac_check_register_iv_close)
    ImageView mRegisterIvClose;
    @BindView(R.id.ac_check_register_rl_select)
    RelativeLayout mRegisterRlSelect;

    @BindView(R.id.ac_check_btnComfirm)
    Button mBtnComfirm;

    @BindView(R.id.ac_inventory_lsv)
    ListView mLsv;
    @BindView(R.id.ac_check_topbar)
    TopBar mTopBar;
    @BindView(R.id.ac_check_register_tv_tag)
    TextView acCheckRegisterTvTag;
    @BindView(R.id.ac_check_scroll_view)
    ScrollView acCheckScrollView;

    private String strDeptCode;//存放科室的code
    private MProdEnterBean mBean;
    private PopMenu popZhuCeCode;//注册证号
    private LsvPopRegisterNumAdapter popRegisterNumAdapter;
    private LsvBusinessBaseAdapter adapter;
    private Vibrator vibrator;//震动
    private int mReg_id = 0;
    /**
     * 是否有订单提交
     */
    private boolean isCommit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        ButterKnife.bind(this);
        initView();
        initTitle();
    }

    @Override
    public void onBackPressed() {
        if (isCommit) {
            dialogQuit();
            return;
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 初始化头部
     */
    private void initTitle() {
        mTopBar.setTvRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBean == null) {//刚进来就点整单确认,在此拦截并提示用户
                    UToast.showText(getString(R.string.no_order));
                    return;
                }
                if (mReg_id == 0) {//用户只有一个订单,还没点确定
                    UToast.showText(getString(R.string.no_order));
                    return;
                }
                if (!mBean.getBuy().equals("-1")) {//用户最后一个订单,还没点确定,提示他要不要点击确定.
                    dialog();
                    return;
                }

                intentActivity();//进入到另CommitAllTableActivity界面

            }
        });
    }

    /**
     * 初始化页面
     */
    private void initView() {

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(30);

        adapter = new LsvBusinessBaseAdapter(this, null);
        mLsv.setAdapter(adapter);
        initEditMain();//初始化主次条码控件
        netPickView();
        popRegisterNumAdapter = new LsvPopRegisterNumAdapter(mActivity, null);
        popZhuCeCode = new PopMenu(mActivity, mRegisterIvClose, mRegisterRlSelect, popRegisterNumAdapter);
    }

    /**
     * 初始化主次条码控件
     */
    private void initEditMain() {
        mEditMain.setOnEditorBarCodeTypeListener(new  BarCodeCallBackListener() {
            @Override
            public void doClose() {
                setEmptyingInput();
            }

            @Override
            public void barCodeType(int barCodeType) {
                netGetDataFromCode();
            }
        });
    }

    /**
     * ProductInfoID : 371044
     * ProductInfoName : 压力延长管
     * EnterpriseID : 3165
     * EnterpriseName : 美国merit医疗设备有限公司
     * GeneralName : null
     * Specification : PM6148
     * BarCode : 0134987350724435
     * SubBarCode : 1720050010210101
     * ProductionDate : null
     * EffectiveDate : 2020-05-31
     * Lot : 210101
     * SN :
     * UnitName : 根
     */
    private void initData(MProdEnterBean bean) {
        if (bean == null) {
            mBean = new MProdEnterBean();
            mBean.setBuy("-1");
            //mBtnComfirm.setBackground(mActivity.getResources().getDrawable(R.drawable.selectable_background));
        } else {
            mBean = bean;
        }

        intRegisterPop();//给注册证的popWindow赋值
        mBean.setOperateName(APP.userModel.getEmployeeName());
        mBean.setOperateID(APP.userModel.getHRCode());
        mBean.setDeptCode(strDeptCode);

        mEditProductInfoName.setText(mBean.getProductInfoName());
        mEdtGeneralName.setText(mBean.getGeneralName());
        mEdtSpecification.setText(mBean.getSpecification());
        mEdtModel.setText(mBean.getModel());
        mEdtEnterpriseName.setText(mBean.getEnterpriseName());

        mRegisterTvNum.setText(mBean.getRegisterNum());
        mEdtProductionDate.setText(mBean.getProductionDate());
        mEdtEffectiveDate.setText(mBean.getEffectiveDate());
        mEdtLot.setText(mBean.getLot());
        mEdtSN.setText(mBean.getSN());

        popRegisterNumAdapter.updateList(mBean.getLicenceList());
        adapter.setIsClick(mBean.getDetailList(), true);//sn为空时可以添加数字,否则就不可以

        if (mBean.getLicenceList() != null && mBean.getLicenceList().size() == 1) {
            setRegisterNum(mBean.getLicenceList().get(0).getRegistrationCard());
        } else if (mBean.getLicenceList() != null) {
            for (LicenceListBean licenceListBean : mBean.getLicenceList()) {
                if (UtilDate.dateToNum(licenceListBean.getEndDate()) > UtilDate.dateToNum(mBean.getProductionDate())
                        && UtilDate.dateToNum(licenceListBean.getStartDate()) < UtilDate.dateToNum(mBean.getProductionDate())) {
                    setRegisterNum(licenceListBean.getRegistrationCard());
                    break;
                }

            }
        }
    }


    /**
     * 网络请求
     * i==0扫描的是主条码
     * i==1扫描的是次条码
     */
    private void netGetDataFromCode() {
        if (strDeptCode == null) {
            UToast.showText("请选择科室");
            return;
        }


        HttpAdapter.getService().MProdEnter(
                Base64Utils.setBase64(mEditMain.strBarCodeSource),//MDEzNDk4NzM1MDcyNDQzNQ%3D%3D
                Base64Utils.setBase64(mEditMain.strSubBarCodeSource)//MTcyMDA1MDAxMDIxMDEwMQ%3D%3D

        )
                .enqueue(new OnResponseListener<MProdEnterBean>(this) {
                    @Override
                    public void onSuccess(MProdEnterBean bean) {

                        mEditMain.selectAll();
                        mBtnComfirm.setBackground(mActivity.getResources().getDrawable(R.drawable.selectable_background));
                        initData(bean);

                        mRlSelectKeshi.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                UToast.showText("整单确认前无法修改存放科室!");
                            }
                        });
                    }

                    @Override
                    public void onFailure(String strToast) {
                        UToast.showSnackBar(mBtnComfirm, strToast, R.color.red, R.color.white);
                        initData(null);
                        mEditMain.setEmpty();
                        vibrator.vibrate(MILLISECONDS);
                    }
                });
    }

    /**
     * 点击确定的业务
     */
    private void netCommit(final int type) {
        if (TextUtils.isEmpty(mEditMain.getText())) {
            setVibrateAndred();
            UToast.showText("请先扫描主码和次码");
            return;
        }
        if (TextUtils.isEmpty(mRegisterTvNum.getText())) {
            setVibrateAndred();
            UToast.showText("请选择注册证");
            return;
        }
        mBean.setBarCodeSource(mEditMain.strBarCodeSource);
        mBean.setSubBarCodeSource(mEditMain.strSubBarCodeSource);
        mBean.setReg_id(mReg_id);

        HttpAdapter.getService().setMProdEnter(
                Ugson.toJson(mBean), type)
                .enqueue(new OnResponseListener<MProdEnterResultBean>(this) {

                    @Override
                    public void onSuccess(MProdEnterResultBean bean) {

                        if (type == 1) {
                            mReg_id = bean.getReg_id();
                            UToast.showSnackBar(mBtnComfirm, getString(R.string.commit_success), R.color.green, R.color.white);
                            isCommit = true;
                            setEmptyingInput();
                        } else {
                            netCommit(1);
                        }
                    }

                    @Override
                    public void onFailure(String strToast) {
                        UToast.showSnackBar(mBtnComfirm, strToast, R.color.red, R.color.white);
                        vibrator.vibrate(MILLISECONDS);
                    }

                    @Override
                    public void onFailure2(String strToast) {
                        dialog(strToast);
                    }


                });
    }

    /**
     * 产品生产日期不在注册证范围内，是否继续？
     * 弹出设置dialog
     */
    public void dialog(String str) {
        UToast.showDialog(mActivity, str, false, new DialogInf() {
            @Override
            public void commit() {
                netCommit(1);
            }
        });
    }

    /**
     * 产品生产日期不在注册证范围内，是否继续？
     * 弹出设置dialog
     */
    public void dialogQuit() {
        UToast.showDialog(mActivity, Html.fromHtml(getString(R.string.quit_hint)), false, new DialogInf() {
            @Override
            public void commit() {
                finish();
            }
        });
    }


    /**
     * 调用震动并将确定按钮设置为红色
     */
    private void setVibrateAndred() {
        vibrator.vibrate(MILLISECONDS);
        //mBtnComfirm.setBackgroundColor(mActivity.getResources().getColor(R.color.red));
    }

    @OnClick({R.id.ac_check_btnComfirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_check_btnComfirm:
                netCommit(0);
                break;
        }
    }


    /**
     * 选择存放科室
     */
    public void netPickView() {
        HttpAdapter.getService().getMDepartment().enqueue(new OnResponseListener<MDepartmentBean>(null) {
            @Override
            public void onSuccess(final MDepartmentBean mDepartmentBean) {

                DBManager dbManager = new DBManager(mActivity, "djfkadls.db");
                dbManager.insertAndUpdateData(mDepartmentBean.getDetails());

                CunfangKeshiPopMenu keshiPopMenu = new CunfangKeshiPopMenu(mActivity, mIvClose, mRlSelectKeshi, mTopBar,
                        new LsvPopCunFanKeshiAdapter(mActivity, null));
                keshiPopMenu.setOnItemClickListener(dbManager, new CunfangKeshiPopMenu.ItemClickListener() {

                    @Override
                    public void itemClickListener(MDepartmentBean.DetailsBean bean) {
                        mTvSelectKeshi.setText(bean.getDepartmentName());
                        strDeptCode = bean.getDeptCode();
                        setEmptyingInput();
                    }
                });

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
                setRegisterNum(strRegisterNum);

            }
        });

    }

    /**
     * 弹出设置权限监测到有未提交订单,是否继续?dialog
     */
    public void dialog() {
        UToast.showDialog(mActivity, mActivity.getString(R.string.check_activity_note), false, new DialogInf() {
            @Override
            public void commit() {
                intentActivity();//进入到另CommitAllTableActivity界面
            }
        });
    }

    /**
     * 清空页面输入
     */
    private void setEmptyingInput() {
        initData(null);
        mEditMain.setText("");
        mEditMain.setEmpty();
    }

    /**
     * 进入到另一个界面
     */
    private void intentActivity() {
        Bundle bundle = new Bundle();
        bundle.putInt("mDepartmentCollarID", mReg_id);
        Uintent.intentDIY(mActivity, CommitAllTableActivity.class, bundle);
    }

    /**
     * 设置注册证号
     *
     * @param strRegisterNum
     */
    private void setRegisterNum(String strRegisterNum) {
        mRegisterTvNum.setText(strRegisterNum);
        mBean.setRegisterNum(strRegisterNum);
    }


}

