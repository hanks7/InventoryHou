package com.easyway.mismclient.ui.business;

import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseActivity;
import com.easyway.mismclient.base.BaseModel;
import com.easyway.mismclient.model.MInventoryBean;
import com.easyway.mismclient.model.UserDeptBean;
import com.easyway.mismclient.ui.adapter.LsvInventoryAdapter;
import com.easyway.mismclient.ui.adapter.LsvPopDeptCodeAdapter;
import com.easyway.mismclient.ui.adapter.LsvPopRegisterNumAdapter;
import com.easyway.mismclient.utils.Base64Utils;
import com.easyway.mismclient.utils.DialogInf;
import com.easyway.mismclient.utils.UToast;
import com.easyway.mismclient.utils.Ugson;
import com.easyway.mismclient.utils.http.HttpAdapter;
import com.easyway.mismclient.utils.http.OnResponseListener;
import com.easyway.mismclient.view.interf.BarCodeCallBackListener;
import com.easyway.mismclient.view.BarCodeEditView;
import com.easyway.mismclient.view.MyDialog;
import com.easyway.mismclient.view.MyTextView;
import com.easyway.mismclient.view.PopMenu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.easyway.mismclient.base.BaseConstants.IS_RELEASE;
import static com.easyway.mismclient.base.BaseConstants.MILLISECONDS;
import static com.easyway.mismclient.base.BaseOFFLineApplication.APP;
import static com.easyway.mismclient.utils.UtilDate.getCurrentTime;

/**
 * @author 侯建军 47466436@qq.com
 * @date 2018/5/28
 * @description 库存盘点界面
 */
public class InventoryActivity extends BaseActivity {
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
    @BindView(R.id.ac_check_edit_inventory_amount)
    MyTextView mEdtInventoryAmount;

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

    private String strDeptCode;
    private LsvInventoryAdapter adapter;
    private MInventoryBean mBean;
    private PopMenu popZhuCeCode;//注册证号
    private LsvPopRegisterNumAdapter popRegisterNumAdapter;
    private Vibrator vibrator;//震动


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_inventory);
        ButterKnife.bind(this);
        initView();
        mEditMain.getFocus();
    }

    private void test() {
        if (IS_RELEASE) return;

        List<String> list = new ArrayList<>();
        list.add("cccccccc");
        list.add("bbbbbb");
        list.add("ddddddddd");
        list.add("ddddddddd");
        list.add("ddddddddd");
        list.add("ddddddddd");
        list.add("ddddddddd");
        list.add("ddddddddd");
        list.add("ddddddddd");
        list.add("ddddddddd");
        list.add("ddddddddd");
        MyDialog.show(this, false, list);
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
    private void initData(MInventoryBean bean) {
        if (bean == null) {
            mBean = new MInventoryBean();
            mBean.setBuy("-1");
            mBtnComfirm.setBackground(mActivity.getResources().getDrawable(R.drawable.selectable_background));
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

//        adapter.setIsClick(mBean.getDetailList(), TextUtils.isEmpty(mBean.getSN()));//sn为空时可以添加数字,否则就不可以
        adapter.setIsClick(mBean.getDetailList(), true);//sn为空时可以添加数字,否则就不可以
        popRegisterNumAdapter.updateList(mBean.getLicenceList());
        if (mBean.getLicenceList() != null && mBean.getLicenceList().size() == 1) {
            setRegisterNum(mBean.getLicenceList().get(0).getRegistrationCard());
        }
        mEdtInventoryAmount.setVisibility(View.GONE);
        if (mBean.getDetailList() != null && mBean.getDetailList().size() > 0) {
            mEdtInventoryAmount.setText(mBean.getDetailList().get(0).getInventoryAmount() + "");
            mEdtInventoryAmount.setVisibility(View.VISIBLE);
        }
        checkEffectiveDate(mBean.getEffectiveDate());//检查有效期日期,临近一个月是给用户提示
    }


    private void initView() {

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(30);

        intPickView();
        adapter = new LsvInventoryAdapter(this, null);
        mLsv.setAdapter(adapter);
        initEditMain();//初始化主次条码控件

    }

    /**
     * 初始化主次条码控件
     */
    private void initEditMain() {
        mEditMain.setOnEditorBarCodeTypeListener(new BarCodeCallBackListener() {
            @Override
            public void barCodeType(int barCodeType) {
                netGetDataFromCode();
            }

            @Override
            public void doClose() {
                setEmptyingInput();
            }


        });
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
     * 网络请求
     * i==0扫描的是主条码
     * i==1扫描的是次条码
     */
    private void netGetDataFromCode() {
        if (strDeptCode == null) {
            UToast.showText("请选择科室");
            return;
        }
        HttpAdapter.getService().mInventory(
                Base64Utils.setBase64(mEditMain.strBarCodeSource),//MDEzNDk4NzM1MDcyNDQzNQ%3D%3D
                Base64Utils.setBase64(mEditMain.strSubBarCodeSource),//MTcyMDA1MDAxMDIxMDEwMQ%3D%3D
                Base64Utils.setBase64(strDeptCode)//MjAwMDAw
        )
                .enqueue(new OnResponseListener<MInventoryBean>(this) {
                    @Override
                    public void onSuccess(MInventoryBean bean) {
                        mEditMain.selectAll();
                        mBtnComfirm.setBackground(mActivity.getResources().getDrawable(R.drawable.selectable_background));
                        initData(bean);

//                        test();
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
     *
     * @param view
     */
    public void netCommit(final View view) {
        if (TextUtils.isEmpty(mEditMain.getText())) {
            setVibrateAndred();
            UToast.showText("请先扫描主码和次码");
            return;
        }
        if (TextUtils.isEmpty(mRegisterTvNum.getText())) {
            setVibrateAndred();
            UToast.showText("请选择注册证");

            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);//加载动画资源文件
            mRegisterRlSelect.startAnimation(shake); //给组件播放动画效果


            return;
        }

        netWork(0);
    }

    /**
     * 网络请求,当bean.getCode()==2需要重新调用此网络请求,并给用dialog提示
     */
    private void netWork(final int type) {
        HttpAdapter.getService().MInventory(
                Ugson.toJson(mBean), type)
                .enqueue(new OnResponseListener<BaseModel>(this) {
                    @Override
                    public void onSuccess(BaseModel bean) {

                        if (type == 1) {//表示为提交
                            UToast.showSnackBar(mBtnComfirm, getString(R.string.commit_success), R.color.green, R.color.white);
                            setEmptyingInput();
                        } else {//表示为校验,校验之后需要重新提交
                            netWork(1);
                        }

                    }

                    @Override
                    public void onFailure(String strToast) {
                        UToast.showSnackBar(mBtnComfirm, strToast, R.color.red, R.color.white);
                        vibrator.vibrate(MILLISECONDS);
                    }

                    @Override
                    public void onFailure2(String strToast) {
                        UToast.showDialog(mActivity, strToast, false, new DialogInf() {
                            @Override
                            public void commit() {
                                netWork(1);
                            }
                        });
                    }
                });
    }


    /**
     * 选择科室
     */
    public void intPickView() {
        PopMenu popKeShi = new PopMenu(this, mIvClose, mRlSelectKeshi, new LsvPopDeptCodeAdapter(this, null));
        popKeShi.setOnItemClickListener(APP.userModel.GetUserDepartment(), new PopMenu.ItemClickListener() {
            @Override
            public void itemClickListener(int position) {
                UserDeptBean bean = APP.userModel.GetUserDepartment().get(position);
                mTvSelectKeshi.setText(APP.userModel.GetUserDepartment().get(position).getDepartmentName());
                strDeptCode = bean.getDeptCode();
                setEmptyingInput();
            }
        });

        popRegisterNumAdapter = new LsvPopRegisterNumAdapter(this, null);
        popZhuCeCode = new PopMenu(this, mRegisterIvClose, mRegisterRlSelect, popRegisterNumAdapter);
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
     * 调用震动并将确定按钮设置为红色
     */
    private void setVibrateAndred() {
        vibrator.vibrate(MILLISECONDS);
        //mBtnComfirm.setBackgroundColor(mActivity.getResources().getColor(R.color.red));
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
     * 检查生产日期,临近一个月是给用户提示
     */
    private void checkEffectiveDate(String strEffectiveDate) {
        if (TextUtils.isEmpty(strEffectiveDate)) {
            return;
        }
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        long day = 0;
        try {
            //这里添加24 * 60 * 60 * 1000 也就是一天的时间 假设2019年11月11日为有效期,那么那天没过期,如果不加24 * 60 * 60 * 1000,则那天已经过期了
            day = (f.parse(strEffectiveDate).getTime() - getCurrentTime()) / 1000 / 60 / 60 / 24;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (day < 0) {
            UToast.showDialog2(this, "该产品有效日期为" + strEffectiveDate + "\n已经过期", true, null);
            return;
        }
        if (day == 0) {
            UToast.showDialog2(this, "该产品有效日期为" + strEffectiveDate + "\n明天过期", true, null);
            return;
        }
        if (day <= 30) {
            UToast.showDialog2(this, "该产品有效日期为" + strEffectiveDate + "\n还剩" + day + "天即将过期！", true, null);
            return;
        }
    }
}
