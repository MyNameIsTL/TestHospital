package com.example.testhospital.activity;

import android.os.Bundle;

import com.example.testhospital.R;
import com.example.testhospital.base.BaseActivity;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    @Override
    public void initView() {
        findView(R.id.tv_title);

    }

    @Override
    public void initData() {

    }
}
