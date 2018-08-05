package com.mmbarno.dotsloader;

import android.graphics.Paint;

/**
 * Created by mmbarno on 8/1/18.
 * Email: manzur.mehedi@gagagugu.com
 */
class StyleData {

    private static final int INITIAL_ALPHA = -1;
    Paint strokePaint;
    Paint fillPaint;

    int alpha = INITIAL_ALPHA;
    private DotsData mDotsData;

    StyleData(DotsData dotsData) {
        mDotsData = dotsData;

        strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(dotsData.dotsColor);
        strokePaint.setStrokeWidth(dotsData.dotsStrokeWidth);
        strokePaint.setAlpha(255);

        fillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setColor(dotsData.dotsColor);
        fillPaint.setAlpha(alpha);
    }

    void setUpStyleData() {
        strokePaint.setColor(mDotsData.dotsColor);
        strokePaint.setStrokeWidth(mDotsData.dotsStrokeWidth);

        fillPaint.setColor(mDotsData.dotsColor);
        fillPaint.setAlpha(alpha);
    }

}
