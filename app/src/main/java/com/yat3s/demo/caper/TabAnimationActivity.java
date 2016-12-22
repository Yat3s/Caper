package com.yat3s.demo.caper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yat3s.demo.caper.util.SystemBarTintManager;

import butterknife.ButterKnife;

/**
 * Created by Yat3s on 22/12/2016.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class TabAnimationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_animation);
        SystemBarTintManager systemBarTintManager = new SystemBarTintManager(this);
        systemBarTintManager.setStatusBarTintEnabled(true);
        systemBarTintManager.setNavigationBarTintEnabled(true);

        ButterKnife.bind(this);

    }


}
