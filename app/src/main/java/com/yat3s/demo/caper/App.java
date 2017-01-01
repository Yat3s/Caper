package com.yat3s.demo.caper;

import android.app.Application;
import android.graphics.Typeface;

/**
 * Created by Yat3s on 12/15/16.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class App extends Application {
    private static final String PATH_CONTENT_TEXT_TYPEFACE = "fonts/DAYPBL__.ttf";
    public static Typeface sContentTextTypeface;

    @Override
    public void onCreate() {
        super.onCreate();

        sContentTextTypeface = Typeface.createFromAsset(getAssets(), PATH_CONTENT_TEXT_TYPEFACE);
    }
}
