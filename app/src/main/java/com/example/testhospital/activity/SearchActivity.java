package com.example.testhospital.activity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.testhospital.R;
import com.example.testhospital.view.LineBreakLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {
    private LineBreakLayout lineBreakLayout;
    private List<String> selectedLables;
    private StringBuffer mSerachContent = new StringBuffer("");
    private EditText et_search_content;
    private int currentCursor = 0;


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
        et_search_content = findViewById(R.id.et_search_content);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.key_pad_A).setOnClickListener(this);
        findViewById(R.id.key_pad_B).setOnClickListener(this);
        findViewById(R.id.key_pad_C).setOnClickListener(this);
        findViewById(R.id.key_pad_D).setOnClickListener(this);
        findViewById(R.id.key_pad_E).setOnClickListener(this);
        findViewById(R.id.key_pad_F).setOnClickListener(this);
        findViewById(R.id.key_pad_G).setOnClickListener(this);
        findViewById(R.id.key_pad_H).setOnClickListener(this);
        findViewById(R.id.key_pad_I).setOnClickListener(this);
        findViewById(R.id.key_pad_J).setOnClickListener(this);
        findViewById(R.id.key_pad_K).setOnClickListener(this);
        findViewById(R.id.key_pad_L).setOnClickListener(this);
        findViewById(R.id.key_pad_M).setOnClickListener(this);
        findViewById(R.id.key_pad_N).setOnClickListener(this);
        findViewById(R.id.key_pad_O).setOnClickListener(this);
        findViewById(R.id.key_pad_P).setOnClickListener(this);
        findViewById(R.id.key_pad_Q).setOnClickListener(this);
        findViewById(R.id.key_pad_R).setOnClickListener(this);
        findViewById(R.id.key_pad_S).setOnClickListener(this);
        findViewById(R.id.key_pad_T).setOnClickListener(this);
        findViewById(R.id.key_pad_U).setOnClickListener(this);
        findViewById(R.id.key_pad_V).setOnClickListener(this);
        findViewById(R.id.key_pad_W).setOnClickListener(this);
        findViewById(R.id.key_pad_X).setOnClickListener(this);
        findViewById(R.id.key_pad_Y).setOnClickListener(this);
        findViewById(R.id.key_pad_Z).setOnClickListener(this);
        findViewById(R.id.key_pad_del).setOnClickListener(this);
        findViewById(R.id.key_pad_save).setOnClickListener(this);
        initLael();
        et_search_content.setInputType(InputType.TYPE_NULL);
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
            case R.id.key_pad_A:
                getString("A");
                break;
            case R.id.key_pad_B:
                getString("B");
                break;
            case R.id.key_pad_C:
                getString("C");
                break;
            case R.id.key_pad_D:
                getString("D");
                break;
            case R.id.key_pad_E:
                getString("E");
                break;
            case R.id.key_pad_F:
                getString("F");
                break;
            case R.id.key_pad_G:
                getString("G");
                break;
            case R.id.key_pad_H:
                getString("H");
                break;
            case R.id.key_pad_I:
                getString("I");
                break;
            case R.id.key_pad_J:
                getString("J");
                break;
            case R.id.key_pad_K:
                getString("K");
                break;
            case R.id.key_pad_L:
                getString("L");
                break;
            case R.id.key_pad_M:
                getString("M");
                break;
            case R.id.key_pad_N:
                getString("N");
                break;
            case R.id.key_pad_O:
                getString("O");
                break;
            case R.id.key_pad_P:
                getString("P");
                break;
            case R.id.key_pad_Q:
                getString("Q");
                break;
            case R.id.key_pad_R:
                getString("R");
                break;
            case R.id.key_pad_S:
                getString("S");
                break;
            case R.id.key_pad_T:
                getString("T");
                break;
            case R.id.key_pad_U:
                getString("U");
                break;
            case R.id.key_pad_V:
                getString("V");
                break;
            case R.id.key_pad_W:
                getString("W");
                break;
            case R.id.key_pad_X:
                getString("X");
                break;
            case R.id.key_pad_Y:
                getString("Y");
                break;
            case R.id.key_pad_Z:
                getString("Z");
                break;
            case R.id.key_pad_del:
                deleteString();
                break;
            case R.id.key_pad_save:
                break;
        }
        if (v.getId() != R.id.iv_back) {
            et_search_content.setText(mSerachContent);
            et_search_content.setSelection(currentCursor + 1);
        }
    }

    private void getString(String content) {
        currentCursor = et_search_content.getSelectionStart();
        mSerachContent.insert(currentCursor, content);
    }

    private void deleteString() {
        if (mSerachContent.length() != 0 && currentCursor > -1) {
            currentCursor = et_search_content.getSelectionStart();
            mSerachContent.delete(currentCursor - 1, currentCursor);
            currentCursor = currentCursor - 2;
        }
    }
}
