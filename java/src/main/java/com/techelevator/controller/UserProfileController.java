package com.techelevator.controller;
import com.techelevator.dao.UserDAO;
import com.techelevator.dao.UserInfoDAO;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@CrossOrigin
public class UserProfileController {

    private UserDAO userDao;
    private UserInfoDAO userInfoDao;

    public UserProfileController(UserDAO userDao, UserInfoDAO userInfoDao) {
        this.userDao = userDao;
        this.userInfoDao = userInfoDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/profile_settings", method = RequestMethod.POST)
    public void profileSetUp(@Valid @RequestBody UserInfoDTO userInfoDto) {
        userInfoDao.insertingProfileData(userInfoDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path="/{id}/profile_settings", method = RequestMethod.GET)
    public UserInfoDTO getProfileSettings(@PathVariable int id) {
        return userInfoDao.getProfileData(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(path="/{id}/profile_settings", method = RequestMethod.PUT)
    public void editProfileSettings(@Valid @RequestBody UserInfoDTO userInfoDto, @PathVariable int id) {
        userInfoDao.editProfileData(userInfoDto, id);
    }

}
