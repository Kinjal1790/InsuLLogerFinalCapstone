package com.techelevator.dao;

import com.techelevator.model.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;


@Service
public class JdbcUserInfoDao implements UserInfoDAO{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcUserInfoDao(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void insertingProfileData(UserInfoDTO userInfoDto) {

        String sql = "INSERT INTO user_info (user_id, yob, sex, weight, bolus_insulin_name, insulin_strength, target_min, target_max, basal_rate, sensitivity, carb_insulin_ratio) "
         + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            jdbcTemplate.update (sql, userInfoDto.getUserId(), userInfoDto.getYob(), userInfoDto.getSex(), userInfoDto.getWeight(), userInfoDto.getBolusInsulinName(), userInfoDto.getInsulinStrength(),
                    userInfoDto.getTargetMin(), userInfoDto.getTargetMax(), userInfoDto.getBasalRate(), userInfoDto.getSensitivity(), userInfoDto.getCarbInsulinRatio());

    }

    @Override
    public UserInfoDTO getProfileData(int id) {

        String sql = "SELECT * FROM user_info where user_id = ?";
        SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql, id);

        UserInfoDTO userInfoDto = null;
        if(results.next()) {
            userInfoDto = profileDataObjectMapper(results);
        }

        return userInfoDto;
    }

    @Override
    public void editProfileData(UserInfoDTO userInfoDto, int id) {
        String sql = "UPDATE user_info SET yob = ?, sex = ?, weight = ?, " +
                " bolus_insulin_name = ?, insulin_strength = ?, target_min = ?, target_max = ?,\n" +
                " basal_rate = ?, sensitivity = ?, carb_insulin_ratio = ?\n" +
                "WHERE user_id = ?;";

        jdbcTemplate.update (sql, userInfoDto.getYob(), userInfoDto.getSex(), userInfoDto.getWeight(), userInfoDto.getBolusInsulinName(),
                userInfoDto.getInsulinStrength(), userInfoDto.getTargetMin(), userInfoDto.getTargetMax(), userInfoDto.getBasalRate(),
                userInfoDto.getSensitivity(), userInfoDto.getCarbInsulinRatio(), userInfoDto.getUserId());

    }



    private UserInfoDTO profileDataObjectMapper(SqlRowSet results) {

        UserInfoDTO userInfoDto = new UserInfoDTO();
        userInfoDto.setUserId(results.getInt("user_id"));
        userInfoDto.setYob(results.getDate("yob").toLocalDate());
        userInfoDto.setSex(results.getString("sex"));
        userInfoDto.setWeight(results.getDouble("weight"));
        userInfoDto.setBolusInsulinName(results.getString("bolus_insulin_name"));
        userInfoDto.setInsulinStrength(results.getString("insulin_strength"));
        userInfoDto.setTargetMin(results.getInt("target_min"));
        userInfoDto.setTargetMax(results.getInt("target_max"));
        userInfoDto.setBasalRate(results.getDouble("basal_rate"));
        userInfoDto.setSensitivity(results.getDouble("sensitivity"));
        userInfoDto.setCarbInsulinRatio(results.getDouble("carb_insulin_ratio"));

        return userInfoDto;
    }
}

