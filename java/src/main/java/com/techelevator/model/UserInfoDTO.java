package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

public class UserInfoDTO {


//    private int userInfoId;

    @NotNull
    private int userId;

    @NotNull
    private LocalDate yob;
    @NotEmpty
    private String sex;
    @NotNull
    private double weight;
    @NotEmpty
    private String bolusInsulinName;
    @NotEmpty
    private String insulinStrength;
    @NotNull
    private int targetMin;
    @NotNull
    private int targetMax;
    @NotNull
    private double basalRate;

    @NotNull
    private double sensitivity;

    @NotNull
    private double carbInsulinRatio;

    public LocalDate getYob() {
        return yob;
    }

    public void setYob(LocalDate yob) {
        this.yob = yob;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getBasalRate() {
        return basalRate;
    }

    public void setBasalRate(double basalRate) {
        this.basalRate = basalRate;
    }

    public double getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(double sensitivity) {
        this.sensitivity = sensitivity;
    }

    public double getCarbInsulinRatio() {
        return carbInsulinRatio;
    }

    public void setCarbInsulinRatio(double carbInsulinRatio) {
        this.carbInsulinRatio = carbInsulinRatio;
    }
}
