package com.example.testhospital.data;

public class LoginInfo {
    private String id;//编号
    private String loginType;//登录方式 （0：人脸登录，1：密码登录）
    private String name;//姓名
    private String workNumber;//工号
    private String password;//密码
    private String loginTime;//登录时间
    private String isNormal;//是否正常交班（0：正常，1：异常）
    private String sencePhoto;//现场照片
    private String isLogin;//是否登录（0：登录,1:退出登录）

    public LoginInfo(String loginType, String name, String workNumber,
                     String password, String loginTime, String isNormal,
                     String sencePhoto, String isLogin) {
        this.loginType = loginType;
        this.name = name;
        this.workNumber = workNumber;
        this.password = password;
        this.loginTime = loginTime;
        this.isNormal = isNormal;
        this.sencePhoto = sencePhoto;
        this.isLogin = isLogin;
    }
}
