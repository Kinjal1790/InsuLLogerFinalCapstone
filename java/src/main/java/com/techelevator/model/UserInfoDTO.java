package com.techelevator.model;

import javax.validation.constraints.NotEmpty;

public class UserInfoDTO {


//    private int userInfoId;

    @NotEmpty
    private int age;
    @NotEmpty
    private String sex;
    @NotEmpty
    private int weight;
    @NotEmpty
    private String bolusInsulinName;
    @NotEmpty
    private int insulinStrength;
    @NotEmpty
    private int targetMin;
    @NotEmpty
    private int targetMax;
    @NotEmpty
    private int basalRate;

//    public int getUserInfoId() {
//        return userInfoId;
//    }
//
//    public void setUserInfoId(int userInfoId) {
//        this.userInfoId = userInfoId;
//    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getBolusInsulinName() {
        return bolusInsulinName;
    }

    public void setBolusInsulinName(String bolusInsulinName) {
        this.bolusInsulinName = bolusInsulinName;
    }

    public int getInsulinStrength() {
        return insulinStrength;
    }

    public void setInsulinStrength(int insulinStrength) {
        this.insulinStrength = insulinStrength;
    }

    public int getTargetMin() {
        return targetMin;
    }

    public void setTargetMin(int targetMin) {
        this.targetMin = targetMin;
    }

    public int getTargetMax() {
        return targetMax;
    }

    public void setTargetMax(int targetMax) {
        this.targetMax = targetMax;
    }

    public int getBasalRate() {
        return basalRate;
    }

    public void setBasalRate(int basalRate) {
        this.basalRate = basalRate;
    }
}
