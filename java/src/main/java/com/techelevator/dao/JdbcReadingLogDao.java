package com.techelevator.dao;

import com.techelevator.model.Bolus;
import com.techelevator.model.ReadingLogDTO;
import com.techelevator.model.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class JdbcReadingLogDao implements ReadingLogDAO{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcReadingLogDao(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertingReadingLogData(ReadingLogDTO readingLogDto) {

        String sql = "INSERT INTO reading_log (user_id, carb_intake, blood_sugar_reading, date_and_time)"
                + "VALUES (?, ?, ?, ?) RETURNING reading_log_id;";

       Integer readingLogId = jdbcTemplate.queryForObject(sql, Integer.class, readingLogDto.getUserId(),
                readingLogDto.getCarbIntake(),
                readingLogDto.getBloodSugarReading(),
                readingLogDto.getDataAndTime());

       return readingLogId;
    }

    @Override
    public List<ReadingLogDTO> getAllReadingLogs(int id) {
        List<ReadingLogDTO> readingLogs = new ArrayList<>();
        String sql = "SELECT carb_intake, blood_sugar_reading, date_and_time " +
                "FROM reading_log WHERE user_id = ?";

        SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()) {
            ReadingLogDTO readingLog = readingLogMap(results);
            readingLogs.add(readingLog);
        }
        return readingLogs;
    }

    @Override
    public Bolus calculateBolus(int id)
    {
        Bolus bolus = new Bolus();

        String sql = "SELECT r.carb_intake, u.whatever FROM reading_log r JOIN user_info u ON r.user_id = u.user_id";
//        double carbIntake = .getDouble ("r.carb_intake");

        return bolus;
    }


    private ReadingLogDTO readingLogMap(SqlRowSet results) {

        ReadingLogDTO readingLogDTO = new ReadingLogDTO();
        readingLogDTO.setUserId(results.getInt("user_id"));
        readingLogDTO.setCarbIntake(results.getDouble("carb_intake"));
        readingLogDTO.setBloodSugarReading(results.getInt("blood_sugar_reading"));
        readingLogDTO.setDataAndTime(results.getString("date_and_time"));

        return readingLogDTO;
    }

}
