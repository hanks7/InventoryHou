package com.easyway.mismclient.view;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.res.TypedArray;
import android.media.AudioManager;
import android.media.SoundPool;
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

import com.easyway.mismclient.R;
import com.easyway.mismclient.utils.UToast;
import com.easyway.mismclient.view.interf.BarCodeCallBackListener;
import com.google.zxing.common.BeepManager;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.easyway.mismclient.base.BaseConstants.MILLISECONDS;

/**
 * Created by User on 2017/4/19.
 */

public class MyEditView extends LinearLayout {


    private final float mContentSize;
    private final float mTitleSize;

    /**
     * 声明一个振动器对象
     */
    private Vibrator mVibrator;
    /**
     * 音源
     */
    int soundId;
    /**
     * 声音池
     */
    SoundPool sp;
    /**
     * 声音震动管理器。如果扫描成功后可以播放一段音频，也可以震动提醒，可以通过配置来决定扫描成功后的行为。
     */
    private BeepManager beepManager;

    @BindView(R.id.item_input_iv_close)
    ImageView ivClose;
    @BindView(R.id.item_input_tv_title)
    TextView tvTitleName;
    @BindView(R.id.item_input_edt_content)
    EditText edtContent;

    private String mTitle;
    private String mContent;

    public MyEditView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyEditView(Context context) {
        this(context, null);
    }

    public MyEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_input_my_edit_view, this);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.input_test_view);
        ButterKnife.bind(this, view);

        mTitle = array.getString(R.styleable.input_test_view_input_title);
        mContent = array.getString(R.styleable.input_test_view_input_content);
        mTitleSize = array.getDimension(R.styleable.input_test_view_input_title_size, 15);
        mContentSize = array.getDimension(R.styleable.input_test_view_input_title_size, 13);


        tvTitleName.setTextSize(mTitleSize);
        edtContent.setTextSize(mContentSize);

        tvTitleName.setText(mTitle);
        edtContent.setText(mContent);

        //添加提示声音
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId = sp.load(context, R.raw.beep, 1);

        //添加震动
        mVibrator = (Vibrator) getContext().getSystemService(Service.VIBRATOR_SERVICE);
        beepManager = new BeepManager((Activity) getContext());
        beepManager.updatePrefs();


    }

    /**
     * 设置震动提示音
     */
    private void playVoiceAndVibrator() {
        sp.play(soundId, 1, 1, 0, 0, 1);//播放声音
        mVibrator.vibrate(MILLISECONDS);//震动
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
     * 设置监听
     */
    private void initListener() {

        edtContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (listener == null) return false;
                playVoiceAndVibrator();//设置震动提示音
                if (TextUtils.isEmpty(textView.getText().toString())) {
                    UToast.showText("输入不能为空");
                    return false;
                }
                if (i == 5 || i == 0 || i == 6) {
                    listener.barCodeType(i);

                }
                return false;
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

        ivClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                getFocus();
                edtContent.setText("");
                if (listener == null) return;
                listener.doClose();
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
        edtContent.selectAll();
        getFocus();
    }

    public String getText() {
        return edtContent.getText().toString().trim();
    }


    public BarCodeCallBackListener listener;

    public void setListener(BarCodeCallBackListener listener) {
        this.listener = listener;
        initListener();
    }


}
