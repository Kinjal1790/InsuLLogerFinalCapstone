package com.techelevator.dao;

import com.techelevator.model.ReportDTO;
import com.techelevator.model.ReportFilterDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

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
                "        where u.user_id = ? AND (SELECT r.date_and_time::date)  between ? AND ?;";


        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id, reportFilterDataDto.getDateFrom(), reportFilterDataDto.getDateTo());

        while(results.next()){
            ReportDTO reportLog = reportLogMap(results);
            reportlogs.add(reportLog);
        }
        return reportlogs;

    }

    private ReportDTO reportLogMap(SqlRowSet results) {


        ReportDTO reportDto = new ReportDTO();

        reportDto.setDateAndTime(results.getTimestamp("date_and_time").toLocalDateTime());
        reportDto.setBloodSugarReading(results.getInt("blood_sugar_reading"));
        reportDto.setBolusDose(results.getDouble("bolus_dose"));
        reportDto.setTargetMin(results.getInt("target_min"));
        reportDto.setTargetMax(results.getInt("target_max"));

        return reportDto;
    }
}
