package com.example.testhospital.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.testhospital.R;
import com.example.testhospital.base.BaseActivity;

import static com.example.testhospital.activity.HomeActivity.getPingMuSize;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Log.d("Test", "size" + getPingMuSize(this));
        int screenWidth = getWindowManager().getDefaultDisplay().getWidth();       // 屏幕宽（像素，如：480px）
        int screenHeight = getWindowManager().getDefaultDisplay().getHeight();      // 屏幕高（像素，如：800p）
        Log.e("Test", "screenWidth=" + screenWidth + "; screenHeight=" + screenHeight);
        // 获取屏幕密度（方法2）
        DisplayMetrics dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();
        float density = dm.density;        // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        int densityDPI = dm.densityDpi;     // 屏幕密度（每寸像素：120/160/240/320）
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;
        Log.e("Test", "xdpi=" + xdpi + "; ydpi=" + ydpi);
        Log.e("Test", "density=" + density + "; densityDPI=" + densityDPI);
        screenWidth = dm.widthPixels;      // 屏幕宽（像素，如：480px）
        screenHeight = dm.heightPixels;     // 屏幕高（像素，如：800px）
        Log.e("Test", "screenWidth=" + screenWidth + "; screenHeight=" + screenHeight);
    }

    @Override
    public void initView() {
        findView(R.id.tv_title);

    }

    @Override
    public void initData() {

    }
}
