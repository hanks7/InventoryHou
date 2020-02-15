package com.easyway.mismclient.ui.business;

import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseActivity;
import com.easyway.mismclient.model.DetailListBean;
import com.easyway.mismclient.model.LingLiaoBean;
import com.easyway.mismclient.model.MProdEnterBean;
import com.easyway.mismclient.model.MProdEnterResultBean;
import com.easyway.mismclient.model.UserDeptBean;
import com.easyway.mismclient.ui.adapter.LsvLingYongBottomAdapter;
import com.easyway.mismclient.ui.adapter.LsvPopDeptCodeAdapter;
import com.easyway.mismclient.ui.adapter.LsvPopLingYingKeShiAdapter;
import com.easyway.mismclient.ui.adapter.LsvPopRegisterNumAdapter;
import com.easyway.mismclient.utils.Base64Utils;
import com.easyway.mismclient.utils.DialogInf;
import com.easyway.mismclient.utils.UToast;
import com.easyway.mismclient.utils.Ugson;
import com.easyway.mismclient.utils.Ulog;
import com.easyway.mismclient.utils.http.HttpAdapter;
import com.easyway.mismclient.utils.http.OnResponseListener;
import com.easyway.mismclient.view.interf.BarCodeCallBackListener;
import com.easyway.mismclient.view.BarCodeEditView;
import com.easyway.mismclient.view.MyEditView;
import com.easyway.mismclient.view.MyTextView;
import com.easyway.mismclient.view.PopMenu;
import com.easyway.mismclient.view.TopBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.easyway.mismclient.base.BaseConstants.MILLISECONDS;
import static com.easyway.mismclient.base.BaseOFFLineApplication.APP;

/**
 * @Package: com.easyway.mismclient.ui.business.ReturnActivity
 * @Author: 侯建军
 * @CreateDate: 2019/12/25 13:32
 * @Description: 还库界面
 */
public class ReturnActivity extends BaseActivity {
    @BindView(R.id.ac_check_edit_main)
    BarCodeEditView mEditMain;
    @BindView(R.id.ac_check_edit_ling_yong_ren)
    MyEditView mEditLingYongRen;


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

    @BindView(R.id.ac_check_tv_select_linyong_keshi)
    TextView mTvSelectLinyongKeshi;
    @BindView(R.id.ac_check_iv_linyong_close)
    ImageView mIvLinyongClose;
    @BindView(R.id.ac_check_rl_select_linyong_keshi)
    RelativeLayout mRlSelectLinyongKeshi;

    @BindView(R.id.ac_check_tv_select_chuku_keshi)
    TextView mTvSelectChukuKeshi;
    @BindView(R.id.ac_check_iv_chuku_close)
    ImageView mIvChukuClose;
    @BindView(R.id.ac_check_rl_select_chuku_keshi)
    RelativeLayout mRlSelectChukuKeshi;


    /**
     * 注册证popwindow
     */
    private PopMenu mZhuCeCodepop;
    private LsvPopRegisterNumAdapter popRegisterNumAdapter;
    private LsvLingYongBottomAdapter adapter;
    private Vibrator vibrator;//震动
    /**
     * 领用人bean
     */
    private LingLiaoBean mLingLiaoBean;
    /**
     * 主键id
     */
    private String mDepartmentCollarID;
    /**
     * 领用人
     */
    private String mHRCode;
    /**
     * 领用人名称
     */
    private String mEmployeeName;
    /**
     * 出库科室代码
     */
    private String mChuKuCode;
    /**
     * 领用科室代码
     */
    private String mLingYongCode;
    /**
     * 最终提交的javabean json
     */
    private MProdEnterBean mBean;
    /**
     * 领用科室的popwindow的listview的adapter
     */
    private LsvPopLingYingKeShiAdapter mLsvPopLingYingKeShiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return);
        ButterKnife.bind(this);

        initView();

        mEditLingYongRen.setListener(new BarCodeCallBackListener() {
            @Override
            public void barCodeType(int barCodeType) {
                netWork(mEditLingYongRen.getText());
            }

            @Override
            public void doClose() {

                initData(null);
                mTvSelectLinyongKeshi.setText("");//领用科室赋空
                mLingYongCode = null;
                mHRCode = null;
                if (mLsvPopLingYingKeShiAdapter != null) {
                    mLsvPopLingYingKeShiAdapter.updateList(null);
                }
            }
        });
        mEditLingYongRen.getFocus();
        mRegisterRlSelect.setVisibility(View.GONE);
    }


    /**
     * 网络请求领用人获取
     */
    private void netWork(String strHRCode) {
        HttpAdapter.getService().MEmployee(Base64Utils.setBase64(strHRCode))
                .enqueue(new OnResponseListener<LingLiaoBean>(this) {
                    @Override
                    public void onSuccess(LingLiaoBean bean) {
                        mLingLiaoBean = bean;
                        mEditLingYongRen.setText(bean.getHRCode() + "  " + bean.getEmployeeName());
                        mHRCode = bean.getHRCode();
                        mEmployeeName = bean.getEmployeeName();
                        setLingYongPopData();//初始化领用人popwindow;
                    }

                    @Override
                    public void onFailure(String strToast) {
                        super.onFailure(strToast);
                        mEditLingYongRen.selectAll();
                    }
                });
    }

    /**
     * 网络请求
     * i==0扫描的是主条码
     * i==1扫描的是次条码
     */
    private void netGetDataFromCode() {

        if (mHRCode == null) {
            mEditLingYongRen.selectAll();
            UToast.showText("请填写领用人工号");
            return;
        }
        if (mLingYongCode == null) {
            mEditMain.selectAll();
            UToast.showText("请选择领用科室");
            return;
        }
        if (mChuKuCode == null) {
            mEditMain.selectAll();
            UToast.showText("请选择出库科室");
            return;
        }
        netWork();
    }

    private void netWork() {
        HttpAdapter.getService().MDepartmentCollarBack(
                Base64Utils.setBase64(mEditMain.strBarCodeSource),//MDEzNDk4NzM1MDcyNDQzNQ%3D%3D
                Base64Utils.setBase64(mEditMain.strSubBarCodeSource),//MTcyMDA1MDAxMDIxMDEwMQ%3D%3D
                Base64Utils.setBase64(mLingYongCode),//MTcyMDA1MDAxMDIxMDEwMQ%3D%3D
                Base64Utils.setBase64(mChuKuCode),//MTcyMDA1MDAxMDIxMDEwMQ%3D%3D
                Base64Utils.setBase64(mHRCode)//MTcyMDA1MDAxMDIxMDEwMQ%3D%3D
        )
                .enqueue(new OnResponseListener<MProdEnterBean>(this) {
                    @Override
                    public void onSuccess(MProdEnterBean bean) {

                        mEditMain.selectAll();
                        initData(bean);


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
     * 领用产品提交验证
     *
     * @param type
     */
    private void clickCommit(final int type) {
        if (mHRCode == null) {
            setVibrateAndred();
            UToast.showText("请填写领用人工号");
            return;
        }
        if (mLingYongCode == null) {
            setVibrateAndred();
            UToast.showText("请选择领用科室");
            return;
        }
        if (mChuKuCode == null) {
            setVibrateAndred();
            UToast.showText("请选择出库科室");
            return;
        }
        if (TextUtils.isEmpty(mEditMain.getText())) {
            setVibrateAndred();
            UToast.showText("请先扫描主码和次码");
            return;
        }
//        if (TextUtils.isEmpty(mRegisterTvNum.getText())) {
//            setVibrateAndred();
//            UToast.showText("请选择注册证");
//            return;
//        }
        if (mBean.getDetailList().size() == 0) {
            setVibrateAndred();
            UToast.showText("没有库存");
            return;
        }

        mBean.setBarCodeSource(mEditMain.strBarCodeSource);
        mBean.setSubBarCodeSource(mEditMain.strSubBarCodeSource);
        mBean.setDepartmentCollarID(mDepartmentCollarID);
        mBean.setHRCode(mHRCode);
        mBean.setEmployeeName(mEmployeeName);

        mBean.setTreasuryDepartment(mChuKuCode);
        mBean.setDeptCode(mLingYongCode);
        mBean.setOperateName(APP.userModel.getEmployeeName());
        mBean.setOperateID(APP.userModel.getHRCode());

        if (mBean.getDetailList() != null) {
            if (mBean.getDetailList().size() == 1) {
                UToast.showDialog(mActivity, getString(R.string.isConfirmSubmit), false, new DialogInf() {
                    @Override
                    public void commit() {
                        netCommit(type);
                    }
                });
                return;
            }
        }
        netCommit(type);
    }

    /**
     * 领用产品提交验证
     *
     * @param type
     */
    private void netCommit(final int type) {
        HttpAdapter.getService().MDepartmentCollarBack(
                Ugson.toJson(mBean), type)
                .enqueue(new OnResponseListener<MProdEnterResultBean>(this) {
                    @Override
                    public void onSuccess(MProdEnterResultBean bean) {

                        if (type == 1) {
                            mDepartmentCollarID = bean.getDepartmentCollarID();
                            UToast.showSnackBar(mBtnComfirm, getString(R.string.commit_success), R.color.green, R.color.white);
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

    @OnClick({R.id.ac_check_btnComfirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_check_btnComfirm:
                clickCommit(0);
                break;
        }
    }


    /**
     * 初始化领用科室;
     */
    private void setLingYongPopData() {

        mLsvPopLingYingKeShiAdapter = new LsvPopLingYingKeShiAdapter(mActivity, mLingLiaoBean.getUserDept());
        if (mLingLiaoBean.getUserDept() != null && mLingLiaoBean.getUserDept().size() == 1) {
            mTvSelectLinyongKeshi.setText(mLingLiaoBean.getUserDept().get(0).getDepartmentName());
            mLingYongCode = mLingLiaoBean.getUserDept().get(0).getDeptCode();
        }
        PopMenu mLingYongPopMenu = new PopMenu(mActivity, mIvLinyongClose, mRlSelectLinyongKeshi, mLsvPopLingYingKeShiAdapter);

        mLingYongPopMenu.setOnItemClickListener(mLingLiaoBean.getUserDept(), new PopMenu.ItemClickListener() {
            @Override
            public void itemClickListener(int position) {
                mTvSelectLinyongKeshi.setText(mLingLiaoBean.getUserDept().get(position).getDepartmentName());
                mLingYongCode = mLingLiaoBean.getUserDept().get(position).getDeptCode();
                Ulog.i("mLingYongCode", mLingYongCode);
                setEmptyingInput();
            }
        });

    }

    /**
     * 设置出库科室
     */
    private void setCHukuKeShi() {

        final List<UserDeptBean> departmentList = APP.userModel.GetUserDepartment();
        if (departmentList != null && departmentList.size() == 1) {
            UserDeptBean bean = departmentList.get(0);
            mTvSelectChukuKeshi.setText(bean.getDepartmentName());
            mChuKuCode = bean.getDeptCode();
        }

        PopMenu popChukuKeShi = new PopMenu(this, mIvChukuClose, mRlSelectChukuKeshi, new LsvPopDeptCodeAdapter(this, null));
        popChukuKeShi.setOnItemClickListener(departmentList, new PopMenu.ItemClickListener() {
            @Override
            public void itemClickListener(int position) {
                UserDeptBean bean = departmentList.get(position);
                mTvSelectChukuKeshi.setText(departmentList.get(position).getDepartmentName());
                mChuKuCode = bean.getDeptCode();
                Ulog.i("mChuKuCode", mChuKuCode);
            }
        });
    }


    /**
     * 初始化页面
     */
    private void initView() {

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(30);

        adapter = new LsvLingYongBottomAdapter(this, null, getString(R.string.allowance));
        mLsv.setAdapter(adapter);
        initEditMain();//初始化主次条码控件
        setCHukuKeShi();
        popRegisterNumAdapter = new LsvPopRegisterNumAdapter(mActivity, null);
        mZhuCeCodepop = new PopMenu(mActivity, mRegisterIvClose, mRegisterRlSelect, popRegisterNumAdapter);
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
        } else {
            mBean = bean;
        }

        intRegisterPop();//给注册证的popWindow赋值

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

        if (mBean.getDetailList() != null) {
            if (mBean.getDetailList().size() == 1) {
                mBean.getDetailList().get(0).setFactAmount(1);
            } else {
                for (DetailListBean listBean : mBean.getDetailList()) {
                    listBean.setFactAmount(0);
                }
            }
        }
        adapter.setIsClick(mBean.getDetailList(), true);//sn为空时可以添加数字,否则就不可以

        popRegisterNumAdapter.updateList(mBean.getLicenceList());
        if (mBean.getLicenceList() != null && mBean.getLicenceList().size() == 1) {
            setRegisterNum(mBean.getLicenceList().get(0).getRegistrationCard());
        }
    }


    /**
     * 给注册证的popWindow赋值
     */
    public void intRegisterPop() {
        mZhuCeCodepop.setOnItemClickListener(mBean.getLicenceList(), new PopMenu.ItemClickListener() {
            @Override
            public void itemClickListener(int position) {
                String strRegisterNum = mBean.getLicenceList().get(position).getRegistrationCard();
                setRegisterNum(strRegisterNum);
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
                clickCommit(1);
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
     * 设置注册证号
     *
     * @param strRegisterNum
     */
    private void setRegisterNum(String strRegisterNum) {
        mRegisterTvNum.setText(strRegisterNum);
        mBean.setRegisterNum(strRegisterNum);
    }

    /**
     * 调用震动
     */
    private void setVibrateAndred() {

        vibrator.vibrate(MILLISECONDS);
    }


}
