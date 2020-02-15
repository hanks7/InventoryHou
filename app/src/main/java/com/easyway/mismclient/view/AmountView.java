package com.easyway.mismclient.view;

/**
 * Created by admin on 2018/4/16.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.easyway.mismclient.R;
import com.easyway.mismclient.utils.UToast;
import com.easyway.mismclient.utils.Ulog;


/**
 *
 * @Package:        com.easyway.mismclient.view.AmountView
 * @Author:         侯建军
 * @CreateDate:     2019/12/30 14:25
 * @Description:    自定义组件：购买数量，带减少增加按钮
 */
public class AmountView extends LinearLayout implements View.OnClickListener, TextWatcher {

    private static final String TAG = "AmountView";
    private int amount = 1; //购买数量

    private OnAmountChangeListener mListener;

    private EditText etAmount;
    private Button btnDecrease;
    private Button btnIncrease;
    private int position;
    private boolean isClick = true;

    public AmountView(Context context) {
        this(context, null);
    }

    public AmountView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_amount, this);
        etAmount = (EditText) findViewById(R.id.etAmount);
        btnDecrease = (Button) findViewById(R.id.btnDecrease);
        btnIncrease = (Button) findViewById(R.id.btnIncrease);
        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        etAmount.addTextChangedListener(this);

        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.AmountView);
        int btnWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btnWidth, LayoutParams.WRAP_CONTENT);
        int tvWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_tvWidth, 80);
        int tvTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_tvTextSize, 0);
        int btnTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btnTextSize, 0);
        obtainStyledAttributes.recycle();

        LayoutParams btnParams = new LayoutParams(btnWidth, LayoutParams.MATCH_PARENT);
        btnDecrease.setLayoutParams(btnParams);
        btnIncrease.setLayoutParams(btnParams);
        if (btnTextSize != 0) {
            btnDecrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
            btnIncrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
        }

        LayoutParams textParams = new LayoutParams(tvWidth, LayoutParams.MATCH_PARENT);
        etAmount.setLayoutParams(textParams);
        if (tvTextSize != 0) {
            etAmount.setTextSize(tvTextSize);
        }
    }

    public void setText(int num, int position) {
        this.position = position;
        amount = num;
        etAmount.setText(amount + "");
    }

    public int getNum() {
        int num = Integer.valueOf(etAmount.getText().toString().trim());
        return num;
    }

    public int getPosition() {
        return position;
    }

    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.mListener = onAmountChangeListener;
    }

    public void setClick(boolean click) {
        this.isClick = click;
        etAmount.setFocusable(click);
        etAmount.setFocusableInTouchMode(click);
    }

    @Override
    public void onClick(View v) {
        if (!isClick) {
            return;
        }

        switch (v.getId()) {
            case R.id.btnDecrease:
                if (amount == 0) break;
                amount--;
                break;
            case R.id.btnIncrease:
                amount++;
                break;

        }
        etAmount.setText(amount + "");
        etAmount.clearFocus();

        if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        Ulog.i("afterTextChanged", s.toString());
        try {
            amount = Integer.valueOf(s.toString());
        } catch (NumberFormatException e) {
            amount = 0;
            UToast.showText("请输入正确的数字");
        }

        if (mListener != null) {
            mListener.onAmountChange(this, amount);
            Ulog.i("amount", amount);
        }
    }


    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount);
    }

}
