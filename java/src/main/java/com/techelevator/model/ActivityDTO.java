package com.techelevator.model;

public class ActivityDTO {

    private String dataAndTime;
    private int userId;
    private double carbIntake;
    private int bloodSugarReading;
    private String warning;
    private String alert;
    private double bolus;

    public String getDataAndTime() {
        return dataAndTime;
    }

    public void setDataAndTime(String dataAndTime) {
        this.dataAndTime = dataAndTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getCarbIntake() {
        return carbIntake;
    }

    public void setCarbIntake(double carbIntake) {
        this.carbIntake = carbIntake;
    }

    public int getBloodSugarReading() {
        return bloodSugarReading;
    }

    public void setBloodSugarReading(int bloodSugarReading) {
        this.bloodSugarReading = bloodSugarReading;
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
