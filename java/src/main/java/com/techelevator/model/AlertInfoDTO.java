package com.techelevator.model;

public class AlertInfoDTO {


    private double bolus;

    private String warning;
    private String alert;

    private int targetMin;

    private int targetMax;

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

    public String getWarning() {
        return warning;
    }

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public double getBolus() {
        return bolus;
    }

    public void setBolus(double bolus) {
        this.bolus = bolus;
    }
}
