package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ReadingLogDTO {
//    @NotEmpty
//    private int readingLogId;

    @NotNull
    private int userId;
    @NotEmpty
    private double carbIntake;
    @NotEmpty
    private int bloodSugarReading;

    @NotEmpty
    private LocalDateTime dataAndTime;

    @NotEmpty
    private double bolusDose;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getDataAndTime() {
        return dataAndTime;
    }

    public void setDataAndTime(LocalDateTime dataAndTime) {
        this.dataAndTime = dataAndTime;
    }

//    public int getReadingLogId() {
//        return readingLogId;
//    }
//
//    public void setReadingLogId(int readingLogId) {
//        this.readingLogId = readingLogId;
//    }



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

    public double getBolusDose() {
        return bolusDose;
    }

    public void setBolusDose(double bolusDose) {
        this.bolusDose = bolusDose;
    }
}
