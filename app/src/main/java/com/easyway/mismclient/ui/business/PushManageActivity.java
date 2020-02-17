package com.easyway.mismclient.ui.business;

import android.os.Bundle;
import android.view.View;

import com.easyway.mismclient.R;
import com.easyway.mismclient.base.BaseActivity;
import com.easyway.mismclient.model.MWareHouseBean;
import com.easyway.mismclient.utils.UToast;
import com.easyway.mismclient.utils.Ulog;
import com.easyway.mismclient.utils.http.HttpAdapter;
import com.easyway.mismclient.utils.http.OnResponseListener;

public class PushManageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_manage);
    }

    private void netWork() {
        HttpAdapter.getService().MWareHouse(
                "QTRK201810230003"
        )
                .enqueue(new OnResponseListener<MWareHouseBean>(this) {
                    @Override
                    public void onSuccess(MWareHouseBean bean) {
                        UToast.showText("onSuccess");
                        Ulog.i("onSuccess",bean.toString());

                    }

                    @Override
                    public void onFailure(String strToast) {
                        UToast.showText("onFailure");

                    }
                });
    }

    public void test(View view) {
        netWork();
    }
}
