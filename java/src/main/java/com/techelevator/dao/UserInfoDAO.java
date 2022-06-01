package com.techelevator.dao;

import com.techelevator.model.UserInfoDTO;

public interface UserInfoDAO {

    void insertingProfileData(UserInfoDTO userInfodto);


    UserInfoDTO getProfileData(int id);

    void editProfileData(UserInfoDTO userInfoDto, int id);
}
