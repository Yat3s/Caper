package com.yat3s.demo.caper;

import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String PROPERTY_ROTATION = "rotation";
    private static final float MENU_CLOSED_ANGLE = -90f;
    private static final float MENU_OPENED_ANGLE = 0f;
    private static final long DEFAULT_DURATION = 500;

    @BindView(R.id.content_layout)
    AnimateLayout contentLayout;
    @BindView(R.id.menu_btn)
    ImageView menuBtn;
    @BindView(R.id.toolbar)
    FrameLayout toolbar;

    private boolean menuIsOpen = true;
    private ObjectAnimator mOpenMenuAnimator, mCloseMenuAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toolbar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    toolbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    toolbar.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                contentLayout.setPivotX(toolbar.getHeight() / 2);
                contentLayout.setPivotY(toolbar.getHeight() / 2);
            }
        });
        configureAnimation();

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuIsOpen) {
                    mCloseMenuAnimator.start();
                } else {
                    mOpenMenuAnimator.start();
                }
                menuIsOpen = !menuIsOpen;
            }
        });
    }

    private void configureAnimation() {
        mOpenMenuAnimator = ObjectAnimator.ofFloat(contentLayout, PROPERTY_ROTATION, MENU_OPENED_ANGLE).setDuration
                (DEFAULT_DURATION);
        mOpenMenuAnimator.setInterpolator(new GuillotineInterpolator());

        mCloseMenuAnimator = ObjectAnimator.ofFloat(contentLayout, PROPERTY_ROTATION, MENU_CLOSED_ANGLE).setDuration
                (DEFAULT_DURATION);
        mCloseMenuAnimator.setInterpolator(new GuillotineInterpolator());


    }
}
