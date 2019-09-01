package com.example.testhospital.data;

import java.util.List;

public class DataEntry {
    private String updateTime;//录入时间
    private String nurseName;//录入人
    //三测表
    private String bodyTemperature;//体温
    private String pulse;//脉搏
    private String breathing;//呼吸

    private String bloodSugar;//血糖
    private String bloodPressure;//血压
    private String height;//身高
    private String weight;//体重
    private String fecesNumber;//大便次数
    private String urinateNumber;//小便次数
    private String outAmount;//出量
    private String intAmount;//入量
    private List<String> allergens;//药物过敏

    public DataEntry(String updateTime, String nurseName, String bodyTemperature, String pulse,
                     String breathing, String bloodSugar, String bloodPressure,
                     String height, String weight, String fecesNumber, String urinateNumber,
                     String outAmount, String intAmount, List<String> allergens) {
        this.updateTime = updateTime;
        this.nurseName = nurseName;
        this.bodyTemperature = bodyTemperature;
        this.pulse = pulse;
        this.breathing = breathing;
        this.bloodSugar = bloodSugar;
        this.bloodPressure = bloodPressure;
        this.height = height;
        this.weight = weight;
        this.fecesNumber = fecesNumber;
        this.urinateNumber = urinateNumber;
        this.outAmount = outAmount;
        this.intAmount = intAmount;
        this.allergens = allergens;
    }
}
