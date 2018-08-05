package com.mmbarno.dotsloader;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by mmbarno on 7/23/18.
 * Email: manzur.mehedi@gagagugu.com
 */
@SuppressWarnings("ALL")
public class DotsLoader extends View implements DotsLoaderManager.AnimationListener {

    private DotsLoaderManager loaderManager;

    public DotsLoader(Context context) {
        this(context, null);
    }

    public DotsLoader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DotsLoader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        loaderManager = new DotsLoaderManager(context, attrs, this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (loaderManager != null) {
            loaderManager.animator().cancel();
        }
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
        if (loaderManager != null) {
            loaderManager.animator().cancel();
        }
    }

    private int reconcileSize(int measureSpec, int desiredSize) {
        final int mode = MeasureSpec.getMode(measureSpec);
        final int measuredSize = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.EXACTLY:
                return measuredSize;
            case MeasureSpec.AT_MOST:
                return Math.min(measuredSize, desiredSize);
            case MeasureSpec.UNSPECIFIED:
            default:
                return desiredSize;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        loaderManager.dotsData().container.measureContainerWidthAndHeight();
        final int desiredWidth = loaderManager.dotsData().container.width + getPaddingLeft() + getPaddingRight();
        final int desiredHeight = loaderManager.dotsData().container.height + getPaddingTop() + getPaddingBottom();

        final int measuredWidth = reconcileSize(widthMeasureSpec, desiredWidth);
        final int measuredHeight = reconcileSize(heightMeasureSpec, desiredHeight);

        loaderManager.dotsData().container.setUpDotsContainerProperties(measuredWidth, measuredHeight);
        loaderManager.dotsData().setUpDots();
        loaderManager.dotsData().styleData.setUpStyleData();

        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        loaderManager.drawer().draw(canvas);
        if (!loaderManager.animator().isAnimationRunning()) {
            post(new Runnable() {
                @Override
                public void run() {
                    loaderManager.animator().animate();
                    removeCallbacks(this);
                }
            });
        }
    }

    public int getDotsSize() {
        return loaderManager.dotsData().dotsSize;
    }

    public void setDotsSize(int dotsSize) {
        if (loaderManager.dotsData().dotsSize == dotsSize) {
            return;
        }
        loaderManager.dotsData().dotsSize = dotsSize;
        requestLayout();
    }

    public int getDotsSpacing() {
        return loaderManager.dotsData().dotsSpacing;
    }

    public void setDotsSpacing(int dotsSpacing) {
        if (loaderManager.dotsData().dotsSpacing == dotsSpacing) {
            return;
        }
        loaderManager.dotsData().dotsSpacing = dotsSpacing;
        requestLayout();
    }

    public int getDotsStrokeWidth() {
        return loaderManager.dotsData().dotsStrokeWidth;
    }

    public void setDotsStrokeWidth(int dotsStrokeWidth) {
        if (loaderManager.dotsData().dotsStrokeWidth == dotsStrokeWidth) {
            return;
        }
        loaderManager.dotsData().dotsStrokeWidth = dotsStrokeWidth;
        loaderManager.dotsData().changeDots();
        loaderManager.dotsData().styleData.setUpStyleData();
    }

    public int getDotsCornerRadius() {
        return loaderManager.dotsData().dotsCornerRadius;
    }

    public void setDotsCornerRadius(int dotsCornerRadius) {
        if (loaderManager.dotsData().dotsCornerRadius == dotsCornerRadius) {
            return;
        }
        loaderManager.dotsData().dotsCornerRadius = dotsCornerRadius;
        loaderManager.dotsData().changeDots();
    }

    public int getDotsColor() {
        return loaderManager.dotsData().dotsColor;
    }

    public void setDotsColor(@ColorInt int dotsColor) {
        if (loaderManager.dotsData().dotsColor == dotsColor) {
            return;
        }
        loaderManager.dotsData().dotsColor = dotsColor;
        loaderManager.dotsData().styleData.setUpStyleData();
    }

    public int getDotsCount() {
        return loaderManager.dotsData().dotsCount;
    }

    public void setDotsCount(int dotsCount) {
        if (loaderManager.dotsData().dotsCount == dotsCount) {
            return;
        }
        loaderManager.dotsData().dotsCount = dotsCount;
        requestLayout();
    }

    public int getTransitionDuration() {
        return loaderManager.dotsData().transitionDuration;
    }

    public void setTransitionDuration(int transitionDuration) {
        if (loaderManager.dotsData().transitionDuration == transitionDuration) {
            return;
        }
        loaderManager.dotsData().transitionDuration = transitionDuration;
        loaderManager.animator().resetDuration();
    }

    @Override
    public void onAnimationUpdate() {
        invalidate();
    }
}
