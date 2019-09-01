package com.example.testhospital.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testhospital.R;
import com.example.testhospital.utils.DateUtil;
import com.example.testhospital.view.LineBreakLayout;
import com.yxq.camera.CameraError;
import com.yxq.camera.CameraPreview;
import com.yxq.camera.CameraUtil;
import com.yxq.camera.OnCameraListener;

import java.util.ArrayList;
import java.util.List;

public class SaveRecordActivity extends AppCompatActivity implements View.OnClickListener {
    private LineBreakLayout lineBreakLayout;
    private List<String> selectedLables;
    private TextView mJobNumber;
    private TextView mPassword;
    private StringBuffer jobNumber = new StringBuffer("");
    private StringBuffer password = new StringBuffer("");
    private boolean isJobNumber = true;
    private ImageView ivJobNumber;
    private ImageView ivPassword;
    private LinearLayout llJobNumber;
    private LinearLayout llPassword;

    private LinearLayout llFaceLogin;
    private LinearLayout llInputLogin;
    private LinearLayout llNursePage;
    private LinearLayout llTrimeter;

    private LinearLayout llTrimeterLine;
    private TextView tvTemperature;
    private TextView tvPulse;
    private TextView tvBreathing;

    private TextView tvDate;
    private TextView tvTime;

    private ImageView ivModifyTrimeterTime;

    CameraPreview cp;

    private Boolean isLogin = false;

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
        setContentView(R.layout.activity_save_record);
        initView();
        startPreview();
        dongHua();
        new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                //实现页面跳转
                llFaceLogin.setVisibility(View.GONE);
                llInputLogin.setVisibility(View.VISIBLE);
                return false;
            }
        }).sendEmptyMessageDelayed(0, 10000);//表示延迟3秒发送任务
    }

    //初始化控件
    private void initView() {
        lineBreakLayout = findViewById(R.id.lineBreakLayout);
        mJobNumber = findViewById(R.id.tv_nurse_job_number);
        mPassword = findViewById(R.id.tv_password);
        ivJobNumber = findViewById(R.id.iv_job_number);
        ivPassword = findViewById(R.id.iv_password);
        llJobNumber = findViewById(R.id.ll_job_number);
        llPassword = findViewById(R.id.ll_password);

        llFaceLogin = findViewById(R.id.ll_face_login_page);
        llInputLogin = findViewById(R.id.ll_input_login_page);
        llNursePage = findViewById(R.id.ll_nurse_page);
        llTrimeter = findViewById(R.id.ll_trimeter);

        llTrimeterLine = findViewById(R.id.ll_trimeter_line);
        tvTemperature = findViewById(R.id.tv_temperature);
        tvPulse = findViewById(R.id.tv_pulse);
        tvBreathing = findViewById(R.id.tv_breathing);

        tvDate = findViewById(R.id.tv_date);
        tvTime = findViewById(R.id.tv_time);
        ivModifyTrimeterTime = findViewById(R.id.iv_modify_trimeter);

        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.key_pad_1).setOnClickListener(this);
        findViewById(R.id.key_pad_2).setOnClickListener(this);
        findViewById(R.id.key_pad_3).setOnClickListener(this);
        findViewById(R.id.key_pad_4).setOnClickListener(this);
        findViewById(R.id.key_pad_5).setOnClickListener(this);
        findViewById(R.id.key_pad_6).setOnClickListener(this);
        findViewById(R.id.key_pad_7).setOnClickListener(this);
        findViewById(R.id.key_pad_8).setOnClickListener(this);
        findViewById(R.id.key_pad_9).setOnClickListener(this);
        findViewById(R.id.key_pad_0).setOnClickListener(this);
        findViewById(R.id.key_pad_del).setOnClickListener(this);
        findViewById(R.id.key_pad_sure).setOnClickListener(this);
        //三测表页面跳转
        llTrimeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLogin) {
                    Toast.makeText(SaveRecordActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(SaveRecordActivity.this, InputTrimeterActivity.class);
                    startActivityForResult(intent, 100);
                }
            }
        });
        //三测表修改跳转
        ivModifyTrimeterTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isLogin) {
                    Toast.makeText(SaveRecordActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(SaveRecordActivity.this, InputTrimeterActivity.class);
                    startActivityForResult(intent, 100);
                }
            }
        });
        initLael();
        llJobNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isJobNumber = true;
                ivJobNumber.setImageResource(R.drawable.ic_job_number);
                ivPassword.setImageResource(R.drawable.ic_password_false);
            }
        });
        llPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isJobNumber = false;
                ivPassword.setImageResource(R.drawable.ic_password);
                ivJobNumber.setImageResource(R.drawable.ic_job_number_false);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("Test", "体温：" + data.getStringExtra("temperature"));
        Log.d("Test", "脉搏：" + data.getStringExtra("pulse"));
        Log.d("Test", "呼吸：" + data.getStringExtra("breathing"));
        if (isLogin) {
            llTrimeterLine.setVisibility(View.VISIBLE);
            tvTemperature.setText(data.getStringExtra("temperature"));
            tvPulse.setText(data.getStringExtra("pulse"));
            tvBreathing.setText(data.getStringExtra("breathing"));
            //添加时间工具类
            String date = DateUtil.formatCurrent(DateUtil.YEAR_MONTH_DAY_SLASH);
            String time = DateUtil.formatCurrent(DateUtil.HOUR_MINUTE);
            tvDate.setText(date);
            tvTime.setText(time);
        }
    }

    private void startPreview() {
        //找到控件
        CameraUtil.INSTANCE.setPreviewView(cp);//设置预览控件
        //设置预览监听
        CameraUtil.INSTANCE.setOnCameraListener(new OnCameraListener() {
            @Override
            public void onCameraDataFetched(byte[] bytes) {
                //实时获取相机数据
            }

            @Override
            public void onError(CameraError cameraError) {
                //打开相机错误
            }
        });
    }

    @Override
    protected void onDestroy() {
        cp = null;
        super.onDestroy();
    }

    private void dongHua() {
        ImageView mQrLineView = (ImageView) findViewById(R.id.scan_line);
        TranslateAnimation mAnimation = new TranslateAnimation(TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.RELATIVE_TO_PARENT, 0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.9f);
        mAnimation.setDuration(1500);
        mAnimation.setRepeatCount(-1);
        mAnimation.setRepeatMode(Animation.RESTART);
        mAnimation.setInterpolator(new LinearInterpolator());
        mQrLineView.setAnimation(mAnimation);
    }

    //初始化标签
    private void initLael() {
        List<String> lable = new ArrayList<>();
        lable.add("  防跌倒  ");
        lable.add("  防坠床  ");
        lable.add("  防压疮  ");
        lable.add("  防管路滑脱  ");
        lable.add("  防误吸  ");
        lable.add("  防窒息  ");
        lable.add("  防烫伤  ");
        lable.add("  防走失  ");
        lable.add("  绝对卧床  ");
        //设置标签
        lineBreakLayout.setLables(lable, true);
        //获取选中的标签
        selectedLables = lineBreakLayout.getSelectedLables();
    }

    //点击事件
    @SuppressLint("ShowToast")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.key_pad_0:
                getString("0");
                break;
            case R.id.key_pad_1:
                getString("1");
                break;
            case R.id.key_pad_2:
                getString("2");
                break;
            case R.id.key_pad_3:
                getString("3");
                break;
            case R.id.key_pad_4:
                getString("4");
                break;
            case R.id.key_pad_5:
                getString("5");
                break;
            case R.id.key_pad_6:
                getString("6");
                break;
            case R.id.key_pad_7:
                getString("7");
                break;
            case R.id.key_pad_8:
                getString("8");
                break;
            case R.id.key_pad_9:
                getString("9");
                break;
            case R.id.key_pad_del:
                deleteString();
                break;
            case R.id.key_pad_sure:
                if (jobNumber.length() == 0) {
                    Toast.makeText(SaveRecordActivity.this, "请输入工号", Toast.LENGTH_SHORT).show();
                    break;
                }
                if (password.length() == 0) {
                    Toast.makeText(SaveRecordActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    break;
                }
                llInputLogin.setVisibility(View.GONE);
                llNursePage.setVisibility(View.VISIBLE);
                isLogin = true;
                break;
        }
        if (v.getId() != R.id.iv_back) {
            if (isJobNumber)
                mJobNumber.setText(jobNumber);
            else
                mPassword.setText(password);
        }
    }

    private void getString(String content) {
        if (isJobNumber) {
            jobNumber.append(content);
        } else {
            password.append(content);
        }
    }

    private void deleteString() {
        if (isJobNumber) {
            if (jobNumber.length() > 0)
                jobNumber.delete(jobNumber.length() - 1, jobNumber.length());
        } else {
            if (password.length() > 0)
                password.delete(password.length() - 1, password.length());
        }
    }
}
