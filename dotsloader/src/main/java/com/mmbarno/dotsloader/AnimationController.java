package com.mmbarno.dotsloader;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by mmbarno on 8/1/18.
 * Email: manzur.mehedi@gagagugu.com
 */
class AnimationController {

    private DotsData mDotsData;
    private AnimationListener mAnimationListener;

    private ValueAnimator mAnimator;
    private AnimatedData mAnimatedData;

    AnimationController(DotsData dotsData, AnimationListener animationListener) {
        mDotsData = dotsData;
        mAnimationListener = animationListener;

        mAnimatedData = new AnimatedData();
        mAnimator = ValueAnimator.ofInt(0, 255);
        mAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimator.setDuration(mDotsData.transitionDuration / 2);
    }

    void resetDuration() {
        mAnimator.setDuration(mDotsData.transitionDuration / 2);
    }

    boolean isAnimationRunning() {
        return mAnimator.isStarted() || mAnimator.isRunning();
    }

    void animate() {
        if (isAnimationRunning()) {
            return;
        }
        if (mAnimator.getListeners() == null || !mAnimator.getListeners().contains(animatorListener)) {
            mAnimator.addListener(animatorListener);
        }
        mAnimator.start();
    }

    void cancel() {
        if (!isAnimationRunning()) {
            return;
        }
        mAnimator.addUpdateListener(animatorUpdateListener);
        mAnimator.removeListener(animatorListener);
        mDotsData.animationData.resetAnimationData();
        mAnimator.cancel();
    }

    private Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            mAnimator.addUpdateListener(animatorUpdateListener);
        }

        @Override
        public void onAnimationEnd(Animator animation) {

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            mDotsData.animationData.incrementRepeatCount();
            if (mDotsData.animationData.repeatCount == 0) {
                mDotsData.animationData.incrementPosition();
            }
        }
    };

    private ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            mAnimatedData.alpha = (int) animation.getAnimatedValue();
            if (mAnimationListener != null) {
                mAnimationListener.onAnimationUpdate(mAnimatedData);
            }
        }
    };

    interface AnimationListener {
        void onAnimationUpdate(AnimatedData animatedData);
    }
}
