package com.example.testhospital.data;

/**
 * Created by TL on 2019/8/19.
 */

public class TestJavaBean {
    private String userName;
    private String password;

    @Override
    public String toString() {
        return "TestJavaBean{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TestJavaBean() {
    }
}
