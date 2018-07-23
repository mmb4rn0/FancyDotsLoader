package com.mmbarno.dotsloader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mmbarno on 7/23/18.
 * Email: manzur.mehedi@gagagugu.com
 */
@SuppressWarnings("SuspiciousNameCombination")
public class DotsLoader extends LinearLayout {
    private static final int DEFAULT_DOT_COUNT = 5;

    private List<View> mIndicatorDots;

    // Attributes
    private int mDotsSize;
    private int mDotsSpacing;
    private int mDotsStrokeWidth;
    private int mDotsCornerRadius;
    private int mDotsColor;
    private int mDotsCount = DEFAULT_DOT_COUNT;

    private boolean isAttributeChanged;

    private int animationIterationCount;
    private int visibility;

    private Animation animation;
    private View mIndicatorDot;
    private int mIndicatorDotCornerRadius;

    public DotsLoader(Context context) {
        this(context, null);
    }

    public DotsLoader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DotsLoader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mIndicatorDots = new ArrayList<>();

        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);

        mDotsSize = dpToPx(16); // 16dp
        mDotsSpacing = dpToPx(4); // 4dp
        mDotsStrokeWidth = dpToPx(2); // 2dp
        mDotsCornerRadius = mDotsSize / 2; // 1dp additional to fill the stroke dots
        mDotsColor = Color.parseColor("#000000");

        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.DotsLoader);

            // Dots attributes
            mDotsColor = a.getColor(R.styleable.DotsLoader_dotsColor, mDotsColor);
            mDotsSize = (int) a.getDimension(R.styleable.DotsLoader_dotsSize, mDotsSize);
            mDotsSpacing = (int) a.getDimension(R.styleable.DotsLoader_dotsSpacing, mDotsSpacing);
            mDotsCornerRadius = (int) a.getDimension(R.styleable.DotsLoader_dotsCornerRadius, mDotsSize / 2);

            // Spring dots attributes
            mDotsStrokeWidth = (int) a.getDimension(R.styleable.DotsLoader_dotsStrokeWidth, mDotsStrokeWidth);
            mDotsCount = a.getInt(R.styleable.DotsLoader_dotsCount, DEFAULT_DOT_COUNT);

            a.recycle();
        }
        int fillDotsSize = mDotsSize - mDotsStrokeWidth * 2;
        mIndicatorDotCornerRadius = (mDotsCornerRadius * fillDotsSize) / mDotsSize;

        addDots();
    }

    private void addDots() {
        for (int i = 0; i < mDotsCount; i++) {
            ViewGroup dot = buildDot(i);
            addView(dot);
        }
    }

    private FrameLayout buildDot(int count) {
        FrameLayout dot = new FrameLayout(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mDotsSize, mDotsSize);
        layoutParams.setMargins(0, 0, count < mDotsCount - 1 ? mDotsSpacing : 0, 0);
        dot.setPadding(mDotsStrokeWidth, mDotsStrokeWidth, mDotsStrokeWidth, mDotsStrokeWidth);
        dot.setLayoutParams(layoutParams);

        GradientDrawable dotBackground = new GradientDrawable();
        dotBackground.setCornerRadius(mDotsCornerRadius);
        dotBackground.setStroke(mDotsStrokeWidth, mDotsColor);
        dot.setBackground(dotBackground);

        setUpFillDot(count, dot);
        return dot;
    }

    private void setUpFillDot(int count, ViewGroup dot) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View fillDotView = new View(getContext());
        GradientDrawable dotBackground = new GradientDrawable();
        dotBackground.setCornerRadius(mIndicatorDotCornerRadius);
        dotBackground.setColor(mDotsColor);
        fillDotView.setBackground(dotBackground);
        fillDotView.setVisibility(count == animationIterationCount ? VISIBLE : GONE);
        fillDotView.setLayoutParams(params);
        dot.addView(fillDotView);
        mIndicatorDots.add(fillDotView);
    }

    private void removeDots() {
        removeAllViews();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        refreshDots(true);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopDotsAnimators(true);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (this.visibility == visibility) {
            return;
        }
        this.visibility = visibility;
        visibilityChanged();
    }

    private void visibilityChanged() {
        if (getVisibility() == VISIBLE) {
            refreshDots(true);
        } else {
            stopDotsAnimators(true);
        }
    }

    private void refreshDots(final boolean reset) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                stopDotsAnimators(reset);
                if (getChildCount() == 0 || mIndicatorDots.isEmpty() || isAttributeChanged) {
                    mIndicatorDots.clear();
                    removeDots();
                    addDots();
                }
                isAttributeChanged = false;
                initAnimation();
                initFillDotState();
                startDotsAnimators();
            }
        });
    }

    private void initAnimation() {
        if (animation == null) {
            animation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_out);
            animation.setAnimationListener(animationListener);
        }
    }

    private void initFillDotState() {
        for (int i = 0; i < mIndicatorDots.size(); i++) {
            mIndicatorDots.get(i).setVisibility(i == animationIterationCount ? VISIBLE : GONE);
        }
    }

    private void startDotsAnimators() {
        if (getVisibility() != VISIBLE) {
            return;
        }
        mIndicatorDot = mIndicatorDots.get(animationIterationCount);
        mIndicatorDot.startAnimation(animation);
        animationIterationCount++;
        if (animationIterationCount >= mDotsCount) {
            animationIterationCount = 0;
        }
    }

    private void stopDotsAnimators(boolean reset) {
        animationIterationCount = reset ? 0 : animationIterationCount;
        if (mIndicatorDot != null) {
            mIndicatorDot.animate().cancel();
            mIndicatorDot.clearAnimation();
            mIndicatorDot = null;
        }
        if (animation != null) {
            animation.setAnimationListener(null);
            animation = null;
        }
    }

    private Animation.AnimationListener animationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {
            mIndicatorDot.setVisibility(VISIBLE);
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            mIndicatorDot.setVisibility(GONE);
            animation.setDuration(400);
            mIndicatorDot.clearAnimation();
            startDotsAnimators();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
            animation.setDuration(250);
        }
    };

    private int dpToPx(int dp) {
        return (int) (getContext().getResources().getDisplayMetrics().density * dp);
    }

    public int getDotsSize() {
        return mDotsSize;
    }

    public void setDotsSize(int dotsSize) {
        if (this.mDotsSize == dotsSize) {
            return;
        }
        this.mDotsSize = dotsSize;
        isAttributeChanged = true;
        refreshDots(false);
    }

    public int getDotsSpacing() {
        return mDotsSpacing;
    }

    public void setDotsSpacing(int dotsSpacing) {
        if (this.mDotsSpacing == dotsSpacing) {
            return;
        }
        this.mDotsSpacing = dotsSpacing;
        isAttributeChanged = true;
        refreshDots(false);
    }

    public int getDotsStrokeWidth() {
        return mDotsStrokeWidth;
    }

    public void setDotsStrokeWidth(int dotsStrokeWidth) {
        if (this.mDotsStrokeWidth == dotsStrokeWidth) {
            return;
        }
        this.mDotsStrokeWidth = dotsStrokeWidth;
        isAttributeChanged = true;
        refreshDots(false);
    }

    public int getDotsCornerRadius() {
        return mDotsCornerRadius;
    }

    public void setDotsCornerRadius(int dotsCornerRadius) {
        if (this.mDotsSpacing == dotsCornerRadius) {
            return;
        }
        this.mDotsCornerRadius = dotsCornerRadius;
        isAttributeChanged = true;
        refreshDots(false);
    }

    public int getDotsColor() {
        return mDotsColor;
    }

    public void setDotsColor(int dotsColor) {
        if (this.mDotsColor == dotsColor) {
            return;
        }
        this.mDotsColor = dotsColor;
        isAttributeChanged = true;
        refreshDots(false);
    }

    public int getDotsCount() {
        return mDotsCount;
    }

    public void setDotsCount(int dotsCount) {
        if (this.mDotsCount == dotsCount) {
            return;
        }
        this.mDotsCount = dotsCount;
        isAttributeChanged = true;
        refreshDots(true);
    }
}
