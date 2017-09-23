package com.tmd.dictionary.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.tmd.dictionary.R;

/**
 * Created by tmd on 02/09/2017.
 */
public class PaperTextView extends android.support.v7.widget.AppCompatTextView {
    private int mLinesColor;
    private float mStrokeWidth;
    private float mLinesPaddingLeft;
    private float mLinesPaddingRight;
    private Paint mLinesPaint;

    public PaperTextView(Context context) {
        super(context);
    }

    public PaperTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray =
            context.getTheme().obtainStyledAttributes(attrs, R.styleable.PaperTextView, 0, 0);
        mLinesColor = typedArray.getColor(R.styleable.PaperTextView_linesColor, Color.TRANSPARENT);
        mStrokeWidth = typedArray.getDimension(R.styleable.PaperTextView_strokeWidth, 1);
        mLinesPaddingLeft = typedArray.getDimension(R.styleable.PaperTextView_linesPaddingLeft, 20);
        mLinesPaddingRight =
            typedArray.getDimension(R.styleable.PaperTextView_linesPaddingRight, 20);
        typedArray.recycle();
        mLinesPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinesPaint.setColor(mLinesColor);
        mLinesPaint.setStrokeWidth(mStrokeWidth);
    }

    public PaperTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawUnderlines(canvas);
    }

    private void drawUnderlines(Canvas canvas) {
        int lineCount = getLineCount();
        int lineHeight = getLineHeight();
        float startX = mLinesPaddingLeft,
            startY = getTextSize() + getPaddingTop(),
            endX = getWidth() - mLinesPaddingRight,
            endY = startY;
        for (int i = 1; i <= lineCount; i++) {
            canvas.drawLine(startX, startY, endX, endY, mLinesPaint);
            startY += lineHeight;
            endY = startY;
        }
    }

    @Override
    public int getLineCount() {
        return super.getLineCount();
    }

    @Override
    public int getLineHeight() {
        return super.getLineHeight();
    }
}