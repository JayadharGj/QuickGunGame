package com.example.diceandload.ui.login;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class TouchEnableDisableLinearLayout extends LinearLayout {
    private boolean mClickable = true;

    public TouchEnableDisableLinearLayout(Context context) {
        super(context);
    }

    public TouchEnableDisableLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchEnableDisableLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TouchEnableDisableLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void setEnable(boolean enable) {
        mClickable = enable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mClickable)
            return super.onInterceptTouchEvent(ev);
        else return true;
    }
}
