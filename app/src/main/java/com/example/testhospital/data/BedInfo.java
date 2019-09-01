package com.example.testhospital.data;

import java.util.List;

public class BedInfo {
    private String bedId;//床位编号
    private String departmentName;//科室名称
    private String bedName;//床位名称
    private String patientName;//患者姓名
    private String patientSex;//患者性别
    private String patientAge;//患者年龄
    private String hospitalizationNumber;//住院号
    private String hospitalizationTime;//住院时间
    private String hospitalizationDiagnosis;//住院诊断
    private String nursingType;//护理类型
    private String dietType;//饮食类型
    private List<String> allergens;//过敏源列表，size=0表示无过敏源
    private String doctorName;//主治医生姓名
    private String doctorPhoto;//主治医生照片
    private String nurseName;//责任护士姓名
    private String nursePhoto;//责任护士照片
    private List<String> attentionMatters;//注意事项，size=0表示无注意事项

    public BedInfo(String bedId, String departmentName, String bedName,
                   String patientName, String patientSex, String patientAge,
                   String hospitalizationNumber, String hospitalizationTime,
                   String hospitalizationDiagnosis, String nursingType,
                   String dietType, List<String> allergens, String doctorName,
                   String doctorPhoto, String nurseName, String nursePhoto,
                   List<String> attentionMatters) {
        this.bedId = bedId;
        this.departmentName = departmentName;
        this.bedName = bedName;
        this.patientName = patientName;
        this.patientSex = patientSex;
        this.patientAge = patientAge;
        this.hospitalizationNumber = hospitalizationNumber;
        this.hospitalizationTime = hospitalizationTime;
        this.hospitalizationDiagnosis = hospitalizationDiagnosis;
        this.nursingType = nursingType;
        this.dietType = dietType;
        this.allergens = allergens;
        this.doctorName = doctorName;
        this.doctorPhoto = doctorPhoto;
        this.nurseName = nurseName;
        this.nursePhoto = nursePhoto;
        this.attentionMatters = attentionMatters;
    }
}
