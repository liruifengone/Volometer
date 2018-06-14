package com.hearing.volometer.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;


public class DrawableRadioButton extends android.support.v7.widget.AppCompatRadioButton {
    private int drawableWidth;
    private int drawableHeight;

    public DrawableRadioButton(Context context) {
        super(context);
    }

    public DrawableRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setDrawableWidth(int drawableWidth) {
        this.drawableWidth = drawableWidth;
    }

    public int getDrawableWidth() {
        return drawableWidth;
    }

    public void setDrawableHeight(int drawableHeight) {
        this.drawableHeight = drawableHeight;
    }

    public int getDrawableHeight() {
        return drawableHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable[] drawables = getCompoundDrawables();
        Drawable drawable = drawables[0];
        if (drawableWidth <= 0) {
            drawableWidth = drawable.getIntrinsicWidth();
        }
        int gravity = getGravity();
        int left = 0;
        if (gravity == Gravity.CENTER) {
            left = ((int) (getWidth() - drawableWidth - getPaint().measureText(getText().toString()))
                    / 2);
        }
        drawable.setBounds(left, 0, left + drawableWidth, drawableHeight);
        super.onDraw(canvas);
    }
}
