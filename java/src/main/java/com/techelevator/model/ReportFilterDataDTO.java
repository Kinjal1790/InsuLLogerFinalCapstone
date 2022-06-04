package com.techelevator.model;

public class ReportFilterDataDTO {

    private String dateFrom;
    private String dateTo;
    private int filter;

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    @Override
    public String toString() {
        return "ReportFilterDataDTO{" +
                "dateFrom='" + dateFrom + '\'' +
                ", dateTo='" + dateTo + '\'' +
                ", filter=" + filter +
                '}';
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public int getFilter() {
        return filter;
    }

    public void setFilter(int filter) {
        this.filter = filter;
    }
}
