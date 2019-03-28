package com.gambler.infiniteexpendablelistview.libs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class FlipImageView extends android.support.v7.widget.AppCompatImageView implements View.OnClickListener {
    OnCheckChangeListener onCheckChangeListener;
    private boolean status;

    public FlipImageView(Context context) {
        super(context);
        init();

    }

    public FlipImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlipImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {

        this.status = status;
        if (isStatus()) {
            setRotation(180);
        } else {
            setRotation(360);
        }
    }

    private void init() {


    }

    public void setOnCheckChangeListener(OnCheckChangeListener onCheckChangeListener) {
        this.onCheckChangeListener = onCheckChangeListener;
        setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        status = !status;
        if (isStatus()) {
            setRotation(180);
        } else {
            setRotation(360);
        }
        if (onCheckChangeListener != null)
            onCheckChangeListener.onChecked(status);
    }

    public interface OnCheckChangeListener {
        public void onChecked(boolean check);
    }
}
