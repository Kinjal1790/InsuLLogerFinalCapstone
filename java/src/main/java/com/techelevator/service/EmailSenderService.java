package com.techelevator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender mailSender;


    public void sendSimpleEmail(String toEmail, String subject,
                                String body)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("insulintracker102@gmail.com");
        message.setTo(toEmail);
        message.setText( "Tracking your blood sugar levels and insulin just got a whole lot easier! \n" +
                "\n" +
                "You registered for an account with iTracker, a multipurpose insulin tracking tool. iTracker helps you monitor your basal blood sugar level, calculate bolus insulin doses, and view detailed summaries of your blood sugar levels so you can focus less on diabetes, and focus more on doing what you love. Visit iTracker today to set up your custom profile. \n" +
                "\n" +
                "Happy tracking! \n" +
                "\n" +
                "Sincerely,\n" +
                "\n" +
                "The iTracker Team\n");
        message.setSubject("Thank you for creating an iTracker account");
        mailSender.send(message);
        System.out.println("Mail Send...");


    }

}
