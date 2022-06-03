package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class ReadingLogDTO {

    @NotNull
    private int userId;
    @NotNull
    private double carbIntake;
    @NotNull
    private int bloodSugarReading;

    @NotEmpty
    private String dataAndTime;


    private String warning;
    private String alert;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDataAndTime() {
        return dataAndTime;
    }

    public void setDataAndTime(String dataAndTime) {
        this.dataAndTime = dataAndTime;
    }


    public int getBloodSugarReading() {
        return bloodSugarReading;
    }

    public void setBloodSugarReading(int bloodSugarReading) {
        this.bloodSugarReading = bloodSugarReading;
    }

    public double getCarbIntake() {
        return carbIntake;
    }

    public void setCarbIntake(double carbIntake) {
        this.carbIntake = carbIntake;
    }


}
