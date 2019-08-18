package com.example.testhospital.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testhospital.R;
import com.example.testhospital.view.PickerView;

import java.util.ArrayList;
import java.util.List;

public class InputTrimeterActivity extends AppCompatActivity implements View.OnClickListener {
    String temperature1 = "37";
    String temperature2 = "5";
    String pulse1 = "6";
    String pulse2 = "6";
    String breathing1 = "6";
    String breathing2 = "6";

    PickerView pickerTemperatureInt;
    PickerView pickerTemperatureDec;
    PickerView pickerPulseTen;
    PickerView pickerPulseBit;
    PickerView pickerBreathingTen;
    PickerView pickerBreathingBit;

    List<PickerView.Pickers> temperatureIntegerData = new ArrayList<>();
    List<PickerView.Pickers> temperatureDecimalData = new ArrayList<>();
    List<PickerView.Pickers> pulseTenPlaceData = new ArrayList<>();
    List<PickerView.Pickers> pulseBitData = new ArrayList<>();
    List<PickerView.Pickers> breathingTenPlaceData = new ArrayList<>();
    List<PickerView.Pickers> breathingBitData = new ArrayList<>();


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
        setContentView(R.layout.activity_input_trimeter);
        initView();
        for (int i = 35; i < 43; i++) {
            PickerView.Pickers pickers = new PickerView.Pickers((i) + "", i + "");
            temperatureIntegerData.add(pickers);
        }

        for (int i = 0; i < 10; i++) {
            PickerView.Pickers pickers = new PickerView.Pickers((i) + "", i + "");
            temperatureDecimalData.add(pickers);
            pulseTenPlaceData.add(pickers);
            pulseBitData.add(pickers);
            breathingTenPlaceData.add(pickers);
            breathingBitData.add(pickers);
        }
        pickerTemperatureInt.setData(temperatureIntegerData);
        pickerTemperatureInt.setSelected("37");
        pickerTemperatureDec.setData(temperatureDecimalData);
        pickerTemperatureDec.setSelected("5");

        pickerPulseTen.setData(pulseTenPlaceData);
        pickerPulseTen.setSelected("6");
        pickerPulseBit.setData(pulseBitData);
        pickerPulseBit.setSelected("6");

        pickerBreathingTen.setData(breathingTenPlaceData);
        pickerBreathingTen.setSelected("6");
        pickerBreathingBit.setData(breathingBitData);
        pickerBreathingBit.setSelected("6");

        pickerTemperatureInt.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(PickerView.Pickers pickers) {
                Log.d("Test", "体温1:" + pickers.getShowConetnt());
                temperature1 = pickers.getShowConetnt();
            }
        });
        pickerTemperatureDec.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(PickerView.Pickers pickers) {
                Log.d("Test", "体温2:" + pickers.getShowConetnt());
                temperature2 = pickers.getShowConetnt();
            }
        });
        pickerPulseTen.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(PickerView.Pickers pickers) {
                Log.d("Test", "脉搏1:" + pickers.getShowConetnt());
                pulse1 = pickers.getShowConetnt();
            }
        });
        pickerPulseBit.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(PickerView.Pickers pickers) {
                Log.d("Test", "脉搏2:" + pickers.getShowConetnt());
                pulse2 = pickers.getShowConetnt();
            }
        });
        pickerBreathingTen.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(PickerView.Pickers pickers) {
                Log.d("Test", "呼吸1:" + pickers.getShowConetnt());
                breathing1 = pickers.getShowConetnt();
            }
        });
        pickerBreathingBit.setOnSelectListener(new PickerView.onSelectListener() {
            @Override
            public void onSelect(PickerView.Pickers pickers) {
                Log.d("Test", "呼吸2:" + pickers.getShowConetnt());
                breathing2 = pickers.getShowConetnt();
            }
        });
    }

    //初始化控件
    private void initView() {
        pickerTemperatureInt = findViewById(R.id.pv_temperature_integer);
        pickerTemperatureDec = findViewById(R.id.pv_temperature_decimal);
        pickerPulseTen = findViewById(R.id.pv_pulse_ten_place);
        pickerPulseBit = findViewById(R.id.pv_pulse_bit);
        pickerBreathingTen = findViewById(R.id.pv_breathing_ten_place);
        pickerBreathingBit = findViewById(R.id.pv_breathing_bit);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.title).setVisibility(View.GONE);
    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                Intent intent = new Intent();
                intent.putExtra("temperature", temperature1 + "." + temperature2);
                intent.putExtra("pulse", pulse1 + pulse2);
                intent.putExtra("breathing", breathing1 + breathing2);
                setResult(100, intent);
                finish();
                break;
        }
    }
}
