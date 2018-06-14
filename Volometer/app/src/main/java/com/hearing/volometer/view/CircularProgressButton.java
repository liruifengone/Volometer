package com.hearing.volometer.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.KeyEvent;

public class CircularProgressButton extends android.support.v7.widget.AppCompatTextView{
    public static final int MAX_PROGRESS = 100;
    public static final int MIN_PROGRESS = 0;

    int measuredWidth, measuredHeight, paddingLeft, paddingRight, paddingTop, paddingBottom;

    float centerX, centerY;

    private boolean isStart = false;
    private boolean inCircle = false;
    private boolean inProgress = false;
    private boolean isPressed = false;

    private String button_text = null;
    private int button_text_color = Color.BLACK;
    private int button_text_size = 18;
    private Rect textRect;
    private int button_radius;
    private float button_width = 50f;
    private float button_height = 30f;
    private float button_stroke_width = 5f;
    private int button_color = Color.WHITE;
    private int button_color_pressed = Color.WHITE;
    private int button_stroke_color = Color.GRAY;

    private boolean width_ready = false;
    private boolean radius_ready = false;
    private float medium_radius;
    private float delta_radius;
    private float medium_width;

    private int mProgress = MIN_PROGRESS;
    private int mProgressColor;
    private int mProgressInnerBackground;
    private int mProgressOutterBackground;
    private float mProgressCornerRadius = 12f;
    private Paint mPaint;
    private Paint mTextPaint;
    private RectF mRectF;

    // 变形的步长
    private float width_step;
    private float radius_step;

    public CircularProgressButton(Context context) {
        super(context);
        init();
    }

    public CircularProgressButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircularProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(1);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textRect = new Rect();
        mRectF = new RectF();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        isPressed = true;
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        isPressed = false;
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();
        paddingTop = getPaddingTop();
        paddingBottom = getPaddingBottom();
        measuredWidth = getMeasuredWidth();
        measuredHeight = getMeasuredHeight();
        setWidth(measuredWidth);
        setHeight(measuredHeight);
        button_width = measuredWidth;
        button_height = measuredHeight;
        mProgressCornerRadius = button_height / 2;
        centerX = (measuredWidth - paddingLeft + paddingRight) / 2;
        centerY = (measuredHeight - paddingBottom + paddingTop)/2;
        if (!isStart && !inProgress) {
            medium_width = button_width;
            medium_radius = button_radius;
        }
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        if (isStart) {
            if (!inProgress) {
                // 由圆角矩形向圆过渡
                if (!inCircle) {
                    if (changeWidth(button_height)) {
                        if (changeRadius(mProgressCornerRadius)) {
                            radius_ready = false;
                            inCircle = true;
                            delta_radius = medium_radius - mProgressCornerRadius * 0.8f;
                            radius_step = delta_radius / 5;
                        }
                    }
                    mPaint.setStyle(Paint.Style.FILL);
                    mRectF.left = (button_width - medium_width) / 2;
                    mRectF.top = button_height;
                    mRectF.right = (button_width + medium_width) / 2;
                    mRectF.bottom = button_height;
                    mPaint.setColor(button_stroke_color);
                    canvas.drawRoundRect(mRectF, medium_radius, medium_radius, mPaint);
                    if (isPressed) {
                        mPaint.setColor(button_color_pressed);
                    } else {
                        mPaint.setColor(button_color);
                    }
                    mRectF.left = (button_width - medium_width) / 2 + button_stroke_width;
                    mRectF.top = button_stroke_width;
                    mRectF.right = (button_width + medium_width) / 2 - button_stroke_width;
                    mRectF.bottom = button_height - button_stroke_width;
                    canvas.drawRoundRect(mRectF, medium_radius, medium_radius, mPaint);
                } else {
                    // 由圆向圆形进度条过渡
                    if (changeRadius(mProgressCornerRadius * 0.8f)) {
                        inProgress = true;
                    }
                    // 最外圈背景
                    mPaint.setStyle(Paint.Style.FILL);
                    mPaint.setColor(mProgressOutterBackground);
                    canvas.drawCircle(centerX, centerY, mProgressCornerRadius, mPaint);
                    // 扇形进度条
                    mPaint.setColor(mProgressColor);
                    if (mProgress > MIN_PROGRESS && mProgress <= MAX_PROGRESS) {
                        mRectF.left = button_width / 2 - mProgressCornerRadius;
                        mRectF.top = 0;
                        mRectF.right = button_width / 2 + mProgressCornerRadius;
                        mRectF.bottom = button_height;
                        canvas.drawArc(mRectF, -90, ((float) mProgress / MAX_PROGRESS) * 360, true, mPaint);
                    }
                    // 内圈背景
                    mPaint.setColor(mProgressInnerBackground);
                    canvas.drawCircle(centerX, centerY, medium_radius, mPaint);

                    if (mProgress >= MAX_PROGRESS) {
                        end();
                    }
                }
            } else {
                // 完成显示圆形进度条
                // 最外圈背景
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setColor(mProgressOutterBackground);
                canvas.drawCircle(centerX, centerY, mProgressCornerRadius, mPaint);
                // 扇形进度条
                mPaint.setColor(mProgressColor);
                if (mProgress > MIN_PROGRESS && mProgress <= MAX_PROGRESS) {
                    mRectF.left = button_width / 2 - mProgressCornerRadius;
                    mRectF.top = 0;
                    mRectF.right = button_width / 2 + mProgressCornerRadius;
                    mRectF.bottom = button_height;
                    canvas.drawArc(mRectF, -90, ((float) mProgress / MAX_PROGRESS) * 360, true, mPaint);
                }
                // 内圈背景
                mPaint.setColor(mProgressInnerBackground);
                canvas.drawCircle(centerX, centerY, mProgressCornerRadius * 0.8f, mPaint);

                if (mProgress >= MAX_PROGRESS) {
                    end();
                }
            }
            invalidate();
        } else {
            if (inCircle) {
                if (inProgress) {
                    // 由圆形进度条向圆过渡
                    if (changeRadius(mProgressCornerRadius)) {
                        inProgress = false;
                        width_ready = false;
                        radius_ready = false;
                        delta_radius = medium_radius - button_radius;
                        radius_step = delta_radius / 5;
                    }
                    // 最外圈背景
                    mPaint.setStyle(Paint.Style.FILL);
                    mPaint.setColor(mProgressColor);
                    canvas.drawCircle(centerX, centerY, mProgressCornerRadius, mPaint);
                    // 内圈背景
                    mPaint.setColor(mProgressInnerBackground);
                    canvas.drawCircle(centerX, centerY, medium_radius, mPaint);
                } else {
                    // 由圆向圆角矩形过渡
                    if (changeWidth(button_width)) {
                        if (changeRadius(button_radius)) {
                            inCircle = false;
                        }
                    }
                    mPaint.setStyle(Paint.Style.FILL);
                    mRectF.left = (button_width - medium_width) / 2;
                    mRectF.top = button_height;
                    mRectF.right = (button_width + medium_width) / 2;
                    mRectF.bottom = button_height;
                    mPaint.setColor(button_stroke_color);
                    canvas.drawRoundRect(mRectF, medium_radius, medium_radius, mPaint);
                    if (isPressed) {
                        mPaint.setColor(button_color_pressed);
                    } else {
                        mPaint.setColor(button_color);
                    }
                    mRectF.left = (button_width - medium_width) / 2 + button_stroke_width;
                    mRectF.top = button_stroke_width;
                    mRectF.right = (button_width + medium_width) / 2 - button_stroke_width;
                    mRectF.bottom = button_height - button_stroke_width;
                    canvas.drawRoundRect(mRectF, medium_radius, medium_radius, mPaint);
                }
                invalidate();
            } else {
                // 圆角矩形按钮
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setColor(button_stroke_color);
                mRectF.left = (button_width - medium_width) / 2;
                mRectF.top = button_height;
                mRectF.right = (button_width + medium_width) / 2;
                mRectF.bottom = button_height;
                canvas.drawRoundRect(mRectF, medium_radius, medium_radius, mPaint);
                if (isPressed) {
                    mPaint.setColor(button_color_pressed);
                } else {
                    mPaint.setColor(button_color);
                }
                mRectF.left = (button_width - medium_width) / 2 + button_stroke_width;
                mRectF.top = button_stroke_width;
                mRectF.right = (button_width + medium_width) / 2 - button_stroke_width;
                mRectF.bottom = button_height - button_stroke_width;
                canvas.drawRoundRect(mRectF, medium_radius, medium_radius, mPaint);

                // 绘制按钮文字
                mTextPaint.setColor(button_text_color);
                mTextPaint.setTextSize(button_text_size);
                mTextPaint.getTextBounds(button_text, 0, button_text.length(), textRect);
                float x = (measuredWidth - mTextPaint.measureText(button_text) - paddingLeft + paddingRight) / 2;
                float y = (measuredHeight - paddingBottom + paddingTop + button_text_size - mPaint.descent()) / 2;
                canvas.drawText(button_text, x, y, mTextPaint);
            }
        }
    }

    private boolean changeWidth(float destWidth) {
        if (width_ready) {
            return true;
        }
        if (width_step < 0) {
            // 宽度逐渐增大至与destWidth相同
            if ((medium_width >= destWidth) ||
                    ((medium_width < destWidth) && (medium_width - width_step >= destWidth))) {
                medium_width = destWidth;
                width_ready = true;
            } else {
                medium_width -= width_step;
            }
        } else if (width_step > 0) {
            // 宽度逐渐减小至与destWidth相同
            if ((medium_width <= destWidth) ||
                    ((medium_width > destWidth) && (medium_width - width_step <= destWidth))) {
                medium_width = destWidth;
                width_ready = true;
            } else {
                medium_width -= width_step;
            }
        }
        return width_ready;
    }

    private boolean changeRadius(float destRadius) {
        if (radius_ready) {
            return true;
        }
        if (delta_radius < 0) {
            // 圆角半径逐渐增大至按钮
            if ((medium_radius >= destRadius) ||
                    ((medium_radius < destRadius) && (medium_radius - radius_step >= destRadius))) {
                medium_radius = destRadius;
                radius_ready = true;
            } else {
                medium_radius -= radius_step;
            }
        } else if (delta_radius > 0) {
            if ((medium_radius <= destRadius) ||
                    ((medium_radius > destRadius) && (medium_radius - radius_step <= destRadius))) {
                medium_radius = destRadius;
                radius_ready = true;
            } else {
                medium_radius -= radius_step;
            }
        }
        return radius_ready;
    }

    public void setProgress(int progress) {
        mProgress = progress;
        invalidate();
    }

    public int getProgress() {
        return mProgress;
    }

    public void setAttr(int progressColor, int progressInnerBackground, int progressOutterBackground, int buttonRadius) {
        mProgressColor = getResources().getColor(progressColor);
        mProgressInnerBackground = getResources().getColor(progressInnerBackground);
        mProgressOutterBackground = getResources().getColor(progressOutterBackground);
        button_radius = buttonRadius;
    }

    public void setButtonText(String text) {
        button_text = text;
    }

    public String getButtonText() {
        return button_text;
    }

    public void setButtonTextColor(int color) {
        button_text_color = getResources().getColor(color);
    }

    public void setButtonTextSize(int size) {
        button_text_size = size;
    }

    public void setButton_color(int color) {
        button_color = getResources().getColor(color);
    }

    public void setButton_color_pressed(int color) {
        button_color_pressed = getResources().getColor(color);
    }

    public void setButton_stroke_color(int color) {
        button_stroke_color = getResources().getColor(color);
    }

    public void setButton_stroke_width(float width) {
        button_stroke_width = width;
    }

    public void start() {
        isStart = true;
        inCircle = false;
        inProgress = false;
        width_ready = false;
        radius_ready = false;
        medium_width = button_width;
        width_step = (button_width - button_height) / 10;
        medium_radius = button_radius;
        delta_radius = button_radius - mProgressCornerRadius;
        radius_step = delta_radius / 5;
    }

    public void end() {
        isStart = false;
        inCircle = true;
        inProgress = true;
        width_ready = false;
        radius_ready = false;
        medium_width = button_height;
        width_step = (button_height - button_width) / 10;
        medium_radius = mProgressCornerRadius * 0.8f;
        delta_radius = medium_radius - mProgressCornerRadius;
        radius_step = delta_radius / 5;
    }

    public void reset() {
        if (isStart) {
            isStart = true;
            inProgress = true;
            mProgress = MAX_PROGRESS;
        }
        invalidate();
    }
}