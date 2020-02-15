package com.easyway.mismclient.ui.offline;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseActivity;
import com.easyway.mismclient.dao.DbHelper;
import com.easyway.mismclient.model.DetailListBean;
import com.easyway.mismclient.model.LicenceListBean;
import com.easyway.mismclient.model.OfflineInverntoryBean;
import com.easyway.mismclient.model.UserDeptBean;
import com.easyway.mismclient.ui.adapter.LsvBusinessBaseAdapter;
import com.easyway.mismclient.ui.adapter.LsvPopDeptCodeAdapter;
import com.easyway.mismclient.ui.adapter.LsvPopRegisterNumAdapter;
import com.easyway.mismclient.utils.UToast;
import com.easyway.mismclient.utils.Ulog;
import com.easyway.mismclient.utils.http.ImpDBSuccessFailListener;
import com.easyway.mismclient.view.BarCodeEditView;
import com.easyway.mismclient.view.MyTextView;
import com.easyway.mismclient.view.PopMenu;
import com.easyway.mismclient.view.interf.BarCodeCallBackListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 侯建军 47466436@qq.com
 * @date 2018/5/28
 * @description 离线盘点界面
 */
public class InventoryOffActivity extends BaseActivity {
    @BindView(R.id.ac_check_edit_main)
    BarCodeEditView mEditMain;

    @BindView(R.id.ac_check_rl_select_keshi)
    RelativeLayout mRlSelectDeptPop;
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

    @BindView(R.id.ac_inventory_lsv)
    ListView mLsv;

    @BindView(R.id.ac_check_register_tv_num)
    TextView mRegisterTvNum;
    @BindView(R.id.ac_check_register_iv_close)
    ImageView mRegisterIvClose;
    @BindView(R.id.ac_check_register_rl_select)
    RelativeLayout mRegisterRlSelect;

    @BindView(R.id.ac_check_btnComfirm)
    Button mBtnComfirm;

    private String numDeptCode;//科室号
    private String numRegister;//注册证号


    private LsvPopRegisterNumAdapter popRegisterNumAdapter;//注册证
    private LsvPopDeptCodeAdapter lsvPopDeptCodeAdapter;//存放科室
    private LsvBusinessBaseAdapter adapter;//listview的adapter

    private PopMenu mDeptPop;//存放科室
    private PopMenu mRegisterPop;//注册证号pop

    private DbHelper mDB;
    private OfflineInverntoryBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_off);
        ButterKnife.bind(this);
        mDB = new DbHelper(mActivity);
        initView();
        initDeptPop();//初始化存放科室
        initEditMain();//初始化主次条码控件


    }

    private void initView() {
        adapter = new LsvBusinessBaseAdapter(this, null);
        popRegisterNumAdapter = new LsvPopRegisterNumAdapter(this, null);
        lsvPopDeptCodeAdapter = new LsvPopDeptCodeAdapter(this, null);

        mRegisterPop = new PopMenu(this, mRegisterIvClose, mRegisterRlSelect, popRegisterNumAdapter);
        mLsv.setAdapter(adapter);

        mDeptPop = new PopMenu(this, mIvClose, mRlSelectDeptPop, lsvPopDeptCodeAdapter);
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
    private void initData(OfflineInverntoryBean bean) {
        if (bean == null) {
            mBean = new OfflineInverntoryBean();
            lsvPopDeptCodeAdapter.updateList(null);
            popRegisterNumAdapter.updateList(null);
        } else {
            mBean = bean;
        }


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

        adapter.setIsClick(mBean.getDetailList(), true);//sn为空时可以添加数字,否则就不可以


    }

    /**
     * 得到注册证list
     *
     * @return
     */
    private ArrayList<LicenceListBean> getlicenceList() {
        return mDB.queryRegistrationCard(mEditMain.strBarCode, mEditMain.barcodeObject);
    }


    /**
     * 初始化主次条码控件
     * <p>
     * "BarType": "R",
     * "Barcode": "1719050010170616V",
     * "BarcodeControlSymbol": "~9~9v[\"cc&,R,",
     * "BarcodeSrc": "~9~9v[\"cc&,R,1719050010170616V",
     * "ErrNo": 0,
     * "ExpirationDate": "2019-05-31",
     * "Lot": "170616V",
     * "RetryTimes": 0,
     * "ScanerSN": "30002",
     * "SubCode": "1719050010170616V",
     * "barcodeType": "Secondary"
     */
    private void initEditMain() {
        mEditMain.getFocus();
        mEditMain.setOnEditorBarCodeTypeListener(new  BarCodeCallBackListener() {
            @Override
            public void doClose() {
                setEmptyingInput();
            }

            @Override
            public void barCodeType(int barCodeType) {
                Ulog.i("barCodeType", barCodeType);
                mBean = mDB.queryProductInfo(mEditMain.strBarCode);

                if (mBean != null) {
                    mBean.setRegisterNum(numRegister);
                    mBean.setSN(mEditMain.barcodeObject.getSN());
                    mBean.setLot(mEditMain.barcodeObject.getLot());
                    mBean.setEffectiveDate(mEditMain.barcodeObject.getExpirationDate());
                    mBean.setProductionDate(mEditMain.barcodeObject.getMakeDate());
                }


                mBtnComfirm.setBackground(mActivity.getResources().getDrawable(R.drawable.selectable_background));
                initData(mBean);
                intRegisterPop();//给注册证的popWindow赋值
                mEditMain.selectAll();
                if (mBean.getProductInfoID() == 0) {
                    UToast.showSnackBar(mBtnComfirm, "此科室不存在概产品", R.color.red, R.color.white);
                    return;
                }

                if (mBean.getDetailList()!=null&&mBean.getDetailList().size()==0&&mEditMain.isHasSubCode()) {
                    UToast.showSnackBar(mBtnComfirm, "没有库存", R.color.red, R.color.white);
                    return;
                }
                if (mBean.getDetailList()==null&&mEditMain.isHasSubCode()) {
                    UToast.showSnackBar(mBtnComfirm, "没有库存", R.color.red, R.color.white);
                    return;
                }
                if (mBean.getIsChecked()==1) {
                    UToast.showSnackBar(mBtnComfirm, "已经别扫描过了", R.color.red, R.color.white);
                    return;
                }

            }
        });
    }


    /**
     * 点击确定的业务
     *
     * @param view
     */
    public void netCommit(final View view) {


        if (TextUtils.isEmpty(mEditMain.getText())) {
            UToast.showSnackBar(mBtnComfirm, "请先扫描主码和次码", R.color.red, R.color.white);
            return;
        }
        if (TextUtils.isEmpty(mRegisterTvNum.getText())) {
            UToast.showSnackBar(mBtnComfirm, "请选择注册证", R.color.red, R.color.white);
            return;
        }
        mBean.setIsChecked(1);//设置已经被扫描过了
        mDB.updateDetailListBean(mBean.getDetailList(), mBean.getProductInfoID(),new ImpDBSuccessFailListener<String>() {
            @Override
            public void onSuccess(String strContent) {
                UToast.showSnackBar(mBtnComfirm, strContent, R.color.green, R.color.white);
                setEmptyingInput();
            }

            @Override
            public void onFail(String strToast) {
                UToast.showSnackBar(mBtnComfirm, strToast, R.color.red, R.color.white);
            }
        });

    }


    /**
     * 初始化存放科室
     */
    private void initDeptPop() {


        final ArrayList<UserDeptBean> userDeptBeanList = mDB.queryDepartments();

        if (userDeptBeanList.size() == 1) {
            UserDeptBean bean = userDeptBeanList.get(0);
            mTvSelectKeshi.setText(userDeptBeanList.get(0).getDepartmentName());
            numDeptCode = bean.getDeptCode();
        } else if (userDeptBeanList.size() == 0) {
            mTvSelectKeshi.setText(null);
            numDeptCode = null;
        }
//        mDeptPop.setOnItemClickListener(userDeptBeanList, new PopMenu.ItemClickListener() {
//            @Override
//            public void itemClickListener(final int position) {
//

//
//            }
//        });

    }

    /**
     * 给注册证的popWindow赋值
     */
    public void intRegisterPop() {
        if (!mEditMain.isHasSubCode()) {
            mRegisterTvNum.setText(null);
            numRegister = null;
            popRegisterNumAdapter.updateList(null);
            return;
        }
        final ArrayList<LicenceListBean> list = getlicenceList();

        mRegisterPop.setOnItemClickListener(list, new PopMenu.ItemClickListener() {
            @Override
            public void itemClickListener(int position) {
                String strRegisterNum = list.get(position).getRegistrationCard();
                setRegisterNum(strRegisterNum);
            }
        });

        if (list.size() == 1) {
            setRegisterNum(list.get(0).getRegistrationCard());
        }


    }

    /**
     * 设置注册证号
     *
     * @param strRegisterNum
     */
    private void setRegisterNum(String strRegisterNum) {

        mRegisterTvNum.setText(strRegisterNum);
        numRegister = strRegisterNum;
        mBean.setRegisterNum(strRegisterNum);
        ArrayList<DetailListBean> list = mDB.queryProductInfoList(mEditMain.strBarCode,
                mEditMain.barcodeObject, numDeptCode, numRegister);
        mBean.setDetailList(list);

        initData(mBean);
    }


    /**
     * 清空页面输入
     */
    private void setEmptyingInput() {
        mEditMain.setText("");
        mEditMain.setEmpty();
        initData(null);

    }


}
