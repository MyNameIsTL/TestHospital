package com.example.testhospital.data;

//入参 departmentId \bedId \startTime\endTime
//费用查询数据结构
public class CostInquiry {
    private String payService;//收费项目
    private String specifications;//规格
    private String unit;//单位
    private String price;//单价
    private String number;//数量
    private String amount;//金额
    private String totalAmount;//合计

    private String totalCosts;//费用总金额
    private String prepayBalance ;//预交金余额
    private String qrCodeRecharge;//二维码充值 图片

    public CostInquiry(String payService, String specifications, String unit, String price, String number, String amount) {
        this.payService = payService;
        this.specifications = specifications;
        this.unit = unit;
        this.price = price;
        this.number = number;
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "CostInquiry{" +
                "payService='" + payService + '\'' +
                ", specifications='" + specifications + '\'' +
                ", unit='" + unit + '\'' +
                ", price='" + price + '\'' +
                ", number='" + number + '\'' +
                ", amount='" + amount + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", totalCosts='" + totalCosts + '\'' +
                ", prepayBalance='" + prepayBalance + '\'' +
                ", qrCodeRecharge='" + qrCodeRecharge + '\'' +
                '}';
    }

    public String getPayService() {
        return payService;
    }

    public void setPayService(String payService) {
        this.payService = payService;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(String totalCosts) {
        this.totalCosts = totalCosts;
    }

    public String getPrepayBalance() {
        return prepayBalance;
    }

    public void setPrepayBalance(String prepayBalance) {
        this.prepayBalance = prepayBalance;
    }

    public String getQrCodeRecharge() {
        return qrCodeRecharge;
    }

    public void setQrCodeRecharge(String qrCodeRecharge) {
        this.qrCodeRecharge = qrCodeRecharge;
    }
}
