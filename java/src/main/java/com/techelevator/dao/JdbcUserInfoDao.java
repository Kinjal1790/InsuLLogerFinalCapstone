package com.techelevator.dao;

import com.techelevator.model.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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

}

