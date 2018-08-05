package com.mmbarno.dotsloader;

/**
 * Created by mmbarno on 8/1/18.
 * Email: manzur.mehedi@gagagugu.com
 */
class Container {
    int width;
    int height;
    int left;
    int right;
    int top;
    int bottom;

    private DotsData mDotsData;

    Container(DotsData dotsData) {
        mDotsData = dotsData;
    }

    void measureContainerWidthAndHeight() {
        width = (mDotsData.dotsCount * mDotsData.dotsSize) + (mDotsData.dotsSpacing * (mDotsData.dotsCount - 1));
        height = mDotsData.dotsSize;
    }

    void setUpDotsContainerProperties(int measuredWidth, int measuredHeight) {
        final int halfMeasuredWidth = measuredWidth / 2;
        final int halfMeasuredHeight = measuredHeight / 2;
        final int halfDotsWidth = width / 2;
        final int halfDotsHeight = height / 2;

        left = halfMeasuredWidth - halfDotsWidth;
        top = halfMeasuredHeight - halfDotsHeight;
        right = halfMeasuredWidth + halfDotsWidth;
        bottom = halfMeasuredHeight + halfDotsHeight;
    }
}
