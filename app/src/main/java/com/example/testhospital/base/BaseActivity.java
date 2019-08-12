package com.example.testhospital.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by TL on 2019/8/3.
 */

public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public abstract void initView();

    public abstract void initData();

    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }
}
