package com.example.testhospital.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.testhospital.R;
import com.example.testhospital.view.LineBreakLayout;

import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;

public class SaveRecordActivity extends AppCompatActivity implements View.OnClickListener{
    private LineBreakLayout lineBreakLayout;
    private List<String> selectedLables;


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
    }
    //初始化控件
    private void initView() {
        lineBreakLayout = findViewById(R.id.lineBreakLayout);
        findViewById(R.id.iv_back).setOnClickListener(this);
        initLael();
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
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
