package com.example.datawarehousDBStaging.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender mailSender;

  public void sendEmail(String toEmail) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(toEmail);
    message.setSubject("Dữ liệu gửi thành công");
    message.setText("Rất tốt nhé!");
    mailSender.send(message);
  }

  public void sendErrorEmail(String toEmail, String errorMessage) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(toEmail);
    message.setSubject("Error in Data Load to Staging");
    message.setText("There was an error while loading data to staging: " + errorMessage);
    mailSender.send(message);
  }
}
