package com.yat3s.demo.caper;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yat3s.demo.caper.widget.AnimateLayout;
import com.yat3s.demo.caper.widget.ContentTextView;
import com.yat3s.demo.caper.widget.GuillotineInterpolator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String GITHUB = "https://github.com/yat3s";
    private static final String PROPERTY_ROTATION = "rotation";
    private static final float MENU_CLOSED_ANGLE = -90f;
    private static final float MENU_OPENED_ANGLE = 0f;
    private static final long DEFAULT_DURATION = 500;

    @BindView(R.id.profile_layout)
    LinearLayout profileLayout;
    @BindView(R.id.menu_btn)
    ImageView menuBtn;
    @BindView(R.id.toolbar)
    FrameLayout toolbar;
    @BindView(R.id.title_tv)
    ContentTextView titleTv;
    @BindView(R.id.performance_left_layout)
    AnimateLayout performanceLeftLayout;
    @BindView(R.id.performance_right_layout)
    AnimateLayout performanceRightLayout;
    @BindView(R.id.performance_viewpager)
    ViewPager performanceViewpager;
    @BindView(R.id.performance_top_layout)
    AnimateLayout performanceTopLayout;
    @BindView(R.id.performance_bottom_layout)
    AnimateLayout performanceBottomLayout;

    private boolean menuIsOpen = false;
    private ObjectAnimator mOpenMenuAnimator, mCloseMenuAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        configureProfile();
        configureViewpager();
        configurePerformanceLayout();
    }


    @OnClick({R.id.performance_left_layout, R.id.performance_right_layout, R.id.performance_bottom_layout})
    public void performance(View view) {
        float topHighlightHeight = App.sScreenHeight * 0.5f;
        switch (view.getId()) {
            case R.id.performance_left_layout:
                performanceLeftLayout.animateSize(App.sScreenWidth * 0.7f, topHighlightHeight);
                performanceRightLayout.animateSize(App.sScreenWidth * 0.3f, topHighlightHeight);
                performanceTopLayout.animateSize(App.sScreenWidth, topHighlightHeight);
                performanceBottomLayout.animateSize(App.sScreenWidth, App.sScreenHeight * 0.3f);
                break;
            case R.id.performance_right_layout:
                performanceLeftLayout.animateSize(App.sScreenWidth * 0.3f, topHighlightHeight);
                performanceRightLayout.animateSize(App.sScreenWidth * 0.7f, topHighlightHeight);
                performanceTopLayout.animateSize(App.sScreenWidth, topHighlightHeight);
                performanceBottomLayout.animateSize(App.sScreenWidth, App.sScreenHeight * 0.3f);
                break;
            case R.id.performance_bottom_layout:
                performanceTopLayout.animateSize(App.sScreenWidth, App.sScreenHeight * 0.3f);
                performanceBottomLayout.animateSize(App.sScreenWidth, App.sScreenHeight * 0.4f);
                performanceBottomLayout.animateMagic();
                break;
        }
    }

    @OnClick({R.id.demo1_item, R.id.demo2_item, R.id.github_item, R.id.email_item})
    public void profileItem(View view) {
        switch (view.getId()) {
            case R.id.demo1_item:
                startActivity(new Intent(this, TabAnimationActivity.class));
                break;
            case R.id.demo2_item:
                break;
            case R.id.github_item:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(GITHUB)));
                break;
            case R.id.email_item:
                break;
        }
    }

    private void configurePerformanceLayout() {
        performanceLeftLayout.initSize(App.sScreenWidth * 0.5f, App.sScreenHeight * 0.3f);
        performanceRightLayout.initSize(App.sScreenWidth * 0.5f, App.sScreenHeight * 0.3f);
        performanceTopLayout.initSize(App.sScreenWidth, App.sScreenHeight * 0.3f);
        performanceBottomLayout.initSize(App.sScreenWidth, App.sScreenHeight * 0.4f);
    }

    private void configureViewpager() {
        final String[] itemTitles = new String[]{"KEEP CALM\nAND \nSLEEP NOW", "CODING \nOR\nDIE", "EVERYTHING\nWILL BE\nOK"};

        performanceViewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return itemTitles.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ContentTextView view = (ContentTextView) getLayoutInflater().inflate(R.layout.item_text, container, false);
                view.setText(itemTitles[position]);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        performanceViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                float processValue = (position + positionOffset) / (itemTitles.length - 1);
                int value = (Integer) new ArgbEvaluator().evaluate(processValue, Color.WHITE, Color.GREEN);
                performanceViewpager.setBackgroundColor(value);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void configureProfile() {
        // Configure rotation pivot.
        profileLayout.setVisibility(View.VISIBLE);
        toolbar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    toolbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    toolbar.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                profileLayout.setPivotX(toolbar.getHeight() / 2);
                profileLayout.setPivotY(toolbar.getHeight() / 2);
            }
        });

        // Configure animation.
        mOpenMenuAnimator = ObjectAnimator.ofFloat(profileLayout, PROPERTY_ROTATION, MENU_CLOSED_ANGLE, MENU_OPENED_ANGLE)
                .setDuration(DEFAULT_DURATION);
        mOpenMenuAnimator.setInterpolator(new GuillotineInterpolator());

        mCloseMenuAnimator = ObjectAnimator.ofFloat(profileLayout, PROPERTY_ROTATION, MENU_OPENED_ANGLE, MENU_CLOSED_ANGLE)
                .setDuration(DEFAULT_DURATION);
        mCloseMenuAnimator.setInterpolator(new GuillotineInterpolator());
        mCloseMenuAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menuIsOpen) {
                    mCloseMenuAnimator.start();
                    titleTv.animate().alpha(1).start();
                } else {
                    mOpenMenuAnimator.start();
                    titleTv.animate().alpha(0).start();
                }
                menuIsOpen = !menuIsOpen;
            }
        });

        profileLayout.setRotation(MENU_CLOSED_ANGLE);
    }
}
