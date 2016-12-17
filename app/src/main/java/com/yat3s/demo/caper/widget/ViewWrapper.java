package com.yat3s.demo.caper.widget;

import android.view.View;

/**
 * Created by Yat3s on 17/12/2016.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class ViewWrapper {
    private View targetView;

    public ViewWrapper(View targetView) {
        this.targetView = targetView;
    }

    public void setWidth(int width) {
        targetView.getLayoutParams().width = width;
        targetView.requestLayout();
    }

    public void setHeight(int height) {
        targetView.getLayoutParams().height = height;
        targetView.requestLayout();
    }

    public int getWidth() {
        return targetView.getLayoutParams().width;
    }

    public int getHeight() {
        return targetView.getLayoutParams().height;
    }
}
