package com.example.datawarehousDBStaging.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StagingService {

  @Autowired
  private ControllerService controllerService;

  @Autowired
  private EmailService emailService;

  public void processStaging() {
    List<Map<String, Object>> fileConfigs = controllerService.getActiveFileConfigs();
    Map<String, Object> config = controllerService.getConfig();
    String errorToEmail = (String) config.get("error_to_mail");

    // Lấy ngày hôm nay.
    String dateNow = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

    /**
     * Kiểm tra xem dữ liệu đã được load vào staging chưa.
     */
    for (Map<String, Object> fileConfig : fileConfigs) {
      String nameFilePattern = (String) fileConfig.get("name_file");
      String nameFile = nameFilePattern.replace("*", dateNow);

      List<Map<String, Object>> fileLogs = controllerService.getFileLogs(nameFile);

      if (!fileLogs.isEmpty()) {
        emailService.sendEmail(errorToEmail, "Dữ liệu đã được tải lên staging đầy đủ!",
            "The data file " + nameFile + " has already been loaded into staging.");
      } else {
        // Proceed with further processing
        // Implement further processing logic here
      }
    }
  }
}
