package com.yat3s.demo.caper.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;

import com.yat3s.demo.caper.App;

/**
 * Created by Yat3s on 12/13/16.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class AnimateLayout extends LinearLayout {
    private static final String TAG = "AnimateLayout";
    private static final String PROPERTY_WIDTH = "customWidth";
    private static final String PROPERTY_HEIGHT = "customHeight";
    private static final int ANIMATE_DURATION = 600;

    public AnimateLayout(Context context) {
        this(context, null);
    }

    public AnimateLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void animateSize(float width, float height) {
        startAnimate(ObjectAnimator.ofFloat(this, PROPERTY_WIDTH, width));
        startAnimate(ObjectAnimator.ofFloat(this, PROPERTY_HEIGHT, height));
    }

    public void animateSizePercent(float widthPercent, float heightPercent) {
        animateSize((int) (App.sScreenWidth * widthPercent), (int) (App.sScreenHeight * heightPercent));
    }

    private void startAnimate(Animator animator) {
        animator.setInterpolator(new OvershootInterpolator());
        animator.setDuration(ANIMATE_DURATION);
        animator.start();
    }

    public float getCustomHeight() {
        return this.getLayoutParams().height;
    }

    public float getCustomWidth() {
        return this.getLayoutParams().width;
    }

    public void setCustomWidth(float width) {
        this.getLayoutParams().width = (int) width;
        this.requestLayout();
    }

    public void setCustomHeight(float height) {
        this.getLayoutParams().height = (int) height;
        this.requestLayout();
    }

    public void initSize(float width, float height) {
        setCustomWidth(width);
        setCustomHeight(height);
    }
}
