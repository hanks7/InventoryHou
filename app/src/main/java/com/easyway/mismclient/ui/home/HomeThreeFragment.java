package com.easyway.mismclient.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseActivity;
import com.easyway.mismclient.base.BaseFragment;
import com.easyway.mismclient.model.UpdateBean;
import com.easyway.mismclient.ui.login.LoginActivity;
import com.easyway.mismclient.utils.UTools;
import com.easyway.mismclient.utils.Uintent;
import com.easyway.mismclient.utils.http.HttpAdapter;
import com.easyway.mismclient.utils.http.OnResponseListener;
import com.easyway.mismclient.utils.update.UpdateManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.easyway.mismclient.base.BaseOFFLineApplication.APP;


public class HomeThreeFragment extends BaseFragment {

    @BindView(R.id.fg_mine_hrcode)
    TextView tvHrcode;
    @BindView(R.id.fg_mine_EmployeeName)
    TextView tvEmployeeName;
    @BindView(R.id.fg_mine_ValidCode)
    TextView tvValidCode;
    @BindView(R.id.fg_mine_HosID)
    TextView tvHosID;
    @BindView(R.id.fg_mine_ll_detail)
    View llDetail;
    private Unbinder unbinder;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home_three;
    }

    @Override
    protected void initView() {
        UTools.addViewPaddingTop(llDetail, (Activity) getContext());
    }


    @Override
    protected void initData() {
        tvHrcode.setText("HRCode:  " + APP.userModel.getHRCode());
        tvEmployeeName.setText("用户名:  " + APP.userModel.getEmployeeName());
        tvValidCode.setText("ValidCode:  " + APP.userModel.GetValidCode());
        tvHosID.setText("HospitalID:  " + APP.userModel.getHospitalID());
    }

    @OnClick({R.id.fg_mine_update, R.id.fg_mine_quit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fg_mine_update:
                netWork();
                break;
            case R.id.fg_mine_quit:
                APP.quitLogin();
                Uintent.intentDIY(this.getActivity(), LoginActivity.class);
                this.getActivity().finish();
                break;
        }
    }

    /**
     * 网络请求
     */
    private void netWork() {
        HttpAdapter.getService().autoUpdate().enqueue(new OnResponseListener<UpdateBean>((BaseActivity)getActivity()) {
                    @Override
                    public void onSuccess(UpdateBean bean) {
                        UpdateManager updateManager = new UpdateManager(getActivity(), bean);
                        updateManager.showDownloadDialog(true);
                    }
                });
    }

    @Override
    protected void onVisible() {
    }

    @Override
    protected void onInvisible() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @OnClick(R.id.fg_mine_quit)
    public void onViewClicked() {


    }


}