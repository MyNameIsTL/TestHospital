package com.example.testhospital.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.testhospital.R;

import androidx.appcompat.app.AppCompatActivity;

public class HospitalizationNotesActivity extends AppCompatActivity implements View.OnClickListener {

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
        setContentView(R.layout.activity_hospitalization_notes);
        init();
    }

    private void init() {
        String content = "尊敬的患者及家属：\n" +
                "\n" +
                "1、介绍科室的主任、护士长及责任护士，以方便患者沟通；介绍环境及公共设施。\n" +
                "\n" +
                "2、医保患者须携带身份证、医保卡、异地农合患者须带当地转诊证明，农合证、身份证及住院押金。\n" +
                "\n" +
                "3、患者住院期间无特殊情况不要离开医院，有事外出需经主管医生或值班护士许可后方可离开。每日费用清单中的疑虑处请找责任护士或护士长咨询。\n" +
                "\n" +
                "4、在病情需要时，医嘱会要求您留守陪护，如陪护有事离开病人请告知护士。\n" +
                "\n" +
                "5、治疗、查房期间请关闭电视；探视人员请遵守医院的陪护、探视制度，保持病区安静。\n" +
                "\n" +
                "6、住院期间请您注意节约用水、电，爱护公共设施；病室内禁止使用各种电器。\n" +
                "\n" +
                "7、住院期间请您妥善保管好贵重物品。如需要帮助请按呼叫器。\n" +
                "\n" +
                "8、医院免费为行动不便的患者提供接送检查等服务。定餐电话2625617免费使用微波炉。\n" +
                "\n" +
                "9、打水、饮水时请不要将水洒在地面上，老年及体质虚弱者注意稳起慢行，以防跌倒。\n" +
                "\n" +
                "10、昏迷、躁动、意识障碍或小儿、老年患者等，请加床护挡，以免坠床。\n" +
                "\n" +
                "11、使用热水袋是请在护士指导下使用，暖水瓶倒水时请抓稳慢倒，防烫伤。\n" +
                "\n" +
                "12、有义齿者告知患者注意防止义齿松动，脱落导致误咽。\n" +
                "\n" +
                "13、入厕或洗浴时请勿将门锁死，夜间入厕请先开灯，扶好站稳，活动不便者请呼叫护士协助。\n" +
                "\n" +
                "14、住院期间，护士会根据您的病情及时进行专科健康知识的指导。\n" +
                "\n" +
                "15、为了您及他人健康，请不要吸烟，病区有指定吸烟区。晚23点关闭电视，以免影响他人。\n" +
                "\n" +
                "16、须办理慢性病的患者请您到病案室复印相关资料，携带照片、出院证、医保卡、身份证到指定的医院办理（区卡需到单位领取盖章的慢性病申请表）。";
        ((TextView) findViewById(R.id.tv_content)).setText(content);
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
