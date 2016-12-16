package com.yat3s.demo.caper;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yat3s.demo.caper.widget.GuillotineInterpolator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String PROPERTY_ROTATION = "rotation";
    private static final float MENU_CLOSED_ANGLE = -90f;
    private static final float MENU_OPENED_ANGLE = 0f;
    private static final long DEFAULT_DURATION = 500;

    @BindView(R.id.content_layout)
    LinearLayout contentLayout;
    @BindView(R.id.menu_btn)
    ImageView menuBtn;
    @BindView(R.id.toolbar)
    FrameLayout toolbar;
    @BindView(R.id.title_tv)
    TextView titleTv;

    private boolean menuIsOpen = false;
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
                    closeMenu();
                } else {
                    openMenu();
                }
                menuIsOpen = !menuIsOpen;
            }
        });
        contentLayout.setRotation(MENU_CLOSED_ANGLE);
    }

    private void openMenu() {
        mOpenMenuAnimator.start();
        titleTv.setVisibility(View.GONE);
    }

    private void closeMenu() {
        mCloseMenuAnimator.start();
    }

    private void configureAnimation() {
        mOpenMenuAnimator = ObjectAnimator.ofFloat(contentLayout, PROPERTY_ROTATION, MENU_CLOSED_ANGLE, MENU_OPENED_ANGLE)
                .setDuration(DEFAULT_DURATION);
        mOpenMenuAnimator.setInterpolator(new GuillotineInterpolator());

        mCloseMenuAnimator = ObjectAnimator.ofFloat(contentLayout, PROPERTY_ROTATION, MENU_OPENED_ANGLE, MENU_CLOSED_ANGLE)
                .setDuration(DEFAULT_DURATION);
        mCloseMenuAnimator.setInterpolator(new GuillotineInterpolator());
        mCloseMenuAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                titleTv.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
