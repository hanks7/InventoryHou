package com.easyway.mismclient.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseFragment;
import com.easyway.mismclient.model.UserModuleBean;
import com.easyway.mismclient.ui.business.CheckActivity;
import com.easyway.mismclient.ui.business.InventoryActivity;
import com.easyway.mismclient.utils.Uintent;
import com.easyway.mismclient.view.TopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.easyway.mismclient.base.BaseOFFLineApplication.APP;


public class HomeOneFragment extends BaseFragment {


    @BindView(R.id.fg_one_1)
    LinearLayout fgOne1;
    @BindView(R.id.fg_one_2)
    LinearLayout fgOne2;

    @BindView(R.id.topbar)
    TopBar topbar;

    Unbinder unbinder;
    @Override
    protected int getLayout() {
        return R.layout.fragment_home_one;
    }


    @OnClick({R.id.fg_one_1, R.id.fg_one_2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fg_one_1:
                Uintent.intentDIY(getActivity(), InventoryActivity.class);
                break;
            case R.id.fg_one_2:
                Uintent.intentDIY(getActivity(), CheckActivity.class);
                break;
        }
    }

    @Override
    protected void initView() {
        for (UserModuleBean userModuleBean :APP.getUserModel().GetModule()) {
            switch (userModuleBean.getPrivilegeCode()) {
                case "pandian":
                    userModuleBean.getPrivilegeCode().equals("pandian");
                    fgOne1.setVisibility(View.VISIBLE);
                    fgOne2.setVisibility(View.VISIBLE);
                    break;

                case "yanshou":
                    userModuleBean.getPrivilegeCode().equals("pandian");
                    fgOne2.setVisibility(View.VISIBLE);
                    break;
            }

        }
        topbar.setTitle(APP.getUserModel().getEmployeeName());
    }

    @Override
    protected void initData() {

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


}