package com.techelevator.dao;

import com.techelevator.model.ReadingLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
public class JdbcReadingLogDao implements ReadingLogDAO{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcReadingLogDao(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Integer insertingReadingLogData(ReadingLogDTO readingLogDto) {

        String sql = "INSERT INTO reading_log (user_id, carb_intake, blood_sugar_reading, date_and_time)"
                + "VALUES (?, ?, ?, ?) returning reading_log_id;";

        int readingLogId = jdbcTemplate.update(sql, Integer.class, readingLogDto.getUserId(),
                readingLogDto.getCarbIntake(),
                readingLogDto.getBloodSugarReading(),
                readingLogDto.getDataAndTime());

        return readingLogId;
    }

//    public int calculatedBolus(int readingLogId){
//
//    }






}
