package com.mmbarno.dotsloader;

import android.graphics.Canvas;

/**
 * Created by mmbarno on 8/1/18.
 * Email: manzur.mehedi@gagagugu.com
 */
class DrawController {

    private DotsData mDotsData;

    DrawController(DotsData dotsData) {
        mDotsData = dotsData;
    }

    void draw(Canvas canvas) {
        canvas.save();
        canvas.clipRect(mDotsData.container.left, mDotsData.container.top, mDotsData.container.right, mDotsData.container.bottom);
        drawStrokeDots(canvas);
        drawIndicatorDot(canvas);
        canvas.restore();
    }

    private void drawStrokeDots(Canvas canvas) {
        for (Dot dot : mDotsData.dotsList) {
            canvas.drawRoundRect(dot.dotRect, dot.cornerRadius, dot.cornerRadius, mDotsData.styleData.strokePaint);
        }
    }

    private void drawIndicatorDot(Canvas canvas) {
        Dot indicator = mDotsData.dotsList.get(mDotsData.animationData.indicatorPosition);
        canvas.drawRoundRect(indicator.dotRect, indicator.cornerRadius, indicator.cornerRadius, mDotsData.styleData.fillPaint);
    }
}
