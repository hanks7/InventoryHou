package com.easyway.mismclient.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.easyway.mismclient.R;

/**
 * Created by hjj on 2018/4/27.
 */
public class MyToast {
    private Toast mToast;

    private MyToast(Context context, CharSequence text, int duration, final Runnable runnable) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_toast, null);

        TextView tvNote = (TextView) v.findViewById(R.id.layout_toast_tv_note);

        tvNote.setText(text);

        mToast = new Toast(context);
        setGravity(Gravity.CENTER, 0, -172);
        mToast.setDuration(duration);
        mToast.setView(v);

        v.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewDetachedFromWindow(View v) {
                runnable.run();
            }
            @Override
            public void onViewAttachedToWindow(View v) {
            }

        });
    }

    public static MyToast makeText(Context context, CharSequence text, int duration, Runnable runnable) {
        return new MyToast(context, text, duration, runnable);
    }

    public void show() {
        if (mToast != null) {
            mToast.show();
        }
    }

    public void setGravity(int gravity, int xOffset, int yOffset) {
        if (mToast != null) {
            mToast.setGravity(gravity, xOffset, yOffset);
        }
    }
}
