package com.yat3s.demo.caper;

import android.content.Context;
import android.support.v4.content.PermissionChecker;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Yat3s on 12/13/16.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class AnimateLayout extends FrameLayout {
    public AnimateLayout(Context context) {
        super(context);
    }

    public AnimateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void setCustomWidth(int width) {
        getLayoutParams().width = width;
        requestLayout();
    }

    private void setCustomHeight(int height) {
        getLayoutParams().height = height;
        requestLayout();
    }
}
