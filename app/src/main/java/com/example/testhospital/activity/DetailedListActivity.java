package com.example.testhospital.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.example.testhospital.R;
import com.example.testhospital.adapter.PayServiceAdapter;
import com.example.testhospital.data.PayServiceInfo;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class DetailedListActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView mListView;
    private List<PayServiceInfo> mList = new ArrayList<>();
    private static int TOTAL = 10;

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
        setContentView(R.layout.activity_detaile_list);
        initData();
        initView();
    }

    private void initData() {
        mList.add(new PayServiceInfo("挂号费", "次", "次", "6.00", "1", "6.00"));
        mList.add(new PayServiceInfo("住院诊查费", "日/次", "次", "14.00", "2", "28.00"));
        mList.add(new PayServiceInfo("床位费", "张", "日", "180.00", "1", "180.00"));
        mList.add(new PayServiceInfo("建档费", "次", "次", "8.00", "1", "8.00"));
        mList.add(new PayServiceInfo("一般专项护理", "次", "次", "9.00", "1", "9.00"));
        int size = mList.size();
        if (size < TOTAL) {
            for (int i = 0; i < TOTAL - size; i++) {
                mList.add(new PayServiceInfo("", "", "", "", "", ""));
            }
        }
    }

    private void initView() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        ((TextView) findViewById(R.id.title)).setText("成都市人民医院住院费用清单");
        mListView = findViewById(R.id.list_view);
        PayServiceAdapter adapter = new PayServiceAdapter(this, mList);
        mListView.setAdapter(adapter);
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
