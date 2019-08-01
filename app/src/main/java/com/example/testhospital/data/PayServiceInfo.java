package com.example.testhospital.data;

/**
 * Created by TL on 2019/8/1.
 */

public class PayServiceInfo {
    private String  mPayService;
    private String  mSpecifications;
    private String  mCompany;
    private String  mUnitPrice;
    private String  mNumber;
    private String  mAmountMoney;

    public PayServiceInfo(String mPayService, String mSpecifications, String mCompany, String mUnitPrice, String mNumber, String mAmountMoney) {
        this.mPayService = mPayService;
        this.mSpecifications = mSpecifications;
        this.mCompany = mCompany;
        this.mUnitPrice = mUnitPrice;
        this.mNumber = mNumber;
        this.mAmountMoney = mAmountMoney;
    }

    public String getmPayService() {
        return mPayService;
    }

    public void setmPayService(String mPayService) {
        this.mPayService = mPayService;
    }

    public String getmSpecifications() {
        return mSpecifications;
    }

    public void setmSpecifications(String mSpecifications) {
        this.mSpecifications = mSpecifications;
    }

    public String getmCompany() {
        return mCompany;
    }

    public void setmCompany(String mCompany) {
        this.mCompany = mCompany;
    }

    public String getmUnitPrice() {
        return mUnitPrice;
    }

    public void setmUnitPrice(String mUnitPrice) {
        this.mUnitPrice = mUnitPrice;
    }

    public String getmNumber() {
        return mNumber;
    }

    public void setmNumber(String mNumber) {
        this.mNumber = mNumber;
    }

    public String getmAmountMoney() {
        return mAmountMoney;
    }

    public void setmAmountMoney(String mAmountMoney) {
        this.mAmountMoney = mAmountMoney;
    }
}
