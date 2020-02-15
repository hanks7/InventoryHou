package com.easyway.mismclient.ui.business;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseActivity;
import com.easyway.mismclient.model.MInventoryBean;
import com.easyway.mismclient.model.UserDeptBean;
import com.easyway.mismclient.ui.adapter.LsvPopDeptCodeAdapter;
import com.easyway.mismclient.utils.Base64Utils;
import com.easyway.mismclient.utils.UToast;
import com.easyway.mismclient.utils.http.HttpAdapter;
import com.easyway.mismclient.utils.http.OnResponseListener;
import com.easyway.mismclient.view.AmountView;
import com.easyway.mismclient.view.BarCodeEditView;
import com.easyway.mismclient.view.MyEditView;
import com.easyway.mismclient.view.MyTextView;
import com.easyway.mismclient.view.PopMenu;
import com.easyway.mismclient.view.TopBar;
import com.easyway.mismclient.view.interf.BarCodeCallBackListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.easyway.mismclient.base.BaseOFFLineApplication.APP;

/**
 * @Package: com.easyway.mismclient.ui.business.OperationRegisterActivity
 * @Author: 侯建军
 * @CreateDate: 2019/12/24 13:30
 * @Description: "手术登记"界面
 */
public class OperationRegisterActivity extends BaseActivity {

    @BindView(R.id.ac_op_edit_main)
    BarCodeEditView mEditMain;

    @BindView(R.id.topbar)
    TopBar topbar;

    @BindView(R.id.ac_op_edit_clinic_room_num)
    MyEditView mEditClinicRoomNum;
    @BindView(R.id.ac_op_edit_clinic_patient_num)
    MyEditView mEditClinicPatientNum;

    @BindView(R.id.ac_op_edt_name)
    MyTextView mEdtName;
    @BindView(R.id.ac_op_edt_sex)
    MyTextView mEdtSex;
    @BindView(R.id.ac_op_edt_patient_department)
    MyTextView mEdtPatientDepartment;
    @BindView(R.id.ac_op_edt_edt_patient_bed_num)
    MyTextView mEdtEdtPatientBedNum;
    @BindView(R.id.ac_op_edt_edt_fill_form_person)
    MyTextView mEdtEdtFillFormPerson;

    @BindView(R.id.ac_op_tv_executive_department_title)
    TextView mTvExecutiveDepartmentTitle;
    @BindView(R.id.ac_op_tv_select_executive_department)
    TextView mTvSelectExecutiveDepartment;
    @BindView(R.id.ac_op_iv_executive_close)
    ImageView mIvExecutiveClose;

    @BindView(R.id.ac_op_tv_operative_info_content)
    TextView mTvOperativeInfoContent;

    @BindView(R.id.ac_op_edt_product_num)
    AmountView mEdtProductNum;

    @BindView(R.id.ac_op_edit_product_name)
    MyTextView mEditProductName;
    @BindView(R.id.ac_op_edt_specs)
    MyTextView mEdtSpecs;
    @BindView(R.id.ac_op_tv_charge_price)
    MyTextView mTvChargePrice;
    @BindView(R.id.ac_op_tv_product_date)
    MyTextView mTvProductDate;
    @BindView(R.id.ac_op_tv_effective_date)
    MyTextView mTvEffectiveDate;
    @BindView(R.id.ac_op_tv_batch_date)
    MyTextView mTvBatchDate;
    @BindView(R.id.ac_op_tv_serial_num)
    MyTextView mTvSerialNum;
    @BindView(R.id.ac_op_tv_product_enterprise)
    MyTextView mTvProductEnterprise;
    @BindView(R.id.ac_op_tv_supply_enterprise)
    MyTextView mTvSupplyEnterprise;
    @BindView(R.id.ac_op_tv_this_time_total_num)
    EditText mTvThisTimeTotalNum;
    @BindView(R.id.ac_op_tv_this_time_total_price)
    MyTextView mTvThisTimeTotalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_register);
        ButterKnife.bind(this);
        intPickView();
        initListener();
    }

    private void initListener() {
        mEditMain.setOnEditorBarCodeTypeListener(new BarCodeCallBackListener() {
            @Override
            public void barCodeType(int barCodeType) {
                mEditMain.post(new Runnable() {
                    @Override
                    public void run() {
                        mEditMain.selectAll();
                    }
                });
                netGetDataFromCode();
            }

            @Override
            public void doClose() {
                setDataEmpty();
            }
        });

        mEditClinicRoomNum.setListener(new BarCodeCallBackListener() {
            @Override
            public void barCodeType(int barCodeType) {

            }

            @Override
            public void doClose() {
                setDataEmpty();
            }
        });
        mEditClinicPatientNum.setListener(new BarCodeCallBackListener() {
            @Override
            public void barCodeType(int barCodeType) {
                mEdtName.setText("name");
                mEdtSex.setText("男");
                mEdtPatientDepartment.setText("病人科室");
                mEdtEdtPatientBedNum.setText("123213");
                mEdtEdtFillFormPerson.setText(APP.userModel.getEmployeeName());
            }

            @Override
            public void doClose() {
                setDataEmpty();
            }
        });
        mEdtProductNum.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                mTvChargePrice.setText(amount * 135 + "");

                mTvThisTimeTotalNum.setText(amount + "");
                mTvThisTimeTotalPrice.setText(amount * 135 + "");
            }
        });
    }

    /**
     * 科室code
     */
    private String strDeptCode;

    /**
     * 选择科室
     */
    public void intPickView() {
        if (APP.userModel.GetUserDepartment() != null && APP.userModel.GetUserDepartment().size() == 1) {
            setStrDeptCode(0);
        }
        PopMenu popKeShi = new PopMenu(this, mIvExecutiveClose, mTvSelectExecutiveDepartment, new LsvPopDeptCodeAdapter(this, null));
        popKeShi.setOnItemClickListener(APP.userModel.GetUserDepartment(), new PopMenu.ItemClickListener() {
            @Override
            public void itemClickListener(int position) {
                setStrDeptCode(position);
            }
        });
    }

    /**
     * 设置科室
     *
     * @param position
     */
    private void setStrDeptCode(int position) {
        UserDeptBean bean = APP.userModel.GetUserDepartment().get(position);
        mTvSelectExecutiveDepartment.setText(APP.userModel.GetUserDepartment().get(position).getDepartmentName());
        strDeptCode = bean.getDeptCode();
    }

    MInventoryBean mBean;

    /**
     * 网络请求
     * i==0扫描的是主条码
     * i==1扫描的是次条码
     */
    private void netGetDataFromCode() {

        HttpAdapter.getService().mInventory(
                Base64Utils.setBase64(mEditMain.strBarCodeSource),//MDEzNDk4NzM1MDcyNDQzNQ%3D%3D
                Base64Utils.setBase64(mEditMain.strSubBarCodeSource),//MTcyMDA1MDAxMDIxMDEwMQ%3D%3D
                Base64Utils.setBase64(strDeptCode)//MjAwMDAw
        )
                .enqueue(new OnResponseListener<MInventoryBean>(this) {
                    @Override
                    public void onSuccess(MInventoryBean bean) {
                        mBean = bean;
                        mEditMain.selectAll();

                        initData(bean);

                    }

                    @Override
                    public void onFailure(String strToast) {
                        mBean = new MInventoryBean();
                        UToast.showSnackBar(mEditMain, strToast, R.color.red, R.color.white);
                        mEditMain.setEmpty();
                    }
                });
    }

    private void initData(MInventoryBean bean) {
        mEditProductName.setText(bean.getProductInfoName());
        mEdtSpecs.setText(bean.getSpecification());
        mTvSupplyEnterprise.setText(bean.getEnterpriseName());
        mTvProductEnterprise.setText(bean.getEnterpriseName());

        mTvProductDate.setText(bean.getProductionDate());
        mTvEffectiveDate.setText(bean.getEffectiveDate());
        mTvBatchDate.setText(bean.getLot());
        mTvSerialNum.setText(bean.getSN());
        mTvOperativeInfoContent.setText(bean.getProductInfoName());

        mEdtProductNum.setText(3, 0);
        mTvChargePrice.setText(3 * 135 + "");

        mTvThisTimeTotalNum.setText(3 + "");
        mTvThisTimeTotalPrice.setText(3 * 135 + "");

    }

    private void setDataEmpty() {
        mEditProductName.setText("");
        mEdtSpecs.setText("");
        mTvSupplyEnterprise.setText("");
        mTvProductEnterprise.setText("");

        mTvProductDate.setText("");
        mTvEffectiveDate.setText("");
        mTvBatchDate.setText("");
        mTvSerialNum.setText("");
        mTvOperativeInfoContent.setText("");

        mEdtProductNum.setText(0, 0);
        mTvChargePrice.setText("");

        mTvThisTimeTotalNum.setText("");
        mTvThisTimeTotalPrice.setText("");

        mEdtName.setText("");
        mEdtSex.setText("");
        mEdtPatientDepartment.setText("");
        mEdtEdtPatientBedNum.setText("");
        mEdtEdtFillFormPerson.setText("");
    }

    @OnClick({
            R.id.ac_op_edit_clinic_room_num,
            R.id.ac_op_btn_commit,
            R.id.ac_op_edit_clinic_patient_num})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_op_edit_clinic_room_num:
                break;
            case R.id.ac_op_btn_commit:
                setDataEmpty();
                break;
            case R.id.ac_op_edit_clinic_patient_num:
                break;
        }
    }
}
