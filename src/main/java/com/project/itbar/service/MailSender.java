package com.project.itbar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender {
    @Value("${spring.mail.username}")
    private String username;

    @Autowired
    JavaMailSender javaMailSender;

    public void sendMail(String mailTo, String mailSubject, String mailText){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(mailTo);
        mailMessage.setSubject(mailSubject);
        mailMessage.setText(mailText);

        javaMailSender.send(mailMessage);
        System.out.println(mailMessage);
    }
}
