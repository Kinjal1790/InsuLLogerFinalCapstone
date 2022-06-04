package com.techelevator.dao;

import com.techelevator.model.ReportDTO;
import com.techelevator.model.ReportFilterDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcReportDao implements ReportDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcReportDao(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<ReportDTO> filterReportData(ReportFilterDataDTO reportFilterDataDto, int id) {


        List<ReportDTO> reportlogs = new ArrayList<>();


        String sql = "SELECT  r.date_and_time, r.blood_sugar_reading, \n" +
                "       b.bolus_dose, u.target_min, u.target_max \n" +
                "      from user_info as u \n" +
                "      inner join reading_log as r on \n" +
                "           u.user_id = r.user_id \n" +
                "       inner join bolus_log as b on \n" +
                "           r.reading_log_id = b.reading_log_id \n" +
                "        where u.user_id = ? AND (SELECT r.date_and_time between ? AND ?) " +
                " ORDER BY r.date_and_time;";


        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id, LocalDate.parse(reportFilterDataDto.getDateFrom()), LocalDate.parse(reportFilterDataDto.getDateTo()));

        while(results.next()){
            ReportDTO reportLog = reportLogMap(results);
            reportlogs.add(reportLog);
        }

        if(reportFilterDataDto.getFilter() == 1) {
            return dailyAverage(reportlogs);
        } else if (reportFilterDataDto.getFilter() == 3) {
            return threeDayAverage(reportlogs);
        }

        return reportlogs;

    }

    private List<ReportDTO> dailyAverage(List <ReportDTO> reportlogs) {

        List<ReportDTO> filterList = new ArrayList<>();
        int totalBloodSugarReading = 0;
        double totalBolusDose = 0.0;
        int count = 0;
        for(int i = 0; i < reportlogs.size(); i++) {
            count ++;
            totalBloodSugarReading += reportlogs.get(i).getBloodSugarReading();
            totalBolusDose += reportlogs.get(i).getBolusDose();
            if(i+1 == reportlogs.size()) {
                ReportDTO temp = new ReportDTO();
                temp.setDateAndTime(reportlogs.get(i).getDateAndTime());
                temp.setDateFrom(reportlogs.get(i).getDateAndTime());
                temp.setDateTo(reportlogs.get(i).getDateAndTime());
                temp.setBloodSugarReading(totalBloodSugarReading / count);
                temp.setBolusDose(totalBolusDose / count);
                temp.setTargetMin(reportlogs.get(i).getTargetMin());
                temp.setTargetMax(reportlogs.get(i).getTargetMax());
                filterList.add(temp);
            }
            else if(reportlogs.get(i).getDateAndTime().compareTo(reportlogs.get(i+1).getDateAndTime()) != 0) {
                ReportDTO temp = new ReportDTO();
                temp.setDateAndTime(reportlogs.get(i).getDateAndTime());
                temp.setDateFrom(reportlogs.get(i).getDateAndTime());
                temp.setDateTo(reportlogs.get(i).getDateAndTime());
                temp.setBloodSugarReading(totalBloodSugarReading / count);
                temp.setBolusDose(totalBolusDose / count);
                temp.setTargetMin(reportlogs.get(i).getTargetMin());
                temp.setTargetMax(reportlogs.get(i).getTargetMax());
                filterList.add(temp);
                totalBloodSugarReading = 0;
                totalBolusDose = 0;
                count = 0;
            }
        }

        return filterList;
    }

    private List<ReportDTO> threeDayAverage(List <ReportDTO> reportlogs) {

        List<ReportDTO> filterList = new ArrayList<>();
        int totalBloodSugarReading = 0;
        double totalBolusDose = 0.0;
        int count = 0;
        LocalDate endDate = reportlogs.get(0).getDateAndTime().plusDays(3);
        for(int i = 0; i < reportlogs.size(); i++) {
            count ++;
            totalBloodSugarReading += reportlogs.get(i).getBloodSugarReading();
            totalBolusDose += reportlogs.get(i).getBolusDose();
            if(i+1 == reportlogs.size()) {
                ReportDTO temp = new ReportDTO();
                temp.setDateAndTime(reportlogs.get(i).getDateAndTime());
                temp.setDateFrom(reportlogs.get(i).getDateAndTime());
                temp.setDateTo(reportlogs.get(i).getDateAndTime());
                temp.setBloodSugarReading(totalBloodSugarReading / count);
                temp.setBolusDose(totalBolusDose / count);
                temp.setTargetMin(reportlogs.get(i).getTargetMin());
                temp.setTargetMax(reportlogs.get(i).getTargetMax());
                filterList.add(temp);
            }
            else if(reportlogs.get(i).getDateAndTime().compareTo(endDate) < 0) {
                ReportDTO temp = new ReportDTO();
                temp.setDateAndTime(reportlogs.get(i).getDateAndTime());
                temp.setDateFrom(reportlogs.get(i).getDateAndTime());
                temp.setDateTo(reportlogs.get(i).getDateAndTime());
                temp.setBloodSugarReading(totalBloodSugarReading / count);
                temp.setBolusDose(totalBolusDose / count);
                temp.setTargetMin(reportlogs.get(i).getTargetMin());
                temp.setTargetMax(reportlogs.get(i).getTargetMax());
                filterList.add(temp);
                totalBloodSugarReading = 0;
                totalBolusDose = 0;
                count = 0;
                endDate = endDate.plusDays(3);
            }
        }

        return filterList;
    }

    private ReportDTO reportLogMap(SqlRowSet results) {


        ReportDTO reportDto = new ReportDTO();

        LocalDateTime dt = (results.getTimestamp("date_and_time").toLocalDateTime());
        reportDto.setDateAndTime(dt.toLocalDate());
        reportDto.setBloodSugarReading(results.getInt("blood_sugar_reading"));
        reportDto.setBolusDose(results.getDouble("bolus_dose"));
        reportDto.setTargetMin(results.getInt("target_min"));
        reportDto.setTargetMax(results.getInt("target_max"));

        return reportDto;
    }
}
