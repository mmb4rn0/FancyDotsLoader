package com.mmbarno.dotsloader;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by mmbarno on 8/1/18.
 * Email: manzur.mehedi@gagagugu.com
 */
class DotsLoaderManager implements AnimationController.AnimationListener {

    private DotsData mDotsData;

    private AnimationListener mAnimationListener;

    private DrawController mDrawController;
    private AnimationController mAnimationController;

    DotsLoaderManager(Context context, AttributeSet attrs, AnimationListener animationListener) {
        mAnimationListener = animationListener;

        mDotsData = new DotsData(context, attrs);
        mDrawController = new DrawController(mDotsData);
        mAnimationController = new AnimationController(mDotsData, this);
    }

    DotsData dotsData() {
        return mDotsData;
    }

    DrawController drawer() {
        return mDrawController;
    }

    AnimationController animator() {
        return mAnimationController;
    }

    @Override
    public void onAnimationUpdate(AnimatedData animatedData) {
        mDotsData.styleData.alpha = animatedData.alpha;
        mDotsData.styleData.setUpStyleData();
        if(mAnimationListener != null) {
            mAnimationListener.onAnimationUpdate();
        }
    }

    interface AnimationListener {
        void onAnimationUpdate();
    }
}
