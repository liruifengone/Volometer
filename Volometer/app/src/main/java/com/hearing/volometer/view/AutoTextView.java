package com.hearing.volometer.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.hearing.volometer.R;

/**
 *
 * Created by Aaron on 2017/3/18.
 */

public class AutoTextView extends android.support.v7.widget.AppCompatTextView{
    private float textSize;
    private float spaceWidth;
    private int textCount;
    private int viewHeight;

    private Paint mPaint;
    private int mAscent;


    public AutoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.black));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int minimumHeight = getSuggestedMinimumHeight();
        int width = MeasureSpec.getSize(widthMeasureSpec);
        viewHeight = measureHeight(heightMeasureSpec);
        textSize = getTextSize();
        textCount = getText().length();
        spaceWidth = (width - (textSize * textCount) - getPaddingLeft() - getPaddingRight()) / (textCount - 1);
        setMeasuredDimension(width, viewHeight);
    }

    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        mAscent = (int) (-mPaint.ascent()+ mPaint.descent());
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = (int) (mAscent + getTextSize()) + getPaddingTop()
                    + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setTextSize(textSize);
        float offset_baseline = (viewHeight-getPaddingBottom()+getPaddingTop()+textSize-mPaint.descent())/2;
        for (int i = 0; i < textCount; i++) {
            drawText(canvas, i, offset_baseline);
        }
    }

    private void drawText(Canvas canvas, int position, float y) {
        // baseline: 基准线(如abcde的底部水平线)
        // ascent: baseline之上至字符最高处的距离
        // descent: baseline之下至字符最低处的距离
        String text = String.valueOf(getText().charAt(position));
        canvas.drawText(text, getPaddingLeft() + position * (textSize + spaceWidth), y, mPaint);
    }
}
