package com.techelevator.dao;

import com.techelevator.model.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    public void insertingProfileData(int userId, UserInfoDTO userInfoDto) {

        String sql = "INSERT INTO user_info (user_id, age, sex, weight, bolus_insulin_name, insulin_strength, target_min, target_max, basal_rate) "
         + "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

            jdbcTemplate.update (sql, userId, userInfoDto.getAge(), userInfoDto.getSex(), userInfoDto.getWeight(), userInfoDto.getBolusInsulinName(), userInfoDto.getInsulinStrength(),
                    userInfoDto.getTargetMin(), userInfoDto.getTargetMax(), userInfoDto.getBasalRate());

    }

}

