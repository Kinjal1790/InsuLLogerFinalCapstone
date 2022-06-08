package com.techelevator.dao;

import com.techelevator.model.ActivityDTO;
import com.techelevator.model.AlertInfoDTO;
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

        String sql = "SELECT target_max, target_min from user_info\n" +
                "where user_id = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, readingLogDto.getUserId());
        Integer targetMax = 0;
        Integer targetMin = 0;
        if(result.next()) {
            targetMax = result.getInt("target_max");
            targetMin = result.getInt("target_min");

        }

        if(readingLogDto.getBloodSugarReading() <=65){
            readingLogDto.setWarning("low");
            readingLogDto.setAlert("");
        }
        else if(readingLogDto.getBloodSugarReading()>65 && readingLogDto.getBloodSugarReading()<targetMin){
            readingLogDto.setAlert("low");
            readingLogDto.setWarning("");
        }
        else if(readingLogDto.getBloodSugarReading()>=targetMin && readingLogDto.getBloodSugarReading()<=targetMax){
            readingLogDto.setAlert("");
            readingLogDto.setWarning("");
        }
        else if(readingLogDto.getBloodSugarReading()>targetMax && readingLogDto.getBloodSugarReading()<300){
            readingLogDto.setWarning("");
            readingLogDto.setAlert("high");
        }
        else if(readingLogDto.getBloodSugarReading()>=300){
            readingLogDto.setWarning("high");
            readingLogDto.setAlert("");
        }


        Timestamp dateTime = Timestamp.valueOf(readingLogDto.getDataAndTime());

        String sql1 = "INSERT INTO reading_log (user_id, carb_intake, blood_sugar_reading, date_and_time, warning, alert)"
                + "VALUES (?, ?, ?, ?, ?, ?) returning reading_log_id;";

       Integer readingLogId = jdbcTemplate.queryForObject(sql1, Integer.class, readingLogDto.getUserId(),
                readingLogDto.getCarbIntake(),
                readingLogDto.getBloodSugarReading(),
//              readingLogDto.getDataAndTime());
                dateTime,
                readingLogDto.getWarning(),
                readingLogDto.getAlert());

       return readingLogId;
    }

    @Override
    public AlertInfoDTO calculateBolus(ReadingLogDTO readingLogDto, int readingLogId)
    {
        Double bolus = 0.0;
        String sql = "SELECT target_max, target_min, sensitivity, carb_insulin_ratio from user_info\n" +
                "where user_id = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, readingLogDto.getUserId());
        Integer targetMax = 0;
        Integer targetMin = 0;
        String warning = "";
        String alert = "";
        if(result.next()){
            targetMax = result.getInt("target_max");
            targetMin = result.getInt("target_min");
            double sensitivity = result.getDouble("sensitivity");
            double carbInsulinRatio = result.getDouble("carb_insulin_ratio");
             warning = readingLogDto.getWarning();
             alert = readingLogDto.getAlert();

            bolus = runBolusCalculation(targetMax, sensitivity, carbInsulinRatio, readingLogDto.getCarbIntake(), readingLogDto.getBloodSugarReading(), readingLogId, warning, alert);
        }

        AlertInfoDTO alertInfodto = new AlertInfoDTO();
        alertInfodto.setBolus(bolus);
        alertInfodto.setWarning(warning);
        alertInfodto.setAlert(alert);
        alertInfodto.setTargetMin(targetMin);
        alertInfodto.setTargetMax(targetMax);

        return alertInfodto;
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
                " where r.user_id = ? " +
                " order by r.date_and_time desc; ";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        while (results.next()) {
            ActivityDTO activityLog = activityLogMap(results);
            activityLogs.add(activityLog);
        }
        return activityLogs;
    }

    @Override
    public List<ActivityDTO> getAllUserActivityLog() {

        List<ActivityDTO> allUserActivityLogs = new ArrayList<>();

        String sql = "SELECT r.date_and_time, r.user_id, r.carb_intake, r.blood_sugar_reading, b.bolus_dose, r.warning, r.alert " +
                " from reading_log as r " +
                " inner join bolus_log as b " +
                " on r.reading_log_id = b.reading_log_id " +
                " order by r.date_and_time desc; ";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            ActivityDTO activityLog = activityLogMap(results);
            allUserActivityLogs.add(activityLog);
        }
        return allUserActivityLogs;
        
    }


    private ActivityDTO activityLogMap(SqlRowSet results) {

            ActivityDTO activityLogDto = new ActivityDTO();

            activityLogDto.setDataAndTime(results.getTimestamp("date_and_time").toString());
            activityLogDto.setUserId(results.getInt("user_id"));
            activityLogDto.setCarbIntake(results.getDouble("carb_intake"));
            activityLogDto.setBloodSugarReading(results.getInt("blood_sugar_reading"));
            if (results.getDouble("bolus_dose")<0){
               activityLogDto.setBolus(0.0);
            }
            else{
               activityLogDto.setBolus(results.getDouble("bolus_dose"));
            }
            activityLogDto.setWarning(results.getString("warning"));
            activityLogDto.setAlert(results.getString("alert"));
        return activityLogDto;
    }


//    @Override
//    public List<ActivityDTO> getUserActivityLogById(int id) {
//
//        List<ActivityDTO> UserActivityLogs = new ArrayList<>();
//
//        String sql = "SELECT r.date_and_time, r.user_id, r.carb_intake, r.blood_sugar_reading, b.bolus_dose, r.warning, r.alert " +
//                " from reading_log as r " +
//                " inner join bolus_log as b " +
//                " on r.reading_log_id = b.reading_log_id " +
//                " where r.user_id = ? " +
//                " order by r.date_and_time desc; ";
//
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
//        while (results.next()) {
//            ActivityDTO activityLog = activityLogMap(results);
//            UserActivityLogs.add(activityLog);
//        }
//        return UserActivityLogs;
//    }



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
