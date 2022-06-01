package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

public class UserInfoDTO {


//    private int userInfoId;

    @NotNull
    private int userId;

    @NotEmpty
    private LocalDate yob;
    @NotEmpty
    private String sex;
    @NotNull
    private int weight;
    @NotEmpty
    private String bolusInsulinName;
    @NotEmpty
    private String insulinStrength;
    @NotNull
    private int targetMin;
    @NotNull
    private int targetMax;
    @NotNull
    private int basalRate;

    @NotNull
    private int sensitivity;

    @NotNull
    private int carbInsulinRatio;

    public LocalDate getYob() {
        return yob;
    }

    public void setYob(LocalDate yob) {
        this.yob = yob;
    }

    public int getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(int sensitivity) {
        this.sensitivity = sensitivity;
    }

    public int getCarbInsulinRatio() {
        return carbInsulinRatio;
    }

    public void setCarbInsulinRatio(int carbInsulinRatio) {
        this.carbInsulinRatio = carbInsulinRatio;
    }
//    public int getUserInfoId() {
//        return userInfoId;
//    }
//
//    public void setUserInfoId(int userInfoId) {
//        this.userInfoId = userInfoId;
//    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

//    public String getYob() {
//        return yob;
//    }
//    public void setYob(String yob) {
//        this.yob = yob;
//    }

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

    public String getInsulinStrength() {
        return insulinStrength;
    }

    public void setInsulinStrength(String insulinStrength) {
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
