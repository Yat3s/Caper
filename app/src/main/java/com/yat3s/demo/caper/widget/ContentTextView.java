package com.yat3s.demo.caper.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.yat3s.demo.caper.App;

/**
 * Created by Yat3s on 12/15/16.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class ContentTextView extends TextView {
    public ContentTextView(Context context) {
        this(context, null);
    }

    public ContentTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContentTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
//        if (!isInEditMode()) {
//            setTypeface(App.sContentTextTypeface);
//        }
    }
}
