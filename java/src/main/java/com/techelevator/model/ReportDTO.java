package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReportDTO {

    private LocalDateTime dateAndTime;
    private int bloodSugarReading;
    private double bolusDose;
    private int targetMin;
    private int targetMax;

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getBloodSugarReading() {
        return bloodSugarReading;
    }

    public void setBloodSugarReading(int bloodSugarReading) {
        this.bloodSugarReading = bloodSugarReading;
    }

    public double getBolusDose() {
        return bolusDose;
    }

    public void setBolusDose(double bolusDose) {
        this.bolusDose = bolusDose;
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
}
