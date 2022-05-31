package com.techelevator.controller;
import com.techelevator.dao.UserDAO;
import com.techelevator.dao.UserInfoDAO;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.security.Principal;

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
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public void profileSetUp(Principal principal, @Valid @RequestBody UserInfoDTO userInfoDto) {

        String email = principal.getName();
        int userInfoId = userDao.findIdByEmail(email);
        userInfoDao.insertingProfileData(userInfoId, userInfoDto);

    }
}
