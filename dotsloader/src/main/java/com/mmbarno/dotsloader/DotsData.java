package com.mmbarno.dotsloader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;

import java.util.ArrayList;

/**
 * Created by mmbarno on 8/1/18.
 * Email: manzur.mehedi@gagagugu.com
 */
class DotsData {

    // Attributes
    int dotsSize;
    int dotsSpacing;
    int dotsStrokeWidth;
    int dotsCornerRadius;
    int dotsColor;
    int dotsCount;
    int transitionDuration;

    Container container;
    ArrayList<Dot> dotsList;
    StyleData styleData;
    AnimationData animationData;

    DotsData(Context context, AttributeSet attrs) {
        dotsSize = dpToPx(context, 16); // 16dp
        dotsSpacing = dpToPx(context, 4); // 4dp
        dotsStrokeWidth = dpToPx(context, 2); // 2dp
        dotsCornerRadius = -1;
        dotsColor = Color.parseColor("#000000");
        dotsCount = 5;
        transitionDuration = 650; // in millis

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DotsLoader);

            dotsSize = (int) a.getDimension(R.styleable.DotsLoader_dots_size, dotsSize);
            dotsSpacing = (int) a.getDimension(R.styleable.DotsLoader_dots_spacing, dotsSpacing);
            dotsStrokeWidth = (int) a.getDimension(R.styleable.DotsLoader_dots_stroke_width, dotsStrokeWidth);
            dotsCornerRadius = (int) a.getDimension(R.styleable.DotsLoader_dots_corner_radius, -1);
            dotsColor = a.getColor(R.styleable.DotsLoader_dots_color, dotsColor);
            dotsCount = a.getInt(R.styleable.DotsLoader_dots_count, dotsCount);
            transitionDuration = a.getInt(R.styleable.DotsLoader_dots_transition_duration, transitionDuration);

            a.recycle();
        }

        container = new Container(this);
        dotsList = new ArrayList<>();
        styleData = new StyleData(this);
        animationData = new AnimationData(this);
    }

    void setUpDots() {
        dotsList.clear();
        for (int i = 0; i < dotsCount; i++) {
            Dot dot = new Dot(this);
            setDotAttributes(i, dot);
            dotsList.add(dot);
        }
    }

    void changeDots() {
        for (int i = 0; i < dotsCount; i++) {
            Dot dot = dotsList.get(i);
            setDotAttributes(i, dot);
        }
    }

    private void setDotAttributes(int i, Dot dot) {
        final int left = container.left + (dotsSize + dotsSpacing) * i;
        dot.set(left, container.top, left + dotsSize, container.bottom);
    }

    private int dpToPx(Context context, int dp) {
        return (int) (context.getResources().getDisplayMetrics().density * dp);
    }
}
