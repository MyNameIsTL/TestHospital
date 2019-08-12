package com.example.testhospital.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.example.testhospital.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // hide titlebar of application
        // must be before setting the layout
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // hide statusbar of Android
        // could also be done later
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_home);
        dongHua();
        Log.d("Test","size"+getPingMuSize(this));
        int screenWidth  = getWindowManager().getDefaultDisplay().getWidth();       // 屏幕宽（像素，如：480px）
        int screenHeight = getWindowManager().getDefaultDisplay().getHeight();      // 屏幕高（像素，如：800p）

        Log.e("Test",  "screenWidth=" + screenWidth + "; screenHeight=" + screenHeight);

        // 获取屏幕密度（方法2）
        DisplayMetrics dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();

        float density  = dm.density;        // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        int densityDPI = dm.densityDpi;     // 屏幕密度（每寸像素：120/160/240/320）
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;

        Log.e("Test", "xdpi=" + xdpi + "; ydpi=" + ydpi);
        Log.e("Test", "density=" + density + "; densityDPI=" + densityDPI);

        screenWidth  = dm.widthPixels;      // 屏幕宽（像素，如：480px）
        screenHeight = dm.heightPixels;     // 屏幕高（像素，如：800px）

        Log.e("Test", "screenWidth=" + screenWidth + "; screenHeight=" + screenHeight);
    }

    /**
     * @ 获取当前手机屏幕的尺寸(单位:像素)
     */
    public static float getPingMuSize(Context mContext) { int densityDpi = mContext.getResources().getDisplayMetrics().densityDpi;
        float scaledDensity = mContext.getResources().getDisplayMetrics().scaledDensity;
        float density = mContext.getResources().getDisplayMetrics().density;
        float xdpi = mContext.getResources().getDisplayMetrics().xdpi;
        float ydpi = mContext.getResources().getDisplayMetrics().ydpi;
        int width = mContext.getResources().getDisplayMetrics().widthPixels;
        int height = mContext.getResources().getDisplayMetrics().heightPixels;

        // 这样可以计算屏幕的物理尺寸
        float width2 = (width / xdpi)*(width / xdpi);
        float height2 = (height / ydpi)*(width / xdpi);

        return (float) Math.sqrt(width2+height2);
    }

    private ImageView mImageView;
    ObjectAnimator animator = null;
    Long currentPlayTime;

    private void dongHua() {
        mImageView = (ImageView) findViewById(R.id.water_drop);
        // 创建动画作用对象：此处以Button为例

        float curTranslationY = mImageView.getTranslationY();
        // 获得当前按钮的位置
        animator = ObjectAnimator.ofFloat(mImageView, "translationY", curTranslationY, 120);
        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是X轴平移（在Y轴上平移同理，采用属性"translationY"
        // 动画效果是:从当前位置平移到 x=1500 再平移到初始位置
        animator.setDuration(5000);
        animator.setInterpolator(new AccelerateInterpolator(3f));
        animator.setRepeatCount(ValueAnimator.INFINITE);
        // animator.setStartDelay(1000);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                mImageView.setVisibility(View.GONE);
//                 try {
//                     animatorPause();
//                    Thread.sleep(1000);
//                     animatorResume();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();
    }

    /**
     * 恢复
     */
    private void animatorResume() {
        animator.start();
        animator.setCurrentPlayTime(currentPlayTime);
    }

    /**
     * 暂停
     */
    private void animatorPause() {
        currentPlayTime = animator.getCurrentPlayTime();
        animator.cancel();
    }
}
