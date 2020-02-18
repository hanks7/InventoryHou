package com.easyway.mismclient.view;

import android.app.Service;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easyway.BarcodeObject;
import com.easyway.mismclient.R;
import com.easyway.mismclient.utils.UProperTies;
import com.easyway.mismclient.utils.UToast;
import com.easyway.mismclient.utils.Ulog;
import com.easyway.mismclient.view.interf.BarCodeCallBackListener;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.easyway.mismclient.base.BaseConstants.IS_RELEASE;
import static com.easyway.mismclient.base.BaseConstants.MILLISECONDS;
import static com.easyway.mismclient.utils.UTools.getBarcodeObject;

/**
 * Created by User on 2017/4/19.
 */

public class BarCodeEditView extends LinearLayout {


    private final float mContentSize;
    private final float mTitleSize;
    /**
     * 声明一个振动器对象
     */
    private Vibrator mVibrator;
//    /**
//     * 音源
//     */
//    int soundId;
//    /**
//     * 声音池
//     */
//    SoundPool sp;

    @BindView(R.id.item_input_iv_close)
    ImageView ivClose;
    @BindView(R.id.item_input_tv_title)
    TextView tvTitleName;
    @BindView(R.id.item_input_edt_content)
    EditText edtContent;

    private String mTitle;
    private String mContent;


    public String strBarCodeSource;//带乱码的主码 ,传参时使用的是带乱码的
    public String strSubBarCodeSource;//带乱码的次码 ,传参时使用的是带乱码的

    public String strBarCode;//去除乱码的主码
    public String strSubBarCode;//去除乱码的次码

    public BarcodeObject barcodeObject;

    /**
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
     * <p>
     * "BarType": "R",
     * "Barcode": "010082700209497017201130108409387",
     * "BarcodeControlSymbol": "\"x\"i*Y|sz`,R,",
     * "BarcodeSrc": "\"x\"i*Y|sz`,R,010082700209497017201130108409387",
     * "Country": "美国",
     * "ErrNo": 0,
     * "ExpirationDate": "2020-11-30",
     * "Lot": "8409387",
     * "MainCode": "0100827002094970",主码
     * "Manufacturer": "70020",
     * "Merchandise": "9497",
     * "Package": "0",
     * "RetryTimes": 0,
     * "ScanerSN": "30002",
     * "SubCode": "17201130108409387",次码
     * "barcodeType": "Concatenated"
     */


    public BarCodeEditView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BarCodeEditView(Context context) {
        this(context, null);
    }

    public BarCodeEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_input_my_edit_view, this);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.input_test_view);
        ButterKnife.bind(this, view);

        mTitle = array.getString(R.styleable.input_test_view_input_title);
        mContent = array.getString(R.styleable.input_test_view_input_content);
        mTitleSize = array.getDimension(R.styleable.input_test_view_input_title_size, 14);
        mContentSize = array.getDimension(R.styleable.input_test_view_input_title_size, 14);


        tvTitleName.setTextSize(mTitleSize);
        edtContent.setTextSize(mContentSize);

        tvTitleName.setText(mTitle);
        edtContent.setText(mContent);

        //添加提示声音
//        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
//        soundId = sp.load(context, R.raw.beep, 1);

        //添加震动
        mVibrator = (Vibrator) getContext().getSystemService(Service.VIBRATOR_SERVICE);
    }


    /**
     * 设置震动提示音
     */
    private void playVoiceAndVibrator() {
//        sp.play(soundId, 1, 1, 0, 0, 1);//播放声音
        mVibrator.vibrate(MILLISECONDS);//震动
    }

    public void setOnEditorBarCodeTypeListener(final BarCodeCallBackListener listener) {
        edtContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                test(textView);//测试上线后关闭
                playVoiceAndVibrator();//设置震动提示音
                if (TextUtils.isEmpty(textView.getText().toString())) {
                    UToast.showText("输入不能为空");
                    return false;
                }
                String strEditMain = getText().replace('', ',');
                Ulog.i("onEditorAction", strEditMain);
                barcodeObject = getBarcodeObject(strEditMain);

                if (i == 5 || i == 0 || i == 6) {
                    if (barcodeObject.getBarcodeType() == null) {
                        UToast.showText("条码格式不正确");
                        return false;
                    }
                    switch (barcodeObject.getBarcodeType()) {
                        case Primary:
                            strBarCodeSource = strEditMain;
                            strBarCode = barcodeObject.getBarcode();
                            strSubBarCodeSource = null;//次码设置为空
                            strSubBarCode = null;//次码设置为空
                            setTitleText(getContext().getString(R.string.primary_code));
                            listener.barCodeType(1);
                            break;
                        case Secondary:
                            if (strBarCode == null) {
                                UToast.showText("请先扫描主码");
                                selectAll();
                            }
                            strSubBarCodeSource = strEditMain;

                            strSubBarCode = barcodeObject.getBarcode();

                            setTitleText(getContext().getString(R.string.second_code));
                            listener.barCodeType(2);
                            break;
                        case Concatenated:
                            strBarCodeSource = strEditMain;

                            strBarCode = barcodeObject.getMainCode();
                            strSubBarCode = barcodeObject.getSubCode();
                            strSubBarCodeSource = null;//次码设置为空
                            strSubBarCode = null;//次码设置为空
                            setTitleText(getContext().getString(R.string.concatenated_code));
                            listener.barCodeType(3);
                            break;
                    }

                }
                return false;
            }
        });

        ivClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setEmpty();
                listener.doClose();
            }
        });

        edtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (TextUtils.isEmpty(edtContent.getText())) {
                    ivClose.setVisibility(View.GONE);
                } else {
                    ivClose.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    public void setText(Object content) {
        edtContent.setText(content + "");
    }

    public void setTitleText(Object title) {
        tvTitleName.setText(title + "");
    }

    public void selectAll() {

        postDelayed(new Runnable() {
            @Override
            public void run() {
                getFocus();
                edtContent.selectAll();
            }
        }, 100);

    }

    public String getText() {
        return edtContent.getText().toString().trim();
    }

    public void setEmpty() {
        setTitleText("主码:");
        selectAll();
        setText("");
        strBarCodeSource = null;
        strSubBarCodeSource = null;
        strBarCode = null;
        strSubBarCode = null;
    }

    /**
     * editContent获取焦点
     */
    public void getFocus() {
        edtContent.setFocusable(true);
        edtContent.setFocusableInTouchMode(true);
        edtContent.requestFocus();
    }


    /**
     * 是否有次码
     */
    public boolean isHasSubCode() {
        if (strBarCode != null && strSubBarCode != null) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 测试上线后关闭
     *
     * @param tv
     */
    private void test(TextView tv) {

        if (IS_RELEASE) return;

        String strTest;
        switch (tv.getText().toString()) {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                strTest = UProperTies.getPropString("tv" + tv.getText().toString());
                break;
            default:
                strTest = tv.getText().toString();
                edtContent.setText(strTest);
        }
        if (strTest == null) {
            edtContent.setText("");
        } else {
            edtContent.setText(strTest);
        }
    }
}
