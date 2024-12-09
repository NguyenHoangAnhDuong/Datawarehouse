package com.example.datawarehousDBStaging.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.datawarehousDBStaging.entity.Config;
import com.example.datawarehousDBStaging.entity.FileConfig;
import com.example.datawarehousDBStaging.entity.FileLog;

@Service
public class WorkflowService {

  private static final Logger logger = LoggerFactory.getLogger(WorkflowService.class);


  @Autowired
  private ConfigService configService;

  @Autowired
  private FileConfigService fileConfigService;

  @Autowired
  private FileLogService fileLogService;

  @Autowired
  private EmailService emailService;

  public void executeWorkflow() {
    List<Config> activeConfigs = configService.getActiveConfigs();
    for (Config config : activeConfigs) {
      List<FileConfig> fileConfigs = fileConfigService.getActiveFileConfigs(config.getId());
      boolean allFilesLoaded = true;
      for (FileConfig fileConfig : fileConfigs) {
        List<FileLog> fileLogs = fileLogService.getFileLogs(fileConfig.getNameFile());
        if (fileLogs.isEmpty()) {
          allFilesLoaded = false;
          break;
        }
      }
      if (allFilesLoaded) {
//        String email = config.getErrorToMail();
        String email = "hnkb0713@gmail.com";
        String date = LocalDate.now().toString();
        String message = "Data for " + date + " has been successfully loaded into staging.";

        // Log the email details before sending it
        logger.info("Sending email to: " + email);
        logger.info("Subject: Data Load Confirmation");
        logger.info("Message: " + message);


        emailService.sendEmail(email, "Data Load Confirmation", message);
        return;
      }
    }
  }

}
