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

    private UserInfoDTO profileDataObjectMapper(SqlRowSet results) {

        UserInfoDTO userInfoDto = new UserInfoDTO();
        userInfoDto.setUserId(results.getInt("user_id"));
        System.out.println("*");
        userInfoDto.setYob(results.getDate("yob").toLocalDate());
        System.out.println("**");
        userInfoDto.setSex(results.getString("sex"));
        System.out.println("***");
        userInfoDto.setWeight(results.getInt("weight"));
        System.out.println("****");
        userInfoDto.setBolusInsulinName(results.getString("bolus_insulin_name"));
        System.out.println("*****");
        userInfoDto.setInsulinStrength(results.getString("insulin_strength"));
        System.out.println("******");
        userInfoDto.setTargetMin(results.getInt("target_min"));
        System.out.println("*******");
        userInfoDto.setTargetMax(results.getInt("target_max"));
        System.out.println("********");
        userInfoDto.setBasalRate(results.getInt("basal_rate"));
        System.out.println("*********");
        userInfoDto.setSensitivity(results.getInt("sensitivity"));
        System.out.println("**********");
        userInfoDto.setCarbInsulinRatio(results.getInt("carb_insulin_ratio"));

        return userInfoDto;
    }
}

