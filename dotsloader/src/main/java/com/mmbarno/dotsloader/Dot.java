package com.mmbarno.dotsloader;

import android.graphics.RectF;

/**
 * Created by mmbarno on 8/1/18.
 * Email: manzur.mehedi@gagagugu.com
 */
class Dot {
    int left;
    int top;
    int right;
    int bottom;
    int cornerRadius;

    RectF dotRect;

    private DotsData mDotsData;
    Dot(DotsData dotsData) {
        mDotsData = dotsData;
        dotRect = new RectF();
    }

    void set(int left, int top, int right, int bottom) {
        this.left = left + mDotsData.dotsStrokeWidth;
        this.top = top + mDotsData.dotsStrokeWidth;
        this.right = right - mDotsData.dotsStrokeWidth;
        this.bottom = bottom - mDotsData.dotsStrokeWidth;
        this.cornerRadius = mDotsData.dotsCornerRadius < 0 ? mDotsData.dotsSize / 2 : mDotsData.dotsCornerRadius;
        setRect();
    }

    private void setRect() {
        dotRect.set(left, top, right, bottom);
    }
}
