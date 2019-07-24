package com.example.testhospital;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

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
    }

    private ImageView mImageView;
    ObjectAnimator animator=null;
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

    /**         * 恢复         */
    private void animatorResume() {
        animator.start();
        animator.setCurrentPlayTime(currentPlayTime);
    }

    /** * 暂停 */
    private void animatorPause() {
        currentPlayTime = animator.getCurrentPlayTime();
        animator.cancel();
    }
}
