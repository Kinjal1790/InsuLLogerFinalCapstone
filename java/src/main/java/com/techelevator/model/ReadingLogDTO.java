package com.techelevator.model;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class ReadingLogDTO {
    @NotEmpty
    private int readingLog;
    @NotEmpty
    private int carbIntake;
    @NotEmpty
    private int bloodSugarReading;
    @NotEmpty
    private int bolusDose;


    @NotEmpty
    private LocalDateTime dataAndTime;


    public LocalDateTime getDataAndTime() {
        return dataAndTime;
    }

    public void setDataAndTime(LocalDateTime dataAndTime) {
        this.dataAndTime = dataAndTime;
    }

    public int getReadingLog() {
        return readingLog;
    }

    public void setReadingLog(int readingLog) {
        this.readingLog = readingLog;
    }

    public int getCarbIntake() {
        return carbIntake;
    }

    public void setCarbIntake(int carbIntake) {
        this.carbIntake = carbIntake;
    }

    public int getBloodSugarReading() {
        return bloodSugarReading;
    }

    public void setBloodSugarReading(int bloodSugarReading) {
        this.bloodSugarReading = bloodSugarReading;
    }

    public int getBolusDose() {
        return bolusDose;
    }

    public void setBolusDose(int bolusDose) {
        this.bolusDose = bolusDose;
    }
}
