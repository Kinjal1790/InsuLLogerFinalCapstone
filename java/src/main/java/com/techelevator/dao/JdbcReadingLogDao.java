package com.techelevator.dao;

import com.techelevator.model.ActivityDTO;
import com.techelevator.model.ReadingLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
    public Integer insertingReadingLogData(ReadingLogDTO readingLogDto) {

        Timestamp dateTime = Timestamp.valueOf(readingLogDto.getDataAndTime());

        String sql = "INSERT INTO reading_log (user_id, carb_intake, blood_sugar_reading, date_and_time, warning, alert)"
                + "VALUES (?, ?, ?, ?, ?, ?) returning reading_log_id;";

       Integer readingLogId = jdbcTemplate.queryForObject(sql, Integer.class, readingLogDto.getUserId(),
                readingLogDto.getCarbIntake(),
                readingLogDto.getBloodSugarReading(),
//              readingLogDto.getDataAndTime());
                dateTime,
                readingLogDto.getWarning(),
                readingLogDto.getAlert());

       return readingLogId;
    }

    @Override
    public Double calculateBolus(ReadingLogDTO readingLogDto, int readingLogId)
    {
        Double bolus = 0.0;
        String sql = "SELECT target_max, sensitivity, carb_insulin_ratio from user_info\n" +
                "where user_id = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, readingLogDto.getUserId());

        if(result.next()){
            Integer targetMax = result.getInt("target_max");
            double sensitivity = result.getDouble("sensitivity");
            double carbInsulinRatio = result.getDouble("carb_insulin_ratio");
            String warning = readingLogDto.getWarning();
            String alert = readingLogDto.getAlert();

            bolus = runBolusCalculation(targetMax, sensitivity, carbInsulinRatio, readingLogDto.getCarbIntake(), readingLogDto.getBloodSugarReading(), readingLogId, warning, alert);
        }


        return bolus;
    }

    private double runBolusCalculation(Integer targetMax, double sensitivity, double carbInsulinRatio, double carbIntake, double bloodSugarReading, int readingLogId, String warning, String alert){

        Double bolus = 0.0;

        if((!warning.equalsIgnoreCase("low")) && (!(alert.equalsIgnoreCase("low")))){
             bolus = (carbIntake/carbInsulinRatio) + ((bloodSugarReading - targetMax)/sensitivity);
        }

        String sql = "Insert into bolus_log (reading_log_id, bolus_dose) values (?,?);";

        jdbcTemplate.update(sql, readingLogId, bolus);

        return bolus;

    }


    @Override
    public List<ActivityDTO> getAllActivityLogs(int id) {
        List<ActivityDTO> activityLogs = new ArrayList<>();


        String sql = "SELECT r.date_and_time, r.user_id, r.carb_intake, r.blood_sugar_reading, b.bolus_dose, r.warning, r.alert " +
                " from reading_log as r " +
                " inner join bolus_log as b " +
                " on r.reading_log_id = b.reading_log_id " +
                " where r.user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()) {
            ActivityDTO activityLog = activityLogMap(results);
            activityLogs.add(activityLog);
        }
        return activityLogs;
    }


        private ActivityDTO activityLogMap(SqlRowSet results) {

            ActivityDTO activityLogDto = new ActivityDTO();

            activityLogDto.setDataAndTime(results.getTimestamp("date_and_time").toString());
            activityLogDto.setUserId(results.getInt("user_id"));
            activityLogDto.setCarbIntake(results.getDouble("carb_intake"));
            activityLogDto.setBloodSugarReading(results.getInt("blood_sugar_reading"));
            activityLogDto.setBolus(results.getDouble("bolus_dose"));
            activityLogDto.setWarning(results.getString("warning"));
            activityLogDto.setAlert(results.getString("alert"));
        return activityLogDto;
    }


//    @Override
//    public List<ReadingLogDTO> getAllReadingLogs(int id) {
//        List<ReadingLogDTO> readingLogs = new ArrayList<>();
//
////        String sql = "SELECT user_id, carb_intake, blood_sugar_reading, date_and_time, warning, alert " +
////                     "FROM reading_log WHERE user_id = ?";
//
//
//        String sql = "SELECT r.date_and_time, r.user_id, r.carb_intake, r.blood_sugar_reading, b.bolus_dose, r.warning, r.alert\n" +
//                "from reading_log as r\n" +
//                "inner join bolus_log as b\n" +
//                "on r.reading_log_id = b.reading_log_id\n" +
//                "where r.user_id = ?;";
//
//        SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql, id);
//        while (results.next()) {
//            ReadingLogDTO readingLog = readingLogMap(results);
//            readingLogs.add(readingLog);
//        }
//        return readingLogs;
//    }
//
//
//    private ReadingLogDTO readingLogMap(SqlRowSet results) {
//
//        ReadingLogDTO readingLogDTO = new ReadingLogDTO();
//        readingLogDTO.setUserId(results.getInt("user_id"));
//        readingLogDTO.setCarbIntake(results.getDouble("carb_intake"));
//        readingLogDTO.setBloodSugarReading(results.getInt("blood_sugar_reading"));
////      readingLogDTO.setDataAndTime(results.getString("date_and_time"));
//        readingLogDTO.setDataAndTime(results.getTimestamp("date_and_time").toString());
//        readingLogDTO.setWarning(results.getString("warning"));
//        readingLogDTO.setAlert(results.getString("alert"));
//
//        return readingLogDTO;
//    }

}
