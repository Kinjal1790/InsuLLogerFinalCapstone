package com.techelevator.controller;

import javax.mail.MessagingException;
import javax.validation.Valid;

import com.techelevator.service.EmailSenderService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.LoginDTO;
import com.techelevator.model.RegisterUserDTO;
import com.techelevator.model.User;
import com.techelevator.model.UserAlreadyExistsException;
import com.techelevator.security.jwt.JWTFilter;
import com.techelevator.security.jwt.TokenProvider;




@RestController
@CrossOrigin
public class AuthenticationController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private UserDAO userDao;

    private final EmailSenderService senderService;

    public AuthenticationController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, UserDAO userDao, EmailSenderService senderService) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userDao = userDao;
        this.senderService = senderService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginDTO loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, false);
        
        User user = userDao.findByEmail(loginDto.getEmail());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new LoginResponse(jwt, user), httpHeaders, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@Valid @RequestBody RegisterUserDTO newUser) throws MessagingException {
        try {
            User user = userDao.findByEmail(newUser.getEmail());
            throw new UserAlreadyExistsException();
        } catch (UsernameNotFoundException e) {
            boolean userCreated = userDao.create(newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), newUser.getPassword(), newUser.getRole());
            if(userCreated){
                triggerMail(newUser.getEmail());

            }
        }


    }

   // @EventListener(ApplicationReadyEvent.class)
    public void triggerMail(String toEmail) throws MessagingException {
        senderService.sendSimpleEmail(toEmail,
                "Thank you for creating an iTracker account",
                "Tracking your blood sugar levels and insulin just got a whole lot easier! \n" +
                        "\n" +
                        "You registered for an account with iTracker, a multipurpose insulin tracking tool. iTracker helps you monitor your basal blood sugar level, calculate bolus insulin doses, and view detailed summaries of your blood sugar levels so you can focus less on diabetes, and focus more on doing what you love. Visit iTracker today to set up your custom profile. \n" +
                        "\n" +
                        "Happy tracking! \n" +
                        "\n" +
                        "Sincerely,\n" +
                        "\n" +
                        "The iTracker Team\n");
    }


    /**
     * Object to return as body in JWT Authentication.
     */
    static class LoginResponse {

        private String token;
        private User user;

        LoginResponse(String token, User user) {
            this.token = token;
            this.user = user;
        }

        @JsonProperty("token")
        String getToken() {
            return token;
        }

        void setToken(String token) {
            this.token = token;
        }

        @JsonProperty("user")
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}
    }
}

