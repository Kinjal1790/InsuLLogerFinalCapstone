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


        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id, LocalDate.parse(reportFilterDataDto.getDateFrom()), (LocalDate.parse(reportFilterDataDto.getDateTo()).plusDays(1)));

        while(results.next()){
            ReportDTO reportLog = reportLogMap(results);
            reportlogs.add(reportLog);
        }



       return calculateAverage(reportlogs, reportFilterDataDto.getFilter());
    }


    private List<ReportDTO> calculateAverage(List <ReportDTO> reportlogs, int filter) {

        List<ReportDTO> filterList = new ArrayList<>();
        int totalBloodSugarReading = 0;
        double totalBolusDose = 0.0;
        int noOfDate = 1;
        int rowCount = 0;
        LocalDate currentDate = reportlogs.get(0).getDate();
        LocalDate fromDate = currentDate;
        for(int i = 0; i < reportlogs.size(); i++) {
            if(currentDate.compareTo(reportlogs.get(i).getDate()) != 0 ){
                noOfDate++;
                currentDate = reportlogs.get(i).getDate();
                if(noOfDate == filter+1){

                    ReportDTO temp = new ReportDTO();

                    temp.setDateFrom(fromDate);
                    temp.setDateTo(reportlogs.get(i-1).getDate());
                    temp.setBloodSugarReading(Math.round((double)totalBloodSugarReading / rowCount));
                    temp.setBolusDose((double)totalBolusDose / rowCount);
                    temp.setTargetMin(reportlogs.get(i).getTargetMin());
                    temp.setTargetMax(reportlogs.get(i).getTargetMax());
                    totalBloodSugarReading = 0;
                    totalBolusDose = 0;
                    rowCount = 0;
                    noOfDate = 1;
                    fromDate = reportlogs.get(i).getDate();
                    filterList.add(temp);
                }
            }
            if(noOfDate <= filter) {
                rowCount ++;
                totalBloodSugarReading += reportlogs.get(i).getBloodSugarReading();
                totalBolusDose += reportlogs.get(i).getBolusDose();
            }
        }

        ReportDTO temp = new ReportDTO();
        temp.setDateFrom(fromDate);
        temp.setDateTo(reportlogs.get(reportlogs.size()-1).getDate());
        temp.setBloodSugarReading(Math.round((double)totalBloodSugarReading / rowCount));
        temp.setBolusDose((double)totalBolusDose / rowCount);
        temp.setTargetMin(reportlogs.get(reportlogs.size()-1).getTargetMin());
        temp.setTargetMax(reportlogs.get(reportlogs.size()-1).getTargetMax());
        filterList.add(temp);


        return filterList;
    }

    private ReportDTO reportLogMap(SqlRowSet results) {


        ReportDTO reportDto = new ReportDTO();

        LocalDateTime dt = (results.getTimestamp("date_and_time").toLocalDateTime());
        reportDto.setDate(dt.toLocalDate());
        reportDto.setBloodSugarReading(results.getInt("blood_sugar_reading"));
        reportDto.setBolusDose(results.getDouble("bolus_dose"));
        reportDto.setTargetMin(results.getInt("target_min"));
        reportDto.setTargetMax(results.getInt("target_max"));

        return reportDto;
    }
}
