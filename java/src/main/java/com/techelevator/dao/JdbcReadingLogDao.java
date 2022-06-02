package com.techelevator.dao;

import com.techelevator.model.ReadingLogDTO;
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
    public void insertingReadingLogData(ReadingLogDTO readingLogDto) {

        String sql = "INSERT INTO reading_log (user_id, carb_intake, blood_sugar_reading, date_and_time)"
                + "VALUES (?, ?, ?, ?)";

       jdbcTemplate.update(sql, readingLogDto.getUserId(),
                readingLogDto.getCarbIntake(),
                readingLogDto.getBloodSugarReading(),
                readingLogDto.getDataAndTime());


    }

    @Override
    public Double calculateBolus(ReadingLogDTO readingLogDto)
    {
        Double bolus = 0.0;
        String sql = "SELECT target_max, sensitivity, carb_insulin_ratio from user_info\n" +
                "where user_id = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, readingLogDto.getUserId());

        if(result.next()){
            double targetMax = result.getDouble("target_max");
            double sensitivity = result.getDouble("sensitivity");
            double carbInsulinRatio = result.getDouble("carb_insulin_ratio");
            bolus = runBolusCalculation(targetMax, sensitivity, carbInsulinRatio, readingLogDto.getCarbIntake(), readingLogDto.getBloodSugarReading());
        }

        return bolus;
    }

    private double runBolusCalculation(double targetMax, double sensitivity, double carbInsulinRatio, double carbIntake, double bloodSugarReading){

        return ((carbIntake/carbInsulinRatio) + ((bloodSugarReading - targetMax)/sensitivity));
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


    private ReadingLogDTO readingLogMap(SqlRowSet results) {

        ReadingLogDTO readingLogDTO = new ReadingLogDTO();
        readingLogDTO.setUserId(results.getInt("user_id"));
        readingLogDTO.setCarbIntake(results.getDouble("carb_intake"));
        readingLogDTO.setBloodSugarReading(results.getInt("blood_sugar_reading"));
        readingLogDTO.setDataAndTime(results.getString("date_and_time"));

        return readingLogDTO;
    }

}
