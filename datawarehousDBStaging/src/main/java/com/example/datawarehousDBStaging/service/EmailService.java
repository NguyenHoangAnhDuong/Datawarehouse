package com.example.datawarehousDBStaging.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

  @Autowired
  private JavaMailSender mailSender;

//  public void sendEmail(String toEmail) {
//    SimpleMailMessage message = new SimpleMailMessage();
//    message.setTo(toEmail);
//    message.setSubject("Dữ liệu gửi thành công");
//    message.setText("Rất tốt nhé!");
//    mailSender.send(message);
//  }
//
//  public void sendErrorEmail(String toEmail, String errorMessage) {
//    SimpleMailMessage message = new SimpleMailMessage();
//    message.setTo(toEmail);
//    message.setSubject("Error in Data Load to Staging");
//    message.setText("There was an error while loading data to staging: " + errorMessage);
//    mailSender.send(message);
//  }

  public void sendEmail(String toEmail) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(toEmail);
    message.setSubject("Dữ liệu đã được tải lên Staging thành công");

    StringBuilder emailContent = new StringBuilder();
    emailContent.append("Kính gửi Quản trị viên,\n\n");
    emailContent.append("Quá trình tải dữ liệu vào Staging đã hoàn tất thành công.\n");


    emailContent.append("\nXin cảm ơn!\n");
    message.setText(emailContent.toString());

    mailSender.send(message);
  }

  // Gửi email thông báo lỗi khi có file chưa load thành công và đã bắt đầu xử lý lại
  public void sendErrorEmail(String toEmail, String errorMessage) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(toEmail);
    message.setSubject("Error in Data Load to Staging");
    message.setText("There was an error while loading data to staging: " + errorMessage);
    mailSender.send(message);
  }

  // Gửi email chi tiết về các file chưa load thành công và quá trình xử lý lại
  public void sendDetailedErrorEmail(String toEmail, String failedFilesMessage) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(toEmail);
    message.setSubject("Các file chưa được load thành công vào Staging");
    message.setText("Các file sau chưa được load thành công vào staging:\n" + failedFilesMessage);
    mailSender.send(message);
  }
}
