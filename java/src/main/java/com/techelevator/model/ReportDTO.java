package com.techelevator.model;

import java.time.LocalDate;

public class ReportDTO {

    private LocalDate date;
    private double bloodSugarReading;
    private double bolusDose;
    private int targetMin;
    private int targetMax;
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getBloodSugarReading() {
        return bloodSugarReading;
    }

    @Override
    public String toString() {
        return "ReportDTO{" +
                "dateAndTime=" + date +
                ", bloodSugarReading=" + bloodSugarReading +
                ", bolusDose=" + bolusDose +
                ", targetMin=" + targetMin +
                ", targetMax=" + targetMax +
                '}';
    }

    public void setBloodSugarReading(double bloodSugarReading) {
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
