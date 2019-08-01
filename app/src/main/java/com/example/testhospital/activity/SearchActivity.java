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

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
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
        setContentView(R.layout.activity_search);
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
        lable.add("左氧氟沙星注射液");
        lable.add("克林霉素针");
        lable.add("头孢类药物");
        lable.add("替硝锉注射液");
        lable.add("精制春风注射液");
        lable.add("胞二磷胆碱注射液");
        lable.add("阿莫西林胶囊");
        lable.add("破伤风");
        lable.add("青霉素");
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
