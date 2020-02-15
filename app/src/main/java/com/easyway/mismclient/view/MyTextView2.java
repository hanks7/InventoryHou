package com.easyway.mismclient.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easyway.mismclient.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by User on 2017/4/19.
 */

public class MyTextView2 extends LinearLayout {


    private final float mContentSize;
    private final float mTitleSize;
    @BindView(R.id.item_input_tv_title)
    TextView tvTitleName;
    @BindView(R.id.item_input_edt_content)
    EditText edtContent;

    private String mTitle;
    private String mContent;

    public MyTextView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextView2(Context context) {
        this(context, null);
    }

    public MyTextView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_input_my_text_view2, this);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.input_test_view);
        ButterKnife.bind(this, view);

        mTitle = array.getString(R.styleable.input_test_view_input_title);
        mContent = array.getString(R.styleable.input_test_view_input_content);
        mTitleSize = array.getDimension(R.styleable.input_test_view_input_title_size, 14);
        mContentSize = array.getDimension(R.styleable.input_test_view_input_title_size, 14);
        int inputType = array.getInteger(R.styleable.input_test_view_input_type, 1);


        tvTitleName.setTextSize(mTitleSize);
        edtContent.setTextSize(mContentSize);
        switch (inputType) {
            case 1:
                edtContent.setInputType(InputType.TYPE_NULL);
                break;
            case 2:
                edtContent.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                break;
            case 3:
                edtContent.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            case 4:
                edtContent.setInputType(InputType.TYPE_NULL);
                break;
            default:
                edtContent.setInputType(InputType.TYPE_NULL);
        }
        edtContent.setInputType(inputType);

        tvTitleName.setText(mTitle);
        edtContent.setText(mContent);

    }


    public void setText(String content) {
        edtContent.setText(content);
    }

    public void setEmpty() {
        edtContent.setText("");
    }

    public String getText() {
        return edtContent.getText().toString().trim();
    }

}
