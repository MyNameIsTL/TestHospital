package com.example.testhospital.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.testhospital.R;
import com.example.testhospital.base.BaseActivity;
import com.example.testhospital.data.BedInfo;
import com.example.testhospital.data.CostInquiry;
import com.example.testhospital.data.DataEntry;
import com.example.testhospital.data.LoginInfo;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
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

        ArrayList<CostInquiry> list = new ArrayList<>();
        CostInquiry costInquiry = new CostInquiry(
                "挂号费",
                "次",
                "次",
                "6.00",
                "1",
                "6.00");
        list.add(costInquiry);
        CostInquiry costInquiry1 = new CostInquiry(
                "住院诊查费",
                "日/次",
                "次",
                "14.00",
                "2",
                "28.00");
        list.add(costInquiry1);
        CostInquiry costInquiry2 = new CostInquiry(
                "床位费",
                "张",
                "日",
                "180.00",
                "1",
                "180.00");
        list.add(costInquiry2);
        CostInquiry costInquiry3 = new CostInquiry(
                "建档费",
                "次",
                "次",
                "8.00",
                "1",
                "8.00");
        list.add(costInquiry3);
        CostInquiry costInquiry4 = new CostInquiry(
                "一般专项护理",
                "次",
                "次",
                "9.00",
                "1",
                "9.00");
        list.add(costInquiry4);
        System.out.println(list.toString());
        String v = new Gson().toJson(list);
        //JSONArray jsonArray = JSONArray.fromObject(list);//这样就将ArrayList转化成json 数据格式了
        Log.d("Test", v);
        List<String> list1 = new ArrayList<>();
        list1.add("青霉素");
        list1.add("链霉素");
        list1.add("破伤风");

        List<String> list2 = new ArrayList<>();
        list2.add("绝对卧床");
        list2.add("预防压疮");
        list2.add("防跌倒");
        list2.add("防坠床");
        BedInfo bedInfo = new BedInfo(
                "23",
                "内分泌科三病区",
                "23床",
                "刘祥宇",
                "男",
                "29岁",
                "00944311584",
                "2019/08/31 14:59",
                "食物中毒",
                "等级护理",
                "半流质饮食",
                list1,
                "李国华",
                "",
                "廖玲",
                "",
                list2
        );
        Log.d("Test", new Gson().toJson(bedInfo));

        DataEntry dataEntry = new DataEntry(
                "2010/08/31 15:14",
                "廖玲",
                "37.2",
                "80",
                "55",
                "100",
                "100",
                "173",
                "60",
                "2",
                "3",
                "30",
                "50",
                list1
        );
        Log.d("Test", new Gson().toJson(dataEntry));
        LoginInfo loginInfo = new LoginInfo(
                "1",
                "廖玲",
                "100001",
                "111111",
                "2019/08/31 15:38",
                "0",
                "",
                "0"
        );
        Log.d("Test", new Gson().toJson(loginInfo));
    }

    @Override
    public void initView() {
        findView(R.id.tv_title);

    }

    @Override
    public void initData() {

    }
}
