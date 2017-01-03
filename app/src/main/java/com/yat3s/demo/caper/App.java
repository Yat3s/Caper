package com.yat3s.demo.caper;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Yat3s on 12/15/16.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class App extends Application {
    private static final String PATH_CONTENT_TEXT_TYPEFACE = "fonts/DAYPBL__.ttf";
    public static Typeface sContentTextTypeface;

    public static int sScreenWidth, sScreenHeight;

    @Override
    public void onCreate() {
        super.onCreate();

        sContentTextTypeface = Typeface.createFromAsset(getAssets(), PATH_CONTENT_TEXT_TYPEFACE);
        sScreenHeight = getScreenHeight(getApplicationContext());
        sScreenWidth = getScreenWidth(getApplicationContext());
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }
}
