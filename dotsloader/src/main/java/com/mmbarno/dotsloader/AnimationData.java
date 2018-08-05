package com.mmbarno.dotsloader;

import android.util.Log;

/**
 * Created by mmbarno on 8/2/18.
 * Email: manzur.mehedi@gagagugu.com
 */
class AnimationData {
    int indicatorPosition;
    int repeatCount;

    private DotsData mDotsData;

    AnimationData(DotsData dotsData) {
        mDotsData = dotsData;
    }

    public void resetAnimationData() {
        Log.i("AnimationController", "resetAnimationData: ");
        indicatorPosition = 0;
        repeatCount = 0;
    }

    public void incrementPosition() {
        indicatorPosition++;
        if (indicatorPosition >= mDotsData.dotsCount) {
            indicatorPosition = 0;
        }
    }

    public void incrementRepeatCount() {
        repeatCount++;
        if (repeatCount > 1) {
            repeatCount = 0;
        }
    }
}
